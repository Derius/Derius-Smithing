package dk.muj.derius.smithing;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Ability;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.lib.ItemUtil;
import dk.muj.derius.req.ReqHasEnoughStamina;

public class Repair extends DeriusAbility implements Ability
{
	private static Repair i = new Repair();
	public static Repair get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	public Repair()
	{
		// Ability properties
		this.setName("Repair");
		this.setDesc("Repair almost broken crafts.");
		this.setType(AbilityType.ACTIVE);
		
		// Exhaustion
		this.setCooldownMillis(1000*5);
		this.setStaminaUsage(50.0);
		
		// Requirements
		this.addActivateRequirements(ReqHasEnoughStamina.get());
	}
	
	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		// Add in description about how much chance there is for the item the be repaired a bit
		return "";
	}
	
	// -------------------------------------------- //
	// SKILL & ID
	// -------------------------------------------- //
	
	@Override
	public Skill getSkill()
	{
		return SmithingSkill.get();
	}

	@Override
	public String getId()
	{
		return "derius:smithing:repair";
	}

	// -------------------------------------------- //
	// ABILITY ACTIVATION
	// -------------------------------------------- //
	
	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		// NULL check
		if ( ! dplayer.isPlayer()) return null;
		ItemStack item = dplayer.getPlayer().getItemInHand();
		if (item == null) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		// Store data about the item
		int durabilityLeft = ItemUtil.durabilityLeft(item);
		int quality = ItemDamageUtil.getRepairQuality(item);
		Material type = item.getType();
		
		// Store data about the player
		int level = dplayer.getLvl(getSkill());
		Player player = dplayer.getPlayer();
		
		// What Material class (iron, gold, ect) is the tool?
		//	Thus, what material is used to repair it (configurable)?
		//	How many repair Materials in inventory? Atleast one?
		
		Material repairType = ItemDamageUtil.getRepairType(item);
		Inventory playerInventory = player.getInventory();
		boolean hasRepairType = playerInventory.contains(repairType);
		
		if ( ! hasRepairType)
		{
			dplayer.msg("<b>You will need some %s <b>to repair this item.", Txt.getNicedEnum(repairType));
			return null;
		}
		
		//	Does the repairQuality undercut the minimum amount?
		if (ItemDamageUtil.willBreakNextRepair(quality))
		{
			ItemUtil.breakItem(item);
			dplayer.msg("<b>The item was too weak and shattered into pieces.")
			return null;
		}
		
		// Is Player able to repair this Itemclass?
		if ( ! ItemDamageUtil.canWorkWithMaterial(dplayer, repairType))
		{
			dplayer.msg("<b>You are incapable of repairing this item.");
			return null;
		}
		
		if ( ! ItemDamageUtil.isRepairSuccessful(dplayer, type))
		{
			dplayer.msg("<b>You tried to repair the item, but failed.");
			ItemDamageUtil.reduceItemQuality(item, 3, level);
			ItemUtil.applyDamage(item, (short)(50 * Math.random()));
			return null;
		}
		
		// -------------------------------------------- //
		// Repair
		// -------------------------------------------- //
		
		// How many repairItems does the player have?
		int repairItemAmount;
		for(ItemStack stack : playerInventory.getContents())
		{
			if (stack.getType() == repairType)
			{
				repairItemAmount += stack.getAmount();
			}
		}
		
		// How many scars are applied to the item?
		ItemDamageUtil.reduceItemQuality(item, level);
		
		// How much durability is being restored?
		short reduce = ItemDamageUtil.getReducedDamage(level, repairType);
		boolean restored = ItemUtil.reduceDamage(item, reduce);
		
		// Send a Message, that the repair was successful
		dplayer.msg("<g>The item was %s", restored ? "fully restored!" : Txt.parse("repaired by <lime>%s <g> points.", reduce));
		
		// Make a shiny particle or so on the Player.
		player.playEffect(EntityEffect.VILLAGER_HAPPY);
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// There will be none probably
	}
}
