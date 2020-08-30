package me.yuuki.dsac.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import me.yuuki.dsac.Main;

public class EventHelper implements Listener{
	
	public EventHelper() {
		Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent p) {
		//p.setJoinMessage(null);		
		Player player = p.getPlayer();
		if(!Main.playerHelper.isCheck(player) && Main.config.mustVerify() && !isBypass(player)) {
			String[] msg = Main.config.joinMsg();
			player.sendMessage(msg);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent p) {
		//p.setQuitMessage(null);
		Main.playerHelper.removePlayer(p.getPlayer());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent p) {
		Player player = p.getPlayer();
		if(!Main.playerHelper.isCheck(player) && Main.config.mustVerify() && !isBypass(player)) {
			p.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent p) {
		Player player = p.getPlayer();
		if(!Main.playerHelper.isCheck(player) && Main.config.mustVerify() && !isBypass(player)) {
			p.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(PlayerCommandPreprocessEvent p) {
		Player player = p.getPlayer();
		if(!Main.playerHelper.isCheck(player) && Main.config.mustVerify() && !isBypass(player)) {
			p.setCancelled(true);
		}
	}
	public boolean isBypass(Player p) {
		return p.hasPermission("dsac.bypass");
	}
}