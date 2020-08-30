package me.yuuki.dsac;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.yuuki.dsac.Socket.SocketHelper;
import me.yuuki.dsac.command.Commands;
import me.yuuki.dsac.event.EventHelper;
import me.yuuki.dsac.player.PlayerHelper;

public class Main extends JavaPlugin{
	public static ServerSocket server = null;
	public static PlayerHelper playerHelper= null;
	SocketHelper socketHelper;
	public static Main plugin;
	public static ConfigHelper config;
	Logger LOGGER = getLogger();
	private Commands commands = new Commands();
	public static Data data = new Data();
	public void onEnable() {	
		if(data.getList() ==null) {
			getLogger().info("Disable because servet not running!");
        	getLogger().info("Data server will restart soon, Sorry!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		init();
	}
	public void onDisable() {
		unload();
		try {
			if(server!=null) {
				server.close();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void init() {		
		plugin = this;
		new EventHelper();
		playerHelper = new PlayerHelper();
		config = new ConfigHelper();		
		getCommand("dscheck").setExecutor(commands);
		try {
			server = new ServerSocket(2468);
			socketHelper = new SocketHelper();
			socketHelper.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(config.joinMsg());
		}
	}
	public void unload() {
		
	}
	
}
