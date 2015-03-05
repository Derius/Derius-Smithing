package dk.muj.derius.smithing;

import java.util.List;
import java.util.Map;

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
	
	public static void reduceRepairQuality(ItemStack item, int baseTake, int level)
	{
		reduceRepairQuality(item, baseTake, level, getRepairQuality(item));
	}
	
	public static void reduceRepairQuality(ItemStack item, int baseTake, int level, int currentQuality)
	{
		double reducePercant = Math.min(level / SmithingSkill.getReduceDamageInRepairPerLevel(), SmithingSkill.getReduceDamageInRepairPercentMax()) / 100;
		int take = (int) Math.round(baseTake * (1.0 - reducePercant));
		
		setRepairQuality(item, currentQuality - take);
	}

	// -------------------------------------------- //
	// repairType
	// -------------------------------------------- //
	
	public static Material getRepairType(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null.");
		
		Material itemMaterial = item.getType();
		Map<Material, Material> retMap = SmithingSkill.getMaterialToRepairType();
		
		if ( ! retMap.containsKey(itemMaterial))
		{
			throw new IllegalArgumentException("DeriusSmithing does not currently provide a repairType for "
				+ Txt.getNicedEnum(itemMaterial) + " nag the authors at https://github.com/Derius/Derius-Smithing/issues");
		}
		
		return retMap.get(itemMaterial);
	}

	// -------------------------------------------- //
	// Booleans
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

}
