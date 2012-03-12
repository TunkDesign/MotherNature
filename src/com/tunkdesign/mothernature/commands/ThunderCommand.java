package com.TunkDesign.MotherNature.commands;

//Local import
import com.TunkDesign.MotherNature.*;
import com.TunkDesign.MotherNature.thread.MotherNatureThread;
//Bukkit import
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ThunderCommand
    implements CommandExecutor
{

    public ThunderCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.thunder"))
        {
            player.getWorld().setStorm(true);
            player.getWorld().setThundering(true);
            MotherNatureThread.thunderSteps = MotherNature.thunderInterval;
            MotherNatureThread.rainSteps = MotherNature.rainInterval;
            player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You have changed the weather to").append(ChatColor.GOLD).append(" Thunder").toString());
        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
