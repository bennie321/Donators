package me.dutch_kids.donators;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Donators extends JavaPlugin
{
	public final static Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
		Load();
		log.info("[Donators] Config loaded!");
		
		getCommand("dsmite").setExecutor(new SmiteExecutor(this));
		getCommand("dfireball").setExecutor(new FireballExecutor(this));
		getCommand("dfix").setExecutor(new FixExecutor(this));
		getCommand("dheal").setExecutor(new HealExecutor(this));
		getCommand("dspawner").setExecutor(new ChangeSpawnerExecutor(this));
		getCommand("dtnt").setExecutor(new TnTExecutor(this));
		log.info("[Donators] Commands created!");
		
		getServer().getPluginManager().registerEvents(new MobSpawnerListener(this), this);
		log.info("[Donators] Listeners created!");
	}
	
	public void onDisable()
	{	
		
	}
	
	public void Load()
	{
		File configFile = new File(this.getDataFolder(), "config.yml");
		
		if (!configFile.exists())
		{
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}
}
