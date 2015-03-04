package dk.muj.derius.smithing;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.util.SkillUtil;

public class ItemDamageUtil
{
	public static String QUALITYLORE = Txt.parse("<i>Quality: ");
	
	// -------------------------------------------- //
	// RepairQuality
	// -------------------------------------------- //
	
	public static int getRepairQuality(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null to get the repairquality.");
		
		List<String> loreList = item.getItemMeta().getLore();
		String qualityLore = null;
		
		// Get the qualityLore string
		for (int x = 0; x < loreList.size(); x++)
		{
			String lore = loreList.get(x);
			
			if ( ! lore.startsWith(QUALITYLORE)) continue;
			
			qualityLore = lore;
			break;
		}
		
		// Getting the quality
		int repairQuality;
		if ( qualityLore == null)
		{
			repairQuality = 0;
			loreList.add(0, QUALITYLORE + repairQuality);
			item.getItemMeta().setLore(loreList);
		}
		else
		{
			String stringNumber = qualityLore.substring(QUALITYLORE.length(), qualityLore.length());
			repairQuality = Integer.parseInt(stringNumber);
		}
		
		return repairQuality;
	}
	
	public static void setRepairQuality(ItemStack item, int endQuality)
	{
		Validate.notNull(item, "The item may not be null to get the repairquality.");
		
		List<String> loreList = item.getItemMeta().getLore();
		int position = 0;
		
		// Get the qualityLore position
		for (int x = 0; x < loreList.size(); x++)
		{
			String lore = loreList.get(x);
			
			if ( ! lore.startsWith(QUALITYLORE)) continue;
			
			// Are there more than one Quality lores? delete them
			if (position != 0)
			{
				loreList.remove(position);
				continue;
			}
				
			position = x;
		}
		
		// Set the lore at correct position
		loreList.set(position, QUALITYLORE + endQuality);
		item.getItemMeta().setLore(loreList);
	}

	// -------------------------------------------- //
	// repairType
	// -------------------------------------------- //
	
	public static Material getRepairType(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null.");
		
		Material ret;
		Material itemMaterial = item.getType();
		
		// Talk with Madus about it
		return null;
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	public static boolean willBreakNextRepair(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null.");
		
		return getRepairQuality(item) <= SmithingSkill.getRepairQualityBreak();
	}
	
	public static boolean willBreakNextRepair(int quality)
	{
		return quality <= SmithingSkill.getRepairQualityBreak();
	}

	public static boolean canWorkWithMaterial(DPlayer dplayer, Material type)
	{
		if ( ! dplayer.isPlayer()) throw new RuntimeException("dplayer must be a player");
		Validate.notNull(type, "The material may not be null.");
		
		int level = dplayer.getLvl(SmithingSkill.get());
		int minLevel = SmithingSkill.getMinLevelToRepair(type);
		
		return level >= minLevel;
	}

	public static boolean isRepairSuccessful(DPlayer dplayer, Material type)
	{
		if ( ! dplayer.isPlayer()) throw new RuntimeException("dplayer must be a player");
		Validate.notNull(type, "The material may not be null.");
		
		int level = dplayer.getLvl(SmithingSkill.get());
		int levelAbove = level - SmithingSkill.getMinLevelToRepair(type);
		
		return SkillUtil.shouldDoubleDropOccur(levelAbove, (int) Math.round(SmithingSkill.getChancePerLevelAboveMin()));
		
	}

	public static void reduceItemQuality(ItemStack item, int baseTake, int level)
	{
		reducePercant
	}

}
