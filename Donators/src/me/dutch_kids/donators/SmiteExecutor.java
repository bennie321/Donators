package me.dutch_kids.donators;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public SmiteExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			
			pl.getWorld().strikeLightning(pl.getTargetBlock(null, 600).getLocation());
			return true;
		}
		else
		{
			sender.sendMessage(prefix + ChatColor.DARK_RED + " Commands are only available for ingame players!");
			return false;
		}
	}
}
