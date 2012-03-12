package com.TunkDesign.MotherNature.commands;

import java.util.ArrayList;
import java.util.Iterator;
import com.TunkDesign.MotherNature.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HelpCommand
    implements CommandExecutor
{

    public HelpCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(MotherNaturePermissions.has(player, "mothernature.command.help", false))
        {
            ArrayList<String> commands = MotherNatureHelp.getMessages(player);
            String commandString;
            for(Iterator<String> iterator = commands.iterator(); iterator.hasNext(); player.sendMessage(commandString))
                commandString = (String)iterator.next();

        } else
        {
            player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You do not have permission to use this command.").toString());
        }
        return true;
    }
}
