package me.dutch_kids.donators;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TnTExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public TnTExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			pl.sendMessage(prefix + ChatColor.GREEN + " Tnt is ignited!");
			return true;
		}
		else
		{
			sender.sendMessage(prefix + ChatColor.DARK_RED + " Commands are only available for ingame players!");
			return false;
		}
	}

}
