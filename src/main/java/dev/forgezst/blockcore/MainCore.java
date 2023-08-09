package dev.forgezst.blockcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class MainCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.translateAlternateColorCodes('&',"&d&lBC&8: &aInicio correctamente"));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.COBWEB) {
           new BukkitRunnable() {
                @Override
                public void run() {
                    block.setType(Material.AIR);
                }
            }.runTaskLater(this, 30 * 10); // 60 segundos (20 ticks/segundo)
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}