package me.yuuki.dsac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Data {
	public Data() {		
	}
	public String getIp() {
		try { 
            URL url=new URL("http://ip-api.com/line/?fields=query");
            URLConnection conn=url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s;
            s=in.readLine();
            if(s!=null) {
            	return s;
            }
            in.close();
        } catch(Exception e) {
        	return null;
        }
		return null;
	}
	public String getList() {
		try { 
            URL url=new URL("https://pastebin.com/raw/eZuTrsbp");
            URLConnection conn=url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s;
            s=in.readLine();
            if(s!=null) {            	
            	return s;
            }
            in.close();
        } catch(Exception e) {
        	return null;
        }
		return null;
	}
	public String[] getHash() {
		try { 
            URL url=new URL("https://pastebin.com/raw/SfVzWe4N");
            URLConnection conn=url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s;
            s=in.readLine();
            if(s!=null) {
            	return s.split(";");
            }
            in.close();
        } catch(Exception e) {
        	return null;
        }
		return null;
	}
}
