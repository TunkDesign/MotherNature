package com.TunkDesign.MotherNature.commands;

import com.Android.magiccarpet.Carpet;
import com.TunkDesign.MotherNature.MotherNature;
import com.TunkDesign.MotherNature.MotherNaturePermissions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class UmbrellaCommand
    implements CommandExecutor
{

    public UmbrellaCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.umbrella"))
        {
            if(MotherNature.umbrellas.get(player.getName()) == null)
            {
                int size = 5;
                if(args.length > 1)
                {
                    try
                    {
                        size = Integer.valueOf(args[1]).intValue();
                    }
                    catch(NumberFormatException e)
                    {
                        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("Correct usage is: /mn umbrella (size). The size is optional, and can only be 3, 5, or 7!").toString());
                        return false;
                    }
                    if(size != 3 && size != 5 && size != 7)
                    {
                        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("The size can only be 3, 5, or 7. Please enter a proper number").toString());
                        return false;
                    }
                }
                Carpet newCarpet = new Carpet(true);
                Location tempLocation = player.getLocation();
                tempLocation.setY(tempLocation.getBlockY() + 4);
                newCarpet.currentBlock = tempLocation.getBlock();
                newCarpet.setSize(size);
                newCarpet.setLights(true);
                player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You are now safe from the").append(ChatColor.GOLD).append(" Elements").toString());
                MotherNature.umbrellas.get(player.getName());
            } else
            {
                ((Carpet)MotherNature.umbrellas.get(player.getName())).removeCarpet();
                MotherNature.umbrellas.remove(player.getName());
                player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You are now unprotected against the").append(ChatColor.GOLD).append(" Elements").toString());
            }
        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
