package com.TunkDesign.MotherNature;

//Java import
import java.io.File;
import java.util.HashMap;
//Bukkit import
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;



public class MotherNatureSettings extends JavaPlugin {
	File configFile;
    File groupsFile;
    File historyFile;
    FileConfiguration config;


  /**
   * Bukkit config class
   */


  /**
   * Load and parse the YAML config file
   */
  public static void load()
  {
      File dataDirectory = new File((new StringBuilder("plugins")).append(File.separator).append("MotherNature").append(File.separator).toString());
      dataDirectory.mkdirs();
      File file = new File((new StringBuilder("plugins")).append(File.separator).append("MotherNature").toString(), "config.yml");
      MotherNature.log.debug((new StringBuilder("Loading config file: ")).append(file.getPath()).toString());
//      config.load();
      if(!file.exists())
      {
          MotherNature.log.debug("Config file not found, saving bare-bones file");
//          config.setProperty("mothernature.debug", Boolean.valueOf(debugMode));
          HashMap<String, Integer> rainHash = new HashMap<String, Integer>();
          rainHash.put("interval", Integer.valueOf(rainInterval));
          rainHash.put("duration", Integer.valueOf(rainLength));
//          config.setProperty("mothernature.rain", rainHash);
          HashMap<String, Integer> thunderHash = new HashMap<String, Integer>();
          thunderHash.put("interval", Integer.valueOf(thunderInterval));
          thunderHash.put("duration", Integer.valueOf(thunderLength));
//          config.setProperty("mothernature.thunder", thunderHash);
//          config.setProperty("mothernature.wand", Integer.valueOf(lightningWand));
//          config.setProperty("mothernature.umbrellatype", String.valueOf(materialName));
//          config.setProperty("mothernature.umbrellaMiddletype", String.valueOf(MaterialMidName));
//          config.save();
      }
      setSettings();
      MotherNature.log.debug("Settings loaded");
  }

  /**
   * Sets the internal variables
   */
  private static void setSettings()
  {
//      debugMode = config.getBoolean("mothernature.debug", false);
//      rainInterval = config.getInt("mothernature.rain.interval", rainInterval);
//      rainLength = config.getInt("mothernature.rain.duration", rainLength);
//      thunderInterval = config.getInt("mothernature.rain.interval", thunderInterval);
//      thunderLength = config.getInt("mothernature.rain.duration", thunderLength);
//      lightningWand = config.getInt("mothernature.wand", lightningWand);
//      materialName = config.getString("mothernature.umbrellatype", materialName);
//      MaterialMidName = config.getString("mothernature.umbrellaMiddletype", MaterialMidName);
  }
	/**
   * Settings
   */


  public static boolean debugMode = false;
  public static int rainLength = 120;
  public static int rainInterval = 600;
  public static int thunderLength = 30;
  public static int thunderInterval = 60;
  public static int lightningWand = 317;
  public static String materialName = null;
//  public static Configuration config = null;
  public static String MaterialMidName = null;
}