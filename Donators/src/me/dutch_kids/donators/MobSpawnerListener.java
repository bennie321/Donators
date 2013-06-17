package me.dutch_kids.donators;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MobSpawnerListener implements Listener
{
	final static String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Donators" + ChatColor.WHITE + "]";
	
	public Donators plugin;
	
	public MobSpawnerListener(Donators plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMineEvent(BlockBreakEvent spawner)
	{
		Block block = spawner.getBlock();
		Player player = (Player) spawner.getPlayer();
		ItemStack iteminhand = player.getItemInHand();
		
		if (iteminhand != null && iteminhand.getType().equals(Material.MOB_SPAWNER))
		{
			spawner.setCancelled(true);
			player.sendMessage(prefix + ChatColor.DARK_RED + " You cannot destroy blocks while holding a mob spawner!");
			return;
		}
		
		if (!(block.getType().equals(Material.MOB_SPAWNER)))
		{
			return;
		}
		
		if (spawner.isCancelled())
		{
			return;
		}
		
		if (!(spawner.getClass().equals(BlockBreakEvent.class)))
		{
			return;
		}
		
		boolean silkTouch = SilkTouchEnchant(iteminhand);
		
		if (silkTouch)
		{
			if (player.hasPermission("donators.mine.spawner") || player.isOp())
			{
				ItemStack spawnerItem = new ItemStack(Material.MOB_SPAWNER, 1);
				player.getWorld().dropItemNaturally(spawner.getBlock().getLocation(), spawnerItem);
				player.sendMessage(prefix + ChatColor.GREEN + " You have mined the spawner succesfully!");
			}
			else
			{
				player.sendMessage(prefix + ChatColor.DARK_RED + " To mine mob spawners you have to donate!");
			}
		}
	}
	
	public boolean SilkTouchEnchant(ItemStack tool)
	{
		int minEnchantLevel = plugin.getConfig().getInt("Mining-SilkTouch-Level", 1);
			
		if (minEnchantLevel == 0)
		{
			return true;
		}
			
		if (tool == null)
		{
			return false;
		}
		
		if (!(tool.containsEnchantment(Enchantment.SILK_TOUCH)))
		{
			return false;
		}
		
		return tool.getEnchantmentLevel(Enchantment.SILK_TOUCH) >= minEnchantLevel;
	}
}
