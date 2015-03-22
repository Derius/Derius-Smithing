package dk.muj.derius.smithing;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.SkillUtil;
import dk.muj.derius.lib.ItemUtil;

public class EngineSmithing extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //

	private static EngineSmithing i = new EngineSmithing();
	public static EngineSmithing get() { return i; }
	public EngineSmithing() { }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return DeriusSmithing.get();
	}
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void QualityIteamBreak(EntityDamageByEntityEvent event)
	{
		if ( ! (event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		DPlayer dplayer = DeriusAPI.getDPlayer(player);
		
		ItemStack inHand = player.getItemInHand();
		boolean wouldBreak = ItemDamageUtil.willBreakNextRepair(inHand);
		
		if ( ! wouldBreak) return;
		
		if ( ! SkillUtil.shouldRandomOccure(dplayer.getLvl(SmithingSkill.get()), 100)) return;
		
		ItemUtil.breakItem(inHand);
		
	}

}
