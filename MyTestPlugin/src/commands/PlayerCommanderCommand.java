package commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

public class PlayerCommanderCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//MyTestPlugin.instance.getLogger().info(String.join(",", args));
		String playerName = args[0];
		String cmdString = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
		
		Player player = Bukkit.getPlayer(playerName);
		if (player != null) {
			Bukkit.dispatchCommand(player, cmdString);
			sender.sendMessage("Done: " + cmdString);
			return true;
		}	
		sender.sendMessage("Could not find player: " + playerName);
        return false;
	}
}
