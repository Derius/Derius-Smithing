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
		if ( ! dplayer.isPlayer()) return Optional.empty();
		ItemStack inHand = dplayer.getPlayer().getItemInHand();
		Validate.notNull(inHand, "The improvable Item may not be null.");
		
		int durabilityLeft = ItemUtil.durabilityLeft(inHand);
		
		// How broken is the tool?
		// What Material class (iron, gold, ect) is the tool?
		
		// How many repair Materials in inventory?
		// 	How much can be repaired with that?
		
		// Chance that comes from levels
		
		
		
		// Repair
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// There will be none probably
	}
}
