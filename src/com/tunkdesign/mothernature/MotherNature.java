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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

// Referenced classes of package com.TunkDesign.MotherNature:
//            MotherNatureLogging, MotherNatureSettings, MotherNaturePermissions, MotherNatureHelp, 
//            MotherNatureCommand, MotherNatureListeners

public class MotherNature extends JavaPlugin
{
	File configFile;
    File groupsFile;
    File historyFile;
    FileConfiguration config;
    FileConfiguration groups;
    FileConfiguration history;
    
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

        /* Register Events */
//        listeners = MotherNatureListeners.load(this);
        pm.registerEvents(this.playerListeners, this);
        log.info((new StringBuilder("Plugin enabled! (version ")).append(getDescription().getVersion()).append(")").toString());
        log.debug("Debug mode enabled!");
    }
    
    public void loadConfiguration(){
        //See "Creating you're defaults"
        getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
        //Save the config whenever you manipulate it
        saveConfig();
    }
    
//    private void firstRun() throws Exception {
//        if(!configFile.exists()){
//            configFile.getParentFile().mkdirs();
//            copy(getResource("config.yml"), configFile);
//        }
//    }
//    private void copy(InputStream in, File file) {
//        try {
//            OutputStream out = new FileOutputStream(file);
//            byte[] buf = new byte[1024];
//            int len;
//            while((len=in.read(buf))>0){
//                out.write(buf,0,len);
//            }
//            out.close();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void saveYamls() {
//        try {
//            config.save(configFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void loadYamls() {
//        try {
//            config.load(configFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public static final MotherNatureLogging log = new MotherNatureLogging();
    private MotherNatureCommand commandHandler;
    private Thread thread;
    public static HashMap<World, Object> umbrellas = new HashMap<World, Object>();
    public MotherNatureListeners listeners;

}
