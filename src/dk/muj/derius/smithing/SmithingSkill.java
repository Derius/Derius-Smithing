package dk.muj.derius.smithing;

import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.skill.DeriusSkill;

public class SmithingSkill extends DeriusSkill implements Skill
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SmithingSkill i = new SmithingSkill();
	public static SmithingSkill get() { return i; }
	
	public SmithingSkill()
	{
		// Skill properties
		this.setName("Smithing");
		
		this.setDesc("Learns you how to.");
		
		this.addEarnExpDescs("Craft, repair or improve Armour and Items.");
		
		this.setIcon(Material.ANVIL);
		
		// Config
		this.writeConfig("repairQualityBreak", -5);
		this.writeConfig("minLevelToRepair", MUtil.map(
				Material.LEATHER, 20,
				Material.WOOD, 40,
				Material.IRON_FENCE, 30,
				Material.IRON_INGOT, 10,
				Material.GOLD_INGOT, 20,
				Material.DIAMOND, 40
				), new TypeToken<Map<Material, Integer>>(){});
		this.writeConfig("chancePerLevelAboveMin", 5.0);
		
	}
	
	@Override
	public String getId()
	{
		return "derius:smithing";
		
	}
	
	// -------------------------------------------- //
	// CONFIG GETTERS
	// -------------------------------------------- //
	
	public static int getRepairQualityBreak()
	{
		return get().readConfig("repairQualityBreak", Integer.class);
	}
	
	public static int getMinLevelToRepair(Material material)
	{
		return get().readConfig("minLevelToRepair", new TypeToken<Map<Material, Integer>>(){}).get(material);
	}
	
	public static double getChancePerLevelAboveMin()
	{
		return get().readConfig("chancePerLevelAboveMin", Double.class);
	}
	
	
}
