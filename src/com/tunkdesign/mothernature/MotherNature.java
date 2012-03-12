package com.TunkDesign.MotherNature;

//Java import
import java.util.*;
import java.io.File;
//Local import
import com.TunkDesign.MotherNature.thread.MotherNatureThread;
//Bukkit import
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

// Referenced classes of package com.TunkDesign.MotherNature:
//            MotherNatureLogging, MotherNatureSettings, MotherNaturePermissions, MotherNatureHelp, 
//            MotherNatureCommand, MotherNatureListeners

public class MotherNature extends JavaPlugin
{
    File configFile;
    FileConfiguration config;
    
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
    public void onEnable()
    {
    	loadConfiguration();
    	config = new YamlConfiguration();
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

        /* Register Events */
        pm.registerEvents(this.playerListeners, this);
        log.info((new StringBuilder("Plugin enabled! (version ")).append(getDescription().getVersion()).append(")").toString());
        log.debug("Debug mode enabled!");
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

}
