package dk.muj.derius.smithing;

import java.util.Optional;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqHasEnoughStamina;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.smithing.reciepts.Recipe;

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
		int size = this.getRecieptsAvailableTill(lvl).size();
		// Add in description to how many receipts could be found
		return Optional.of(Txt.parse("<i>You can find %s recipes and have a %s% chance in succeeding.", String.valueOf(size), "percantage"));
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
		Set<Recipe> recipes = this.getRecieptsAvailableTill(level);
		
		// Get chance for successful research by level and materials
		//	Is it successful? If yes, continue
		if ( ! this.isResearchSuccessful(level))
		{
			this.removeResearchItems();
			dplayer.msg("<b>You were not successful researching the receipt and lost your items being used.");
			return null;
		}
		
		// -------------------------------------------- //
		// Get the reciept
		// -------------------------------------------- //
		
		
		Recipe recipe = MUtil.random(recipes);
		ItemStack core = recipe.getCore();
		
		// Store the recipe id to the dplayers list
		recipe.storeFor(dplayer);
		
		// Hand out physical core-part
		player.getInventory().addItem(core);
		
		// Inform
		if ( ! recipe.isKnownto(dplayer))
		{
			dplayer.msg("<g>You now know the reciept %s!", recipe.getName());
		}
		else
		{
			dplayer.msg("<b>You researched a receipt that you were already aware of!");
			dplayer.msg("<i>The item-core might still be usefull for you though.");
		}
		
		this.removeResearchItems();
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other) { }
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	private boolean isResearchSuccessful(int level)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	private void removeResearchItems()
	{
		// TODO Auto-generated method stub
		
	}
	
	private Set<Recipe> getRecieptsAvailableTill(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
