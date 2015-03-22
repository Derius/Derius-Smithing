package dk.muj.derius.smithing;

import java.util.Optional;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.Couple;
import com.massivecraft.massivecore.util.InventoryUtil;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqHasEnoughStamina;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.api.util.SkillUtil;
import dk.muj.derius.lib.ItemUtil;

public class Repair extends AbilityAbstract<ItemStack>
{
	private static Repair i = new Repair();
	public static Repair get() { return i; }
	
	// -------------------------------------------- //
	// CONTRUCT & DESCRIPTION
	// -------------------------------------------- //
	
	private Repair()
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
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		return Optional.of("<i>There is a <h>" + String.valueOf(getPercent(lvl)) + "% <i>chance to repair a basic item./nThis starts at zero for every new item class you are able to repair.");
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
	public Object onActivate(DPlayer dplayer, ItemStack item)
	{
		// NULL check
		if ( ! dplayer.isPlayer()) return null;
		if (item == null) return null;
		
		// -------------------------------------------- //
		// Preparation and checks
		// -------------------------------------------- //
		
		Skill skill = getSkill();
		
		// Store data about the item
		int quality = ItemDamageUtil.getRepairQuality(item);
		Material type = item.getType();
		
		// Store data about the player
		int level = dplayer.getLvl(skill);
		Player player = dplayer.getPlayer();
		
		// What is the material to repair this item with?
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
			dplayer.msg("<b>The item was too weak and shattered into pieces.");
			return null;
		}
		
		// Is Player able to repair this Itemclass?
		if ( ! ItemDamageUtil.canWorkWithMaterial(dplayer, repairType))
		{
			dplayer.msg("<b>You are incapable of repairing this item.");
			return null;
		}
		
		// Is the repair successful?
		if ( ! this.isRepairSuccessful(level, type))
		{
			dplayer.msg("<b>You tried to repair the item, but failed.");
			ItemDamageUtil.reduceRepairQuality(item, 3, level, quality);
			ItemUtil.applyDamage(item, (short)(50 * Math.random()));
			return null;
		}
		
		// -------------------------------------------- //
		// Repair
		// -------------------------------------------- //
		
		// How many repairItems does the player have?
		int repairItemAmount = InventoryUtil.countSimilar(playerInventory, new ItemStack(repairType));
		
		// For how much is the quality lessend?
		ItemDamageUtil.reduceRepairQuality(item, level, repairItemAmount, quality);
		
		// How much durability is being restored?
		short reduce = this.getReducedDamage(level, item, repairType);
		Couple<ItemStack, Boolean> restore = ItemUtil.reduceDamage(item, reduce);
		
		// Send a Message, that the repair was successful
		dplayer.msg("<g>The item was %s", restore.getValue() ? "fully restored!" : Txt.parse("repaired by <lime>%s <g> durability.", reduce));
		
		// Make a shiny particle or so on the Player.
		player.playEffect(EntityEffect.VILLAGER_HAPPY);
		
		// Give exp for the item class
		if (SkillUtil.canPlayerLearnSkill(dplayer, skill, VerboseLevel.HIGHEST))
		{
			dplayer.addExp(skill, SmithingSkill.getExpGain().get(repairType));
		}
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other) {}
	
	// -------------------------------------------- //
	// PRIVATES
	// -------------------------------------------- //
	
	private boolean isRepairSuccessful(int level, Material type)
	{
		if (type != null)
		{
			level = level - SmithingSkill.getMinLevelToHandle(type);
		}
		
		return SkillUtil.shouldRandomOccure(level, SmithingSkill.getRepairDamagePercentPerLevels());
	}
	
	private short getReducedDamage(int level, ItemStack item, Material repairType)
	{
		short durabilityLeft = (short) (ItemUtil.maxDurability(item) - item.getDurability());
		
		return (short) (this.getPercent(level) * durabilityLeft);
	}
	
	private double getPercent(int level)
	{
		return level / SmithingSkill.getRepairDamagePercentPerLevels();
	}
}
