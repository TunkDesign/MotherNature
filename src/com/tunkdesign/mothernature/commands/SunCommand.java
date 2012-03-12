package com.TunkDesign.MotherNature.commands;

//Local import
import com.TunkDesign.MotherNature.MotherNature;
import com.TunkDesign.MotherNature.MotherNaturePermissions;
import com.TunkDesign.MotherNature.thread.MotherNatureThread;
//Bukkit import
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SunCommand
    implements CommandExecutor
{

    public SunCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.sun"))
        {
            player.getWorld().setStorm(false);
            player.getWorld().setThundering(false);
            MotherNatureThread.thunderSteps = 0;
            MotherNatureThread.rainSteps = 0;
            MotherNatureThread.thunderIntSteps = 0;
            MotherNatureThread.rainIntSteps = 0;
            player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("The clouds clear! It is now ").append(ChatColor.GOLD).append("Sunny").toString());
        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
