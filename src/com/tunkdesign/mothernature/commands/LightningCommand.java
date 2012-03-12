package com.TunkDesign.MotherNature.commands;

import java.util.Iterator;
//import java.util.List;
import com.TunkDesign.MotherNature.MotherNature;
import com.TunkDesign.MotherNature.MotherNaturePermissions;
import org.bukkit.ChatColor;
//import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class LightningCommand
    implements CommandExecutor
{

    public LightningCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.strike"))
        {
            if(args.length > 1)
            {
                Player target = null;
                for(Iterator<Player> iterator = player.getWorld().getPlayers().iterator(); iterator.hasNext();)
                {
                    Player worldplayer = (Player)iterator.next();
                    if(worldplayer.getName().equalsIgnoreCase(args[1]))
                        target = worldplayer;
                }

                if(target == null)
                    player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("That player is not online.").toString());
                player.getWorld().strikeLightning(target.getLocation());
                player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("Boom! A bolt of lightning shoots from the sky and hits ").append(ChatColor.GOLD).append(target.getName()).toString());
            }
        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
