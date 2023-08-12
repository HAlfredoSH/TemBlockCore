package dev.forgezst.blockcore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class MainCore extends JavaPlugin implements Listener {
  public void onEnable() {
    System.out.println(ChatColor.translateAlternateColorCodes('&', "&e&lBC&8: &astart successfully"));
    Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
  }
  
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    final Block block = event.getBlock();
    final Player player = event.getPlayer();
    if (block.getType() != Material.COBWEB) {
      event.setCancelled(true);
      return;
    } 
    (new BukkitRunnable() {
        int timeLeft = 30;
        
        public void run() {
          if (this.timeLeft <= 0) {
            block.setType(Material.AIR);
            cancel();
            return;
          } 
          player.spigot().sendMessage(ChatMessageType.ACTION_BAR, 
              TextComponent.fromLegacyText(ChatColor.YELLOW + "Blocks placed will be removed at: " + this.timeLeft + "s"));
          this.timeLeft--;
        }
      }).runTaskTimer((Plugin)this, 0L, 20L);
  }
  
  public void onDisable() {}
}
