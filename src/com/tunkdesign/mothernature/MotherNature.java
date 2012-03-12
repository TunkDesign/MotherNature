package com.TunkDesign.MotherNature;

//Java import
import java.util.*;
//Local import
import com.TunkDesign.MotherNature.thread.MotherNatureThread;
//Bukkit import
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
//import java.io.IOException;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

// Referenced classes of package com.TunkDesign.MotherNature:
//            MotherNatureLogging, MotherNatureSettings, MotherNaturePermissions, MotherNatureHelp, 
//            MotherNatureCommand, MotherNatureListeners

public class MotherNature extends JavaPlugin
{
    File configFile;
    FileConfiguration conFile;
    
    public MotherNature()
    {
    }
    
    /* Define Listener Class */
    public final MotherNatureListeners playerListeners = new MotherNatureListeners();
    
    /* Handle Plugin Disable */
    public void onDisable()
    {
        try
        {
            thread.interrupt();
            thread.join();
            log.info("Thread successfully joined.");
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        log.info((new StringBuilder("Plugin disabled. (version ")).append(getDescription().getVersion()).append(")").toString());
    }

    /* Handle Plugin Enable */
    @Override
    public void onEnable()
    {
    	loadConfiguration();
//    	config = new YamlConfiguration();
    	config = getConfig();
//    	loadYamls();
    	configFile = new File(getDataFolder(), "config.yml");
    	PluginManager pm = getServer().getPluginManager();
        MotherNatureSettings.load();
        MotherNaturePermissions.load(this);
        MotherNatureHelp.load(this);
        commandHandler = new MotherNatureCommand();
        commandHandler.addCommands(this);
        log.debug("Initiating threads");
        thread = new Thread(new MotherNatureThread(this), "mn_thread");
        thread.start();
        log.debug("Disabling vanilla weather");
        
        World world;
        for(Iterator<World> iterator = getServer().getWorlds().iterator(); iterator.hasNext(); world.setWeatherDuration(0))
        {
            world = iterator.next();
            world.setThunderDuration(0);
        }
        
        /**
         * Load and parse the YAML config file
         */
        
        Configuration config = this.getConfig();
        File dataDirectory = new File((new StringBuilder("plugins")).append(File.separator).append("MotherNature").append(File.separator).toString());
        dataDirectory.mkdirs();
        File file = new File((new StringBuilder("plugins")).append(File.separator).append("MotherNature").toString(), "config.yml");
        MotherNature.log.debug((new StringBuilder("Loading config file: ")).append(file.getPath()).toString());
        getConfig();
        if(!file.exists())
        {
            MotherNature.log.debug("Config file not found, saving bare-bones file");
            config.set("mothernature.debug", dMode);
            HashMap<String, Integer> rainHash = new HashMap<String, Integer>();
            rainHash.put("interval", rInterval);
            rainHash.put("duration", rLenght);
            getConfig().getDefaults();
            HashMap<String, Integer> thunderHash = new HashMap<String, Integer>();
            thunderHash.put("interval", tInterval);
            thunderHash.put("duration", tLenght);
            config.set("mothernature.thunder", thunderHash);
            config.set("mothernature.wand", lWand);
            config.set("mothernature.umbrellatype", matName);
            config.set("mothernature.umbrellaMiddletype", matMidName);
            saveConfig();
        }
        setSettings();
        MotherNature.log.debug("Settings loaded");
        
        
        /* Register Events */
//        listeners = MotherNatureListeners.load(this);
        pm.registerEvents(this.playerListeners, this);
        log.info((new StringBuilder("Plugin enabled! (version ")).append(getDescription().getVersion()).append(")").toString());
        log.debug("Debug mode enabled!");
    }
    
    
    /**
     * Sets the internal variables
     */
    private static void setSettings()
    {
        debugMode = config.getBoolean("mothernature.debug", false);
        rainInterval = config.getInt("mothernature.rain.interval", rainInterval);
        rainLength = config.getInt("mothernature.rain.duration", rainLength);
        thunderInterval = config.getInt("mothernature.rain.interval", thunderInterval);
        thunderLength = config.getInt("mothernature.rain.duration", thunderLength);
        lightningWand = config.getInt("mothernature.wand", lightningWand);
        materialName = config.getString("mothernature.umbrellatype", materialName);
        MaterialMidName = config.getString("mothernature.umbrellaMiddletype", MaterialMidName);
    }

	public void loadConfiguration(){
        getConfig().options().copyDefaults(true); 
        saveConfig();
    }
    
    
    public static final MotherNatureLogging log = new MotherNatureLogging();
    private MotherNatureCommand commandHandler;
    private Thread thread;
    public static HashMap<World, Object> umbrellas = new HashMap<World, Object>();
    public MotherNatureListeners listeners;

    
	/**
     * Settings
     */    
    public static boolean debugMode = false;
    public boolean dMode = Boolean.valueOf(debugMode);
    
    public static int rainLength = 120;
    public int rLenght = Integer.valueOf(rainLength);
    
    public static int rainInterval = 600;
    public int rInterval = Integer.valueOf(rainInterval);
    
    public static int thunderLength = 30;
    public int tLenght = Integer.valueOf(thunderLength);
    
    public static int thunderInterval = 60;
    public int tInterval = Integer.valueOf(thunderInterval);
    
    public static int lightningWand = 369;
    public int lWand = Integer.valueOf(lightningWand);
    
    public static String materialName = null;
    public String matName = String.valueOf(materialName);
    
    public static Configuration config = null;
    
    public static String MaterialMidName = null;
    public String matMidName = String.valueOf(MaterialMidName);
}
