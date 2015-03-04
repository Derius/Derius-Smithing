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
	
	public static int getRepairQuality(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null to get the repairquality.");
		
		List<String> loreList = item.getItemMeta().getLore();
		
		for (String lore : loreList)
		{
			
		}
		
		String qualityLore = loreList.get(0);
		int repairQuality;
		
		if ( ! qualityLore.startsWith(QUALITYLORE))
		{
			repairQuality = 0;
			loreList.add(0, QUALITYLORE + repairQuality);
			item.getItemMeta().setLore(loreList);
		}
		else
		{
			String stringNumber = (String) qualityLore.subSequence(QUALITYLORE.length(), qualityLore.length());
			repairQuality = Integer.parseInt(stringNumber);
		}
		
		return repairQuality;
	}
	
	public static void setRepairQuality(ItemStack item, int endQuality)
	{
		Validate.notNull(item, "The item may not be null to get the repairquality.");
		
		List<String> loreList = item.getItemMeta().getLore();
		String qualityLore = loreList.get(0);
		
		if (qualityLore.startsWith(QUALITYLORE))
		{
			loreList.remove(0);
		}
		
		loreList.add(0, QUALITYLORE + endQuality);
		item.getItemMeta().setLore(loreList);
	}

	public static Material getRepairType(ItemStack item)
	{
		Validate.notNull(item, "The item may not be null.");
		
		Material ret;
		Material itemMaterial = item.getType();
		
		// Talk with Madus about it
		return null;
	}

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

	public static void reduceItemQuality(ItemStack item, int i, int level)
	{
		
	}

}
