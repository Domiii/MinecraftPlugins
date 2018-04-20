package mc.myfirstplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
 
    @Override
    public void onEnable() {
       
    }
   
    @Override
    public void onDisable() {
       
    }

    @Override
    public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
    	
    	sender.sendMessage("You ran " + command.getName() + "!");
        if (command.getName().equalsIgnoreCase("testtest")) {
            sender.sendMessage("very good");
            return true;
        }
        return false;
        
    }
   
}