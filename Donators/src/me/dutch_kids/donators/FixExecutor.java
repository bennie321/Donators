package me.dutch_kids.donators;

import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FixExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public FixExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			
			final ItemStack item = pl.getItemInHand();
			
			if (item == null)
			{
				pl.sendMessage(prefix + ChatColor.DARK_RED + " Selected item cannot be repaired!");
				return true;
			}
			
			if (!(item.getEnchantments().isEmpty()))
			{
				pl.sendMessage(prefix + ChatColor.DARK_RED + " You cannot repair enchanted items!");
				return true;
			}
			
			Material material = Material.getMaterial(item.getTypeId());
			
			if (material.isBlock() || material.getMaxDurability() < 1)
			{
				pl.sendMessage(prefix + ChatColor.DARK_RED + " Item has no durability, cannot be repaired");
				return true;
			}
			
			if (item.getDurability() == 0)
			{
				pl.sendMessage(prefix + ChatColor.DARK_RED + " Item is at full durability!");
				return true;
			}
			
			item.setDurability((short) 0);
			final String Name = pl.getItemInHand().getType().toString().toLowerCase(Locale.ENGLISH);
			pl.sendMessage(prefix + ChatColor.GREEN + " Successfully repaired your " + ChatColor.DARK_GREEN + Name.replace("_", " "));
			return true;
		}
		else
		{
			sender.sendMessage(prefix + ChatColor.DARK_RED + " Commands are only available for ingame players!");
			return false;
		}
	}
}
