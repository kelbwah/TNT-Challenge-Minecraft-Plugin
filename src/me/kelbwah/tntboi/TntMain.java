package me.kelbwah.tntboi;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import net.md_5.bungee.api.ChatColor;

public class TntMain extends JavaPlugin implements Listener{

	@Override
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(label.equalsIgnoreCase("tntstart"))
		{
			if(sender instanceof Player)
			{
				Player newP = (Player) sender;
				if(newP.hasPermission("tntstart.use"))
				{
					newP.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "TNT Will Now Spawn Every 10 Seconds!");
					BukkitScheduler scheduler = getServer().getScheduler();
					scheduler.scheduleSyncRepeatingTask(this,  new Runnable() {
						@Override
						public void run() {
							EntityType tnt = EntityType.PRIMED_TNT; 
							newP.getLocation().getWorld().spawnEntity(newP.getLocation(), tnt);
						}
					}, 0L, 200L);
					return true;
				}
				newP.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "You do not have permission to use this.");
				return true;
			}
		}
		return true;
	}
	
	/*@EventHandler
	public void onPlayerSpawn(PlayerJoinEvent player)
	{
		Player newP = player.getPlayer();
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this,  new Runnable() {
			@Override
			public void run() {
				EntityType tnt = EntityType.PRIMED_TNT; 
				newP.getLocation().getWorld().spawnEntity(newP.getLocation(), tnt);
			}
		}, 0L, 200L);	
	}
	*/
	
}
