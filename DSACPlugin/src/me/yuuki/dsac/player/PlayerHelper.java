package me.yuuki.dsac.player;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.yuuki.dsac.Main;

public class PlayerHelper {
	ArrayList<Player> playerList;
	ArrayList<String> hashList = new ArrayList<>();
	boolean test = false;

	public PlayerHelper() {
		playerList = new ArrayList<>();
        String ip = Main.data.getIp();        
        String list = Main.data.getList();
        if(list.contains(ip)) {
        	test = true;
        }else {
        	Main.plugin.getLogger().info("Not active!");
        	return;
        }
        String[] gg = Main.data.getHash();
        for(String ss : gg) {
        	hashList.add(ss);
        }
	}
	@SuppressWarnings("deprecation")
	public void newPlayer(String name) {
		Player p = Bukkit.getPlayer(name);
		if(p!=null) {
			Main.plugin.getLogger().info(name + " verify success!");
			p.sendMessage(Main.config.verifyMsg());
			playerList.add(p);
		}
	}
	public void removePlayer(Player p) {
		if(playerList.contains(p)) {
			playerList.remove(p);
		}
	}
	public boolean isCheck(Player p) {
		return playerList.contains(p);
	}
	@SuppressWarnings("deprecation")
	public void check(String data) {
		if(!test) {
			return;
		}
		String[] info = data.split(";");
		String username = info[0];
		String vertype = info[1];		
		Player p = Bukkit.getPlayer(username);		
		if(p==null) {
			return;
		}
		if(isCheck(p)) {
			return;
		}
		if(vertype.equals("Badlion")) {
			if(Main.config.allowBadlion()) {
				newPlayer(username);
			}else {
				p.kickPlayer(Main.config.kickMsg());
			}
		}else if(vertype.equals("Labymod")) {
			if(Main.config.allowLabyMod()) {
				newPlayer(username);
			}else {
				p.kickPlayer(Main.config.kickMsg());
			}
		}else if(vertype.equals("Forge")) {
			if(Main.config.allowForge()) {
				String[] mod = info[2].split(",");
				if(mod.length>0) {
					for(String hash :mod) {
						if(!hashList.contains(hash)) {
							p.kickPlayer(Main.config.kickMsg());
							return;
						}
					}
					newPlayer(username);
				}else {
					newPlayer(username);
				}
			}else {
				p.kickPlayer(Main.config.kickMsg());
			}
		}else if(vertype.equals("Optifine")) {
			String hash = info[2];
			if(!hashList.contains(hash)) {
				p.kickPlayer(Main.config.kickMsg());
			}else {
				newPlayer(username);
			}
		}else{
			String hash = info[2];
			if(!hashList.contains(hash)) {
				p.kickPlayer(Main.config.kickMsg());
			}else {
				newPlayer(username);
			}
		}
	}
}
