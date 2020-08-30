package me.yuuki.dsac.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.yuuki.dsac.Main;

public class Commands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg) {
		
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("dscheck")) {
				if (arg.length != 0) {	
					@SuppressWarnings("deprecation")
					Player pp = Bukkit.getPlayer(arg[0]);
					if (pp==null) {						
						return false;
					}
					if(Main.playerHelper.isCheck(pp)) {
						String msg = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + pp.getName()+" verify!";
						sender.sendMessage(msg);
						return true;
					}else {	
						String msg = ChatColor.RED.toString() + ChatColor.BOLD.toString() + pp.getName()+" no verify!";
						sender.sendMessage(msg);
						return true;
					}
				}
			}
		}
		return false;
	}

}