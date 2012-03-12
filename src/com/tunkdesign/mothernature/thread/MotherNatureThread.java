package com.TunkDesign.MotherNature.thread;

import java.util.Iterator;
//import java.util.List;
import com.TunkDesign.MotherNature.*;
//import org.bukkit.Server;
import org.bukkit.World;

public class MotherNatureThread
  implements Runnable
{

  public MotherNatureThread(MotherNature parent)
  {
      interrupted = false;
      this.parent = parent;
  }

  public void run()
  {
      do
      {
          try
          {
              Thread.sleep(5000L);
              if(rainSteps * 5 >= MotherNatureSettings.rainInterval)
              {
                  if(rainIntSteps == 0)
                  {
                      World world;
                      for(Iterator<World> iterator = parent.getServer().getWorlds().iterator(); iterator.hasNext(); world.setThundering(false))
                      {
                          world = (World)iterator.next();
                          world.setStorm(true);
                      }

                      MotherNature.log.info("Server is now raining.");
                  }
                  if(rainIntSteps * 5 >= MotherNatureSettings.rainLength)
                  {
                      World world;
                      for(Iterator<World> iterator1 = parent.getServer().getWorlds().iterator(); iterator1.hasNext(); world.setThundering(false))
                      {
                          world = (World)iterator1.next();
                          world.setStorm(false);
                      }

                      MotherNature.log.info("Server is no longer raining.");
                      rainIntSteps = 0;
                      rainSteps = 0;
                  } else
                  {
                      rainIntSteps++;
                  }
                  if(thunderSteps * 5 >= MotherNatureSettings.thunderInterval)
                  {
                      if(thunderIntSteps == 0)
                      {
                          World world;
                          for(Iterator<World> iterator2 = parent.getServer().getWorlds().iterator(); iterator2.hasNext(); world.setThundering(true))
                              world = (World)iterator2.next();

                          MotherNature.log.info("Server is now thundering.");
                      }
                      if(thunderIntSteps * 5 >= MotherNatureSettings.thunderLength)
                      {
                          World world;
                          for(Iterator<World> iterator3 = parent.getServer().getWorlds().iterator(); iterator3.hasNext(); world.setThundering(false))
                              world = (World)iterator3.next();

                          MotherNature.log.info("Server is no longer thundering.");
                          thunderIntSteps = 0;
                          thunderSteps = 0;
                      } else
                      {
                          thunderIntSteps++;
                      }
                  } else
                  {
                      World world;
                      for(Iterator<World> iterator4 = parent.getServer().getWorlds().iterator(); iterator4.hasNext(); world.setThundering(false))
                          world = (World)iterator4.next();

                      thunderSteps++;
                  }
              } else
              {
                  World world;
                  for(Iterator<World> iterator5 = parent.getServer().getWorlds().iterator(); iterator5.hasNext(); world.setThundering(false))
                  {
                      world = (World)iterator5.next();
                      world.setStorm(false);
                  }

                  rainSteps++;
              }
              continue;
          }
          catch(InterruptedException ex) { }
          break;
      } while(!Thread.interrupted() && !interrupted);
  }

  public boolean interrupted;
  private MotherNature parent;
  public static int rainSteps = 0;
  public static int rainIntSteps = 0;
  public static int thunderSteps = 0;
  public static int thunderIntSteps = 0;

}
