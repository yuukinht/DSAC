package me.yuuki.dsac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHelper {
	FileConfiguration config;
	String [] joinMsgs = null;
	String [] verifyMsgs = null;
	public ConfigHelper() {
		config = Main.plugin.getConfig();
		setupConfig();
	}
	public void setupConfig() {
		
		config.addDefault("mustVerify" , true);
        config.addDefault("allowForge" , false);
        config.addDefault("allowBadlion" , false);
        config.addDefault("allowLabyMod" , false);
        config.addDefault("kickMsg" , "You have been kick by console!");
        config.addDefault("verifyMsg" , "Verify Success!");
        
        config.options().copyDefaults(true);
        Main.plugin.saveConfig();
        joinMsgs = readFile("join.yml");
        verifyMsgs = readFile("verify.yml");
    }
	public boolean allowForge() {
		return config.getBoolean("allowForge");
	}
	public boolean allowBadlion() {
		return config.getBoolean("allowBadlion");
	}
	public boolean allowLabyMod() {
		return config.getBoolean("allowLabyMod");
	}
	public boolean mustVerify() {
		return config.getBoolean("mustVerify");
	}
	public String kickMsg() {
		return config.getString("kickMsg");
	}
	public String[] joinMsg() {
		return joinMsgs;
	}
	public String[] verifyMsg() {
		return verifyMsgs;
	}
	public String[] readFile(String name) {
		
		File msg = new File(Main.plugin.getDataFolder(),name);
        if(!msg.exists()) {
        	try {
				msg.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else {
        	Scanner scan;
			try {
				scan = new Scanner(msg,"UTF-8");
				ArrayList<String> strs = new ArrayList<>();
	        	while(scan.hasNextLine()) {	        		
	        		strs.add(scan.nextLine());
	        	}
	        	String[] cache = new String[strs.size()];
	        	for(int i=0;i<strs.size();i++) {
	        		cache[i] = strs.get(i);
	        	}        	
	        	scan.close();
	        	return cache;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}        	
        }
		return null;
	}
}
