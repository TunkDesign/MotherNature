package com.TunkDesign.MotherNature;

//Bukkit import
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

//Local import
import com.Android.magiccarpet.Carpet;


//Referenced classes of package com.TunkDesign.MotherNature:
//          MotherNature, MotherNatureLogging

public class MotherNatureListeners implements Listener {
	
	/**
   * Bukkit Listeners class
   */


  /**
   * Load and parse the PlayerListeners
   */

  @EventHandler(priority = EventPriority.MONITOR)
       public void onPlayerInteract(PlayerInteractEvent event) {
  	if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && event.hasItem() && event.getItem().getTypeId() == MotherNatureSettings.lightningWand && MotherNaturePermissions.has(event.getPlayer(), "mothernature.lwand"))
          event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock(null, 500).getLocation());
       }

  	public void onPlayerQuit(PlayerQuitEvent event)
       {
  		Player player = event.getPlayer();
          Carpet carpet = (Carpet)MotherNature.umbrellas.get(player.getName());
          if(carpet == null)
          {
              return;
          } else
          {
              carpet.removeCarpet();
              return;
          }
       }

       public void onPlayerTeleport(PlayerTeleportEvent event)
       {
      	 Location to = event.getTo().clone();
           Player player = event.getPlayer();
           Carpet carpet = (Carpet)MotherNature.umbrellas.get(player.getName());
           if(carpet == null)
               return;
           to.setY(to.getY() + 4D);
           Location last = carpet.currentBlock.getLocation();
           if(last.getBlockX() == to.getBlockX() && last.getBlockY() == to.getBlockY() && last.getBlockZ() == to.getBlockZ())
           {
               return;
           } else
           {
               carpet.removeCarpet();
               carpet.currentBlock = to.getBlock();
               carpet.drawCarpet();
               return;
           }
       }

       public void onPlayerMove(PlayerMoveEvent event)
       {
      	 Location to = event.getTo().clone();
           Location from = event.getFrom().clone();
           Player player = event.getPlayer();
           Carpet carpet = (Carpet)MotherNature.umbrellas.get(player.getName());
           if(carpet == null)
               return;
           to.setY(to.getY() + 4D);
           from.setY(from.getY() + 4D);
           if(from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ())
               return;
           carpet.removeCarpet();
           if(MotherNaturePermissions.has(player, "mothernature.command.umbrella"))
           {
               carpet.currentBlock = to.getBlock();
               carpet.drawCarpet();
               if(MotherNaturePermissions.has(player, "mothernature.parachute") && player.getFallDistance() >= 1.0F)
               {
                   Vector v = player.getVelocity().multiply(1.5D).setY(0);
                   player.setVelocity(v);
                   player.setFallDistance(0.0F);
               }
           } else
           {
               MotherNature.umbrellas.remove(player.getName());
           }
       }

       public void onPlayerJoin(PlayerJoinEvent event)
       {
      	 Player player = event.getPlayer();
           Carpet carpet = (Carpet)MotherNature.umbrellas.get(player.getName());
           if(carpet == null && !player.equals(null) && MotherNaturePermissions.has(player, "mothernature.command.umbrella"))
           {
               World world = event.getPlayer().getWorld();
               if(world.isThundering())
                   player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("It is thundering, maybe you should pull out an").append(ChatColor.GOLD).append(" umbrella").toString());
           }
       }

}
