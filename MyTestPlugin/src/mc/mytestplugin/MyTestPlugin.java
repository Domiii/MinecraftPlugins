package mc.mytestplugin;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import commands.CustomOpCommand;
import commands.FunCommand;
import commands.PlayerCommanderCommand;
import commands.SuckCommand;
import npcs.DiggerTrait;

//plugman reload MyTestPlugin

public class MyTestPlugin extends JavaPlugin {
	public static MyTestPlugin instance;
	
	void registerCommands() {
		instance = this;
		getCommand("testtest").setExecutor(new CustomOpCommand());
		getCommand("runasplayer").setExecutor(new PlayerCommanderCommand());
		getCommand("fun").setExecutor(new FunCommand());
		getCommand("suck").setExecutor(new SuckCommand());
	}
 
    @Override
    public void onEnable() {
    	registerCommands();
    	setupCitizens();
    	
    	getLogger().info("hi 3!");
    	for (Player p : Bukkit.getOnlinePlayers()) {
    		p.chat("Reloaded MyTestPlugin");
    	}
    }
    
    void setupCitizens() {
    	// TODO: add source path: https://github.com/CitizensDev/Citizens2/tree/master/v1_8_R3/src/main/java
    	

		if(getServer().getPluginManager().getPlugin("Citizens") == null || getServer().getPluginManager().getPlugin("Citizens").isEnabled() == false) {
			getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
			getServer().getPluginManager().disablePlugin(this);	
			return;
		}	

		// Register traits with Citizens
		DiggerTrait.registerTrait();
    }
    
    void tearDownCitizens() {
    	DiggerTrait.ungisterTrait();
    }
    
   
    @Override
    public void onDisable() {
    	tearDownCitizens();
    	getLogger().info("bye bye!");
    }

//    @Override
//    public boolean onCommand(CommandSender sender,
//            Command command,
//            String label,
//            String[] args) {
//    	
//        
//    }
   
}