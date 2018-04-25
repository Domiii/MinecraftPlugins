package npcs;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import mc.mytestplugin.MyTestPlugin;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitInfo;
import net.citizensnpcs.api.util.DataKey;

//@TraitName("digger")
/**
 * 
 * @see https://github.com/CitizensDev/CitizensAPI/tree/master/src/main/java/net/citizensnpcs/api/trait/Trait.java
 *
 */
public class DiggerTrait extends Trait {
	public static final String name = "digger";
	static TraitInfo info;
	public static void registerTrait() {
		info = net.citizensnpcs.api.trait.TraitInfo.create(DiggerTrait.class);
		CitizensAPI.getTraitFactory().registerTrait(info);
	}
	public static void ungisterTrait() {
		CitizensAPI.getTraitFactory().deregisterTrait(info);
	}
	public static NPC spawnOne(Player p) {
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
		npc.spawn(p.getLocation());
		npc.setProtected(true);
		
		npc.data().set("owner", p.getUniqueId().toString());
		
		// initialize our trait
		npc.addTrait(DiggerTrait.class);
		
//		JavaPlugin plugin = MyTestPlugin.instance;
//		plugin.getLogger().info("digger: " + npc.hasTrait(DiggerTrait.class));
		return npc;
	}
	
	
	public int tickDelay = 6;
	JavaPlugin plugin = null;
	public Player owner;
	BukkitTask digTask;

	public DiggerTrait() {
		super(name);
		plugin = MyTestPlugin.instance;
	}
	
	boolean init() {
		String uuid = npc.data().get("owner");
		owner = Bukkit.getPlayer(UUID.fromString(uuid));
		if (owner == null || !owner.isValid()) {
			npc.destroy();
			return false;
		}
		return true;
	}

	// An example event handler. All traits will be registered automatically as
	// Bukkit Listeners.
	@EventHandler	
	public void click(net.citizensnpcs.api.event.NPCClickEvent event) {
		// Handle a click on a NPC. The event has a getNPC() method.
		// Be sure to check event.getNPC() == this.getNPC() so you only handle clicks on
		// this NPC!

	}

	// Called every tick
	@Override
	public void run() {
		if (!owner.isValid()) {
			// we are gone if owner is gone
			npc.destroy();
		}
	}

	// Run code when your trait is attached to a NPC.
	// This is called BEFORE onSpawn, so npc.getBukkitEntity() will return null
	// This would be a good place to load configurable defaults for new NPCs.
	@Override
	public void onAttach() {
		plugin.getServer().getLogger().info(npc.getName() + "has been assigned MyTrait!");
	}

	// Run code when the NPC is despawned. This is called before the entity actually
	// despawns so npc.getBukkitEntity() is still valid.
	@Override
	public void onDespawn() {
		if (digTask != null) {
			digTask.cancel();
		}
	}

	// Run code when the NPC is spawned. Note that npc.getBukkitEntity() will be
	// null until this method is called.
	// This is called AFTER onAttach and AFTER Load when the server is started.
	@Override
	public void onSpawn() {
		if (init()) {
			this.npc.getNavigator().setTarget(owner.getLocation());
			dig();
		}
	}

	// run code when the NPC is removed. Use this to tear down any repeating tasks.
	@Override
	public void onRemove() {
	}


	// ##########################################################################################
	// Digging!
	// ##########################################################################################
	
	/**
	 * Based on https://www.spigotmc.org/threads/getting-actual-block-below-player.40191/
	 */
	void dig() {
        digTask = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable(){
            @Override
            public void run() {
        		Location l = npc.getEntity().getLocation();
                final Block standingOn = l.getBlock().getRelative(BlockFace.DOWN);
                Block b = l.getBlock();
                
                final double x = l.getX() - (double)l.getBlockX();
                final double z = l.getZ() - (double)l.getBlockZ();
                final Block b0 = standingOn.getLocation().getBlock();
                final Block b1 = standingOn.getLocation().add(1.0, 0.0, 0.0).getBlock();
                final Block b2 = standingOn.getLocation().add(1.0, 0.0, 1.0).getBlock();
                final Block b3 = standingOn.getLocation().add(0.0, 0.0, 1.0).getBlock();
                final Block b4 = standingOn.getLocation().add(-1.0, 0.0, 1.0).getBlock();
                final Block b5 = standingOn.getLocation().add(-1.0, 0.0, 0.0).getBlock();
                final Block b6 = standingOn.getLocation().add(-1.0, 0.0, -1.0).getBlock();
                final Block b7 = standingOn.getLocation().add(0.0, 0.0, -1.0).getBlock();
                final Block b8 = standingOn.getLocation().add(1.0, 0.0, -1.0).getBlock();
                
            	digBlock(b0);
            	
                if (x > 0.7) {
                	digBlock(b1);
                }
                if (x < 0.3) {
                	digBlock(b5);
                }
                if (z > 0.7) {
                	digBlock(b3);
                }
                if (z < 0.3) {
                    digBlock(b7);
                }
                //corners
                if (x > 0.7 && z > 0.7) {
                	digBlock(b2);
                }
                if (x < 0.3 && z > 0.7) {
                	digBlock(b4);
                }
                if (x > 0.7 && z < 0.3) {
                	digBlock(b8);
                }
                if (x < 0.3 && z < 0.3) {
                	digBlock(b6);
                }
            }
        }, tickDelay, tickDelay);
	}
	
	void digBlock(Block b) {
		if (b.getType() != Material.AIR) {
            b.setType(Material.AIR);
        }
	}
	
	
	// ##########################################################################################
	// Save/load
	// ##########################################################################################

	
//	boolean SomeSetting = false;
//	// see the 'Persistence API' section
//	@Persist("mysettingname")
//	boolean automaticallyPersistedSetting = false;
//
//	// Here you should load up any values you have previously saved (optional).
//	// This does NOT get called when applying the trait for the first time, only
//	// loading onto an existing npc at server start.
//	// This is called AFTER onAttach so you can load defaults in onAttach and they
//	// will be overridden here.
//	// This is called BEFORE onSpawn, npc.getBukkitEntity() will return null.
//	public void load(DataKey key) {
//		SomeSetting = key.getBoolean("SomeSetting", false);
//	}
//
//	// Save settings for this NPC (optional). These values will be persisted to the
//	// Citizens saves file
//	public void save(DataKey key) {
//		key.setBoolean("SomeSetting", SomeSetting);
//	}

}
