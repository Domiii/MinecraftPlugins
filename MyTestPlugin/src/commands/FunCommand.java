package commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import npcs.DiggerTrait;

import org.bukkit.command.CommandExecutor;

public class FunCommand implements CommandExecutor {
	static float flySpeed = 1200;
	static float nearbyRadius = 100;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("players only");
			return false;
		}
		if (args.length < 1) {
			sender.sendMessage("missing argument");
			return false;
		}
		
		Player p = (Player)sender;
		World world = p.getWorld();
		Location location = p.getLocation();

		String arg = args[0];
		String arg1 = args.length > 1 ? args[1] : "";
		float n1 = 0;
		try {
			n1 = Float.parseFloat(arg1);
		}
		catch (Exception err) {}
		String arg2 = args.length > 2 ? args[2] : "";
		switch (arg) {
		case "a": {
			// wheeeee
			Vector v = location.getDirection();
			v.add(new Vector(0,0.2f,0));
			p.setVelocity(v.normalize().multiply(flySpeed));
			//p.setFlying(false);
			p.chat("wheeeeeeeeeEEEEEEeeeee");
			return true;
		}
		case "b": {
			// 產生
			if (n1 < 1) {
				n1 = 1;
			}
			for (int i = 0; i < n1; ++i) {
				Villager v = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
			}
			sender.sendMessage("spawned " + n1 + " villagers");
			return true;
		}
		case "c": {
			// 推
			float r = n1 > 0 ? n1 : nearbyRadius;
			List<Entity> neighbors = p.getNearbyEntities(r,r,r);
			for (Entity n : neighbors) {
				if (n == p) continue;
				Vector theirPos = n.getLocation().toVector();
				Vector v = theirPos.subtract(location.toVector()).normalize();
				if (v.getY() < 0) {
					v.setY(-v.getY());
				}
				v = v.add(new Vector(0, 0.05f, 0)).normalize();
				n.setVelocity(v.multiply(10));
			}
			sender.sendMessage("Sent " + neighbors.size() + " entities flying!");
			return true;
		}
		case "d": {
			// 吸吸
			float r = n1 > 0 ? n1 : nearbyRadius;
			List<Entity> neighbors = p.getNearbyEntities(r,r,r);
			for (Entity n : neighbors) {
				if (n == p) continue;
				n.teleport(location);
			}
			sender.sendMessage("Sucked up " + neighbors.size() + " entities");
			return true;
		}
		case "k": {
			// 殺
			float r = n1 > 0 ? n1 : nearbyRadius;
			List<Entity> neighbors = p.getNearbyEntities(r,r,r);
			for (Entity n : neighbors) {
				if (n == p) continue;
				n.remove();
			}
			sender.sendMessage("Killed " + neighbors.size() + " entities");
			return true;
		}
		case "dig": {
			DiggerTrait.spawnOne(p);
		}
		case "e": {
		}
		}
		return false;
	}

}
