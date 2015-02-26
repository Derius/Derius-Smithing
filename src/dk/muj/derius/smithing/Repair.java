package dk.muj.derius.smithing;

import java.util.Optional;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

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
		this.setTicksCooldown(20*10);
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
		ItemStack inHand = dplayer.getPlayer().getItemInHand();
		if (inHand == null) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		// How broken is the tool?
		int durabilityLeft = ItemUtil.durabilityLeft(inHand);
		

		// Was the tool repaired before? (Mstore Coll?)
		//	if so, how many repair scars does it have?
		//	does it exceed the maximum repair scars per item?
		
		// What Material class (iron, gold, ect) is the tool?
		//	Thus, what material is used to repair it (configurable)?
		//	How many repair Materials in inventory? Atleast one?
		
		// Is Player able to repair this Itemclass?
		
		// -------------------------------------------- //
		// Repair
		// -------------------------------------------- //
		
		// How much can be repaired with the amount of materials?
		
		// How many scars are applied to the item?
		// How much durability is being restored?
		
		// Send a Message, that the repair was succesfull (Actionbar?)
		// Make a shiny particle or so on the Player.
		
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// There will be none probably
	}
}
