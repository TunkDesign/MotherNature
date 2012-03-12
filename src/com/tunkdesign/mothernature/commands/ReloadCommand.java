package com.TunkDesign.MotherNature.commands;

//Local import
import com.TunkDesign.MotherNature.*;
//Bukkit import
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ReloadCommand
    implements CommandExecutor
{

    public ReloadCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.reload"))
        {
            MotherNatureSettings.load();
            player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("Mother Nature Settings have been ").append(ChatColor.GOLD).append("Reloaded").toString());
            MotherNature.log.info("[MotherNature] Settings has been reloaded!");
        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
