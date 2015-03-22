package dk.muj.derius.smithing;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqHasEnoughStamina;
import dk.muj.derius.api.skill.Skill;

public class Improving extends AbilityAbstract<ItemStack>
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
		this.setCooldownMillis(1000*5);
		this.setStaminaUsage(50.0);
		
		// Requirements
		this.addActivateRequirements(ReqHasEnoughStamina.get());
	}
	
	@Override
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		// Add in description about how much chance there is for the item the be improved or not
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
		return "derius:smithing:improving";
	}

	// -------------------------------------------- //
	// ABILITY ACTIVATION
	// -------------------------------------------- //
	
	@Override
	public Object onActivate(DPlayer dplayer, ItemStack item)
	{
		// NULL check
		if ( ! dplayer.isPlayer()) return null;
		ItemStack inHand = dplayer.getPlayer().getItemInHand();
		if (inHand == null) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		// How many Enchants does the item have?
		//	Does it exceed the maximum amount (configurabel)?
		
		// How broken is the tool? Including repair scars?
		//	Can it still be improved (configurabel)?
		//	Or should we let them go foreward and break it in the next step @Madus?
		
		// What is the Material class of the Item.
		//	Can this material class be improved?
		
		// (Chance) Will the item be improved or demolished?
		//	Take Level, material class and scars into consideration?
		
		// -------------------------------------------- //
		// Improve
		// -------------------------------------------- //
		
		// Calculate the Level of the enchantment
		
		// Calculate what enchant it will be (more or less random?)
		
		// Chance that enchantment disapers again after the ability has run out (so cruel.)
		
		// Mark item as instable and pass it over into onDeactivate.
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		// If chance lets it disapear, give exp and remove entchant
	}
}
