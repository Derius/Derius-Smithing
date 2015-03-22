package dk.muj.derius.smithing;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqHasEnoughStamina;
import dk.muj.derius.api.skill.Skill;

public class Craft extends AbilityAbstract<ItemStack>
{
	private static Craft i = new Craft();
	public static Craft get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	public Craft()
	{
		// Ability properties
		this.setName("Craft");
		this.setDesc("Craft a new item with a reciept.");
		this.setType(AbilityType.ACTIVE);
		
		// Exhaustion
		this.setStaminaUsage(50.0);
		this.setCooldownMillis(1000*5);
		
		// Requirements
		this.addActivateRequirements(ReqHasEnoughStamina.get());
	}
	
	@Override
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		// Add in description to how many reciepts could be found
		return Optional.of("");
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
		return "derius:smithing:craft";
	}

	// -------------------------------------------- //
	// ABILITY ACTIVATION
	// -------------------------------------------- //
	
	@Override
	public Object onActivate(DPlayer dplayer, ItemStack item)
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
