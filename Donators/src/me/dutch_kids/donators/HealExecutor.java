package me.dutch_kids.donators;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public HealExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			pl.setHealth(20);
			pl.setFoodLevel(20);
			pl.sendMessage(prefix + ChatColor.GREEN + " You have been healed!");
			return true;
		}
		else
		{
			sender.sendMessage(prefix + ChatColor.DARK_RED + " Commands are only available for ingame players!");
			return true;
		}
	}

}
