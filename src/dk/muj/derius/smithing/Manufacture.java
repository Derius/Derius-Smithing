package dk.muj.derius.smithing;

import dk.muj.derius.api.Ability;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.req.ReqHasEnoughStamina;

public class Manufacture extends DeriusAbility implements Ability
{
	private static Manufacture i = new Manufacture();
	public static Manufacture get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	public Manufacture()
	{
		// Ability properties
		this.setName("Manufacture");
		this.setDesc("Try out a new reciept.");
		this.setType(AbilityType.ACTIVE);
		
		// Exhaustion
		this.setTicksCooldown(20*60*5);
		this.setStaminaUsage(50.0);
		
		// Requirements
		this.addActivateRequirements(ReqHasEnoughStamina.get());
	}
	
	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		// Add in description to how many reciepts could be found
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
		return "derius:smithing:manufacture";
	}

	// -------------------------------------------- //
	// ABILITY ACTIVATION
	// -------------------------------------------- //
	
	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		// NULL check
		if ( ! dplayer.isPlayer()) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		// Save players level in skill
		
		// Get all reciepts available till this level (for loop)
		// and save them in a set
		
		// Get chance for successfull research by level and materials
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// TODO Auto-generated method stub

	}

}
