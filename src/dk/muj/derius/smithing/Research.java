package dk.muj.derius.smithing;

import dk.muj.derius.api.Ability;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.req.ReqHasEnoughStamina;

public class Research extends DeriusAbility implements Ability
{
	private static Research i = new Research();
	public static Research get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	public Research()
	{
		// Ability properties
		this.setName("Research");
		this.setDesc("Research a new reciept.");
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
		//	Is it succesfull? If yes, continue
		
		// -------------------------------------------- //
		// Get the reciept
		// -------------------------------------------- //
		
		// Get the phisical reciept/component
		
		// Store the reciept id to the dplayers list
		
		// Msg the player that he now knows a new reciept
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// TODO Auto-generated method stub

	}
}
