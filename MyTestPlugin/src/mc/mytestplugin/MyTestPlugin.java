package mc.mytestplugin;

import java.util.logging.Handler;
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

//plugman reload MyTestPlugin

public class MyTestPlugin extends JavaPlugin {
	public static MyTestPlugin instance;
	
	void registerThis() {
		instance = this;
		getCommand("testtest").setExecutor(new CustomOpCommand());
		getCommand("runasplayer").setExecutor(new PlayerCommanderCommand());
		getCommand("fun").setExecutor(new FunCommand());
		getCommand("suck").setExecutor(new SuckCommand());
	}
 
    @Override
    public void onEnable() {
    	registerThis();
    	
    	getLogger().info("hi 3!");
    	for (Player p : Bukkit.getOnlinePlayers()) {
    		p.chat("Reloaded MyTestPlugin");
    	}
    }
    
   
    @Override
    public void onDisable() {
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