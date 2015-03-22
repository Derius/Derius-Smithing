package dk.muj.derius.smithing;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqHasEnoughStamina;
import dk.muj.derius.api.skill.Skill;

public class Research extends AbilityAbstract<ItemStack>
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
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		// Add in description to how many receipts could be found
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
		return "derius:smithing:research";
	}

	// -------------------------------------------- //
	// ABILITY ACTIVATION
	// -------------------------------------------- //
	
	@Override
	public Object onActivate(DPlayer dplayer, ItemStack item)
	{
		// NULL check
		if ( ! dplayer.isPlayer()) return null;
		if (item == null) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		Skill skill = getSkill();
		
		// Store data about the player
		int level = dplayer.getLvl(skill);
		Player player = dplayer.getPlayer();
		
		// Get all receipts available till this level (for loop)
		// and save them in a set
		Set<Receipts> receipts = ReceiptUtil.getRecieptsAvailableTill(level);
		
		// Get chance for successfull research by level and materials
		//	Is it successful? If yes, continue
		if ( ! this.isResearchSuccessful(level))
		{
			ReceiptUtil.removeResearchItems();
			dplayer.msg("<b>You were not successful researching the receipt and lost your items being used.");
			return null;
		}
		
		// -------------------------------------------- //
		// Get the reciept
		// -------------------------------------------- //
		
		// Get the phisical reciept/component
		ReceiptUtil.getRandomReciept(receipts);
		
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
