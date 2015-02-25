package dk.muj.derius.smithing;

import java.util.Optional;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.Ability;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.req.ReqHasEnoughStamina;

public class Improving extends DeriusAbility implements Ability
{
	private static Improving i = new Improving();
	public static Improving get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	public Improving()
	{
		// Ability properties
		this.setName("Improve");
		this.setDesc("Improve mundane crafts");
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
		// Add in description about how much chance there is for the item the be improved or not
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
		return "derius:smithing:improving";
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
		

		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// Nothing as far as I can think.
	}
}
