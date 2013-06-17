package me.dutch_kids.donators;

import java.util.List;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ChangeSpawnerExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public ChangeSpawnerExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			List<String> mobs = plugin.getConfig().getStringList("mobspawner");
			
			if (args.length == 0)
			{
				String names = "";
				
				for(int i = 0; i < mobs.size(); i++)
				{
					if (pl.hasPermission("donators.spawner." + mobs.get(i).toLowerCase(Locale.ENGLISH)))
					{
						names += ChatColor.GREEN + ", " + mobs.get(i).toString().toLowerCase();
					}
					else
					{
						names += ChatColor.GREEN + ", " + ChatColor.RED + mobs.get(i).toString().toLowerCase(Locale.ENGLISH);
					}
					
				}
				
				pl.sendMessage(prefix + ChatColor.DARK_GREEN + " Usage: " + ChatColor.GREEN + " /dspawner <mobtype>");
				pl.sendMessage(prefix + ChatColor.DARK_GREEN + " Available mobtypes: " + names);
				return true;
			}
			
			else if (args.length > 0)
			{
				final Location block = pl.getTargetBlock(null, 40).getLocation();
			
				if ((block.getBlock().getType().equals(Material.MOB_SPAWNER)) && (block != null))
				{
					if (mobs.contains(args[0].toUpperCase()))
					{
						if (pl.hasPermission("donators.spawner." + args[0].toLowerCase()) || pl.isOp())
						{
							CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());
							String mobname = args[0].toUpperCase();
							spawner.setSpawnedType(EntityType.valueOf(mobname));
							spawner.setDelay(0);
							
							pl.sendMessage(prefix + ChatColor.GREEN + " Mob spawner successfully changed to " + ChatColor.DARK_GREEN + args[0].toLowerCase().replace("_", " "));
							return true;
						}
						else
						{
							pl.sendMessage(prefix + ChatColor.DARK_RED + " You have not the permission to change the mob spawner to " + ChatColor.RED + args[0].toLowerCase().replace("_", " "));
							return true;
						}
					}
					else
					{
						pl.sendMessage(prefix + ChatColor.DARK_RED + " Unknown mobtype " + ChatColor.RED + args[0].toLowerCase().replace("_", " "));
						pl.sendMessage(prefix + ChatColor.DARK_RED + " Check all mobtypes with " + ChatColor.RED + "/dspawner");
						return true;
					}
				}
				else
				{
					pl.sendMessage(prefix + ChatColor.DARK_RED + " That block is not a mob spawner! Be sure there is a mob spawner at the place you are looking at!");
					return true;
				}
			}
			return false;
		}
		else
		{
			sender.sendMessage(prefix + ChatColor.DARK_RED + " Commands are only available for ingame players!");
			return true;
		}
	}
}
