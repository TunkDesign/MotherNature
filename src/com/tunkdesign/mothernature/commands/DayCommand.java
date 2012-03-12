package com.TunkDesign.MotherNature.commands;

//Local import
import com.TunkDesign.MotherNature.MotherNature;
import com.TunkDesign.MotherNature.MotherNaturePermissions;
//Bukkit import
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DayCommand
    implements CommandExecutor
{

    public DayCommand(MotherNature parent)
    {
        this.parent = parent;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        World world = (sender instanceof Player) ? ((Player)sender).getWorld() : (World)parent.getServer().getWorlds().get(0);
        world.getTime();
        Player player = (Player)sender;
        if(!player.equals(null))
            if(MotherNaturePermissions.has(player, "mothernature.command.day"))
            {
                world.setTime(23000L);
                player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You have changed the time to").append(ChatColor.GOLD).append(" Sunrise").toString());
            } else
            {
                sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You don't have permission to change the server time to day").toString());
            }
        return false;
    }

    private MotherNature parent;
}
