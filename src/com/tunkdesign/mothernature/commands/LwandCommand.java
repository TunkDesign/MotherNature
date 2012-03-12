package com.TunkDesign.MotherNature.commands;

//Local import
import com.TunkDesign.MotherNature.*;
//Bukkit import
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LwandCommand
    implements CommandExecutor
{

    public LwandCommand(MotherNature parent)
    {
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
        Player player = (Player)sender;
        if(!player.equals(null) && MotherNaturePermissions.has(player, "mothernature.command.lwand"))
            if(!player.getInventory().contains(MotherNature.lightningWand))
            {
                ItemStack item = new ItemStack(MotherNature.lightningWand);
                item.setAmount(1);
                player.getInventory().addItem(new ItemStack[] {
                    item
                });
                player.sendMessage((new StringBuilder()).append(ChatColor.GREEN).append("You have been given a").append(ChatColor.GOLD).append(" lightning wand").toString());
            } else
            if(player.getInventory().contains(MotherNature.lightningWand))
                player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You already have a lightning wand").toString());
            else
                player.sendMessage((new StringBuilder()).append(ChatColor.RED).append("You don't have the permission for a lightning wand").toString());
        return false;
    }
}
