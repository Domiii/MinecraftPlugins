package mc.mytestplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

class Sucker extends BukkitRunnable {
	SuckCommand manager;
	CommandSender sender;
	Entity center;
	int iRepetition = 0;
	float suckSpeed;
	float radius;
	int maxRepetitions;
	
	public Sucker(SuckCommand manager, CommandSender sender, Entity center, float suckSpeed, float radius, int maxRepetitions) {
		this.manager = manager;
		this.sender = sender;
		this.center = center;
		this.suckSpeed = suckSpeed;
		this.radius = radius;
		this.maxRepetitions = maxRepetitions;
	}

	@Override
	public void run() {
		if (!center.isValid()) {
			// cancel this
			_cancel();
			return;
		}
		
    	++iRepetition;

		List<Entity> neighbors = center.getNearbyEntities(radius,radius,radius);
		for (Entity n : neighbors) {
			if (n == sender || !n.isValid()) continue;
			
			Vector pos = center.getLocation().toVector();
			Vector dist = pos.subtract(n.getLocation().toVector());
			if (dist.length() < suckSpeed * 5) {
				// close enough!
				continue;
			}
			
			Vector v = dist.normalize().multiply(suckSpeed);
			n.setVelocity(v);
		}
		
		if (center instanceof Player) {
			Player p = (Player)center;
			p.chat("suck " + iRepetition);	
		}
		
		if (iRepetition > maxRepetitions) {
			sender.sendMessage("Finished sucking up " + neighbors.size() + " entities!");
			_cancel();
		}
	}
	
	public void _cancel() {
		cancel();
		manager.onCancel(this);
	}
	
}

// fancy 吸吸
public class SuckCommand implements CommandExecutor {
	static float suckSpeed = 1.5f;
	static float nearbyRadius = 100;
	static int maxRepetitions = 10000;
	static int delayTicks = 1;
	
	List<Sucker> suckers;
	
	SuckCommand() {
		suckers = new ArrayList<Sucker>();
	}
	
	public void onCancel(Sucker s) {
		suckers.remove(s);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			sender.sendMessage("missing argument");
			return false;
		}
		
		String arg = args[0];
		
		switch (arg) {
		case "run":
		case "r": {

			if (!(sender instanceof Player)) {
				sender.sendMessage("players only");
				return false;
			}
			Player p = (Player)sender;
			World world = p.getWorld();
			Location location = p.getLocation();

			String arg1 = args.length > 1 ? args[1] : "";
			String arg2 = args.length > 2 ? args[2] : "";
			
			float n1 = 0;
			try {
				n1 = Float.parseFloat(arg1);
			}
			catch (Exception err) {}
			float r = n1 > 0 ? n1 : nearbyRadius;
			Sucker sucker = new Sucker(this, sender, p, suckSpeed, r, maxRepetitions);
			sucker.runTaskTimer(MyTestPlugin.instance, delayTicks, delayTicks);
			suckers.add(sucker);
			sender.sendMessage("started new sucker");
			return true;
		}
		case "stopall":
		case "s":{
			int n = suckers.size();
			for (int i = suckers.size()-1; i >= 0; --i) {
				Sucker s = suckers.get(i);
				s._cancel();
			}
			sender.sendMessage("stopped suckers: " + n);
			return true;
		}
		default: {
			sender.sendMessage("invalid command: " + arg);
			return false;
		}
		}
	}

}
