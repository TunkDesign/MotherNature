package com.TunkDesign.MotherNature;

//Java import
import java.util.logging.Level;
import java.util.logging.Logger;

//Referenced classes of package com.TunkDesign.MotherNature:
//          MotherNatureSettings

public class MotherNatureLogging
{

  public MotherNatureLogging()
  {
  }

  public void info(String s)
  {
      logger.log(Level.INFO, (new StringBuilder("[MotherNature] ")).append(s).toString());
  }

  public void debug(String s)
  {
      if(MotherNature.debugMode)
          logger.log(Level.INFO, (new StringBuilder("[MotherNature] DEBUG: ")).append(s).toString());
  }

  public void severe(String s)
  {
      logger.log(Level.SEVERE, (new StringBuilder("[MotherNature] SEVERE: ")).append(s).toString());
  }

  public void warning(String s)
  {
      logger.log(Level.WARNING, (new StringBuilder("[MotherNature] WARNING: ")).append(s).toString());
  }

  public static final Logger logger = Logger.getLogger("Minecraft");

}
