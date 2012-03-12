package com.TunkDesign.MotherNature.commands;

import com.TunkDesign.MotherNature.MotherNature;
import com.TunkDesign.MotherNature.MotherNaturePermissions;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
//import org.bukkit.plugin.PluginDescriptionFile;

public class VersionCommand
    implements CommandExecutor
{

    public VersionCommand(MotherNature parent)
    {
        this.parent = parent;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        if(MotherNaturePermissions.has((Player)sender, "mothernature.command.version", false))
            sender.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You're running: ").append(ChatColor.GOLD.toString()).append(parent.getDescription().getName()).append(ChatColor.GREEN).append(" version ").append(ChatColor.GOLD).append(parent.getDescription().getVersion()).toString());
        else
            sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        return true;
    }

    private MotherNature parent;
}
