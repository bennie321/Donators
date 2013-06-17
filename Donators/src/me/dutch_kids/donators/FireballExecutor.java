package me.dutch_kids.donators;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

public class FireballExecutor implements CommandExecutor
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public FireballExecutor(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player pl = (Player) sender;
			
			Class<? extends Entity> clazz = Fireball.class;
			
			if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase("small"))
				{
					clazz = SmallFireball.class;
				}
				else if (args[0].equalsIgnoreCase("large"))
				{
					clazz = LargeFireball.class;
				}
				else if (args[0].equalsIgnoreCase("snowball"))
				{
					clazz = Snowball.class;
				}
				else if (args[0].equalsIgnoreCase("arrow"))
				{
					clazz = Arrow.class;
				}
			}
				
			final Vector direction = pl.getEyeLocation().getDirection().multiply(2);
			Projectile projectile = (Projectile) pl.getWorld().spawn(pl.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), clazz);
			projectile.setShooter(pl);
			projectile.setVelocity(direction);
			return true;
		}
		return false;
	}
	
}
