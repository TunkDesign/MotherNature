package com.TunkDesign.MotherNature;

//Java import
import java.util.HashMap;
//Local import
import com.TunkDesign.MotherNature.commands.DayCommand;
import com.TunkDesign.MotherNature.commands.HelpCommand;
import com.TunkDesign.MotherNature.commands.LightningCommand;
import com.TunkDesign.MotherNature.commands.LwandCommand;
import com.TunkDesign.MotherNature.commands.NightCommand;
import com.TunkDesign.MotherNature.commands.RainCommand;
import com.TunkDesign.MotherNature.commands.ReloadCommand;
import com.TunkDesign.MotherNature.commands.SunCommand;
import com.TunkDesign.MotherNature.commands.ThunderCommand;
import com.TunkDesign.MotherNature.commands.UmbrellaCommand;
import com.TunkDesign.MotherNature.commands.VersionCommand;
//Bukkit import
import org.bukkit.command.*;
import org.bukkit.entity.Player;

//Referenced classes of package com.TunkDesign.MotherNature:
//          MotherNature

public class MotherNatureCommand
  implements CommandExecutor
{

  public MotherNatureCommand()
  {
      executors = new HashMap<String, CommandExecutor>();
  }

  public void registerExecutor(String subcmd, CommandExecutor cmd)
  {
      executors.put(subcmd.toLowerCase(), cmd);
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
  {
      String commandName = command.getName().toLowerCase();
      if((sender instanceof Player) && commandName.equals("mn"))
      {
          if(args.length == 0)
              return false;
          String subcommandName = args[0].toLowerCase();
          if(!executors.containsKey(subcommandName))
              return false;
          else
              return executors.get(subcommandName).onCommand(sender, command, commandLabel, args);
      } else
      {
          return false;
      }
  }

  public void addCommands(MotherNature parent)
  {
      registerExecutor("version", new VersionCommand(parent));
      registerExecutor("reload", new ReloadCommand(parent));
      registerExecutor("help", new HelpCommand(parent));
      registerExecutor("rain", new RainCommand(parent));
      registerExecutor("thunder", new ThunderCommand(parent));
      registerExecutor("strike", new LightningCommand(parent));
      registerExecutor("sun", new SunCommand(parent));
      registerExecutor("umbrella", new UmbrellaCommand(parent));
      registerExecutor("sunrise", new DayCommand(parent));
      registerExecutor("sunset", new NightCommand(parent));
      registerExecutor("lwand", new LwandCommand(parent));
      parent.getCommand("mn").setExecutor(this);
  }

  private HashMap<String, CommandExecutor> executors;
}
