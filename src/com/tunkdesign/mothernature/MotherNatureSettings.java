package com.TunkDesign.MotherNature;

//Java import
import java.io.File;
import java.util.HashMap;
//Bukkit import
//import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;

/**
 * This class is not used, but it's required!
 */

public class MotherNatureSettings {
	File configFile;
    //static FileConfiguration config;

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
          config.set("mothernature.debug", Boolean.valueOf(debugMode));
          HashMap<String, Integer> rainHash = new HashMap<String, Integer>();
          rainHash.put("interval", Integer.valueOf(rainInterval));
          rainHash.put("duration", Integer.valueOf(rainLength));
          config.set("mothernature.rain", rainHash);
          HashMap<String, Integer> thunderHash = new HashMap<String, Integer>();
          thunderHash.put("interval", Integer.valueOf(thunderInterval));
          thunderHash.put("duration", Integer.valueOf(thunderLength));
          config.set("mothernature.thunder", thunderHash);
          config.set("mothernature.wand", Integer.valueOf(lightningWand));
          config.set("mothernature.umbrellatype", String.valueOf(materialName));
          config.set("mothernature.umbrellaMiddletype", String.valueOf(MaterialMidName));
//          config.save();
      }
//      setSettings();
      MotherNature.log.debug("Settings loaded");
  }

  public static boolean debugMode = false;
  public static int rainLength = 120;
  public static int rainInterval = 600;
  public static int thunderLength = 30;
  public static int thunderInterval = 60;
  public static int lightningWand = 369;
  public static String materialName = null;
  public static Configuration config = null;
  public static String MaterialMidName = null;
}