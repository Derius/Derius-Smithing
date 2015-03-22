package dk.muj.derius.smithing;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import dk.muj.derius.api.skill.SkillAbstract;

public class SmithingSkill extends SkillAbstract
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
		
		this.addEarnExpDescs("Craft, repair or improve Armor and Items.");
		
		this.setIcon(Material.ANVIL);
		
		// Config
		this.writeConfig(Const.JSON_REPAIR_QUALITY_BREAK, -5);
		this.writeConfig(Const.JSON_REPAIR_DAMAGE_LEVELS_PER_PERCENT, 50);

		this.writeConfig(Const.JSON_REDUCE_QUALITY_LOSS_PERCENT_PER_LEVELS, 0.01);
		this.writeConfig(Const.JSON_REDUCE_QUALITY_LOSS_PERCENT_MAX, 33.33);
		
		this.writeConfig(Const.JSON_MIN_LEVEL_TO_HANDLE, MUtil.map(
				Material.LEATHER, 0,
				Material.WOOD, 0,
				Material.IRON_FENCE, 100,
				Material.IRON_INGOT, 300,
				Material.GOLD_INGOT, 400,
				Material.DIAMOND, 500
				), new TypeToken<Map<Material, Integer>>(){});
		
		this.writeConfig(Const.JSON_MATERIAL_TO_REPAIRYPE, MUtil.map(
				
				// Leather Items				
				Material.LEATHER_HELMET,		Material.LEATHER,
				Material.LEATHER_CHESTPLATE,	Material.LEATHER,
				Material.LEATHER_LEGGINGS,		Material.LEATHER,
				Material.LEATHER_BOOTS,			Material.LEATHER,
				
				// Wood Items
				Material.WOOD_SWORD,			Material.WOOD,
				Material.WOOD_AXE,				Material.WOOD,
				Material.WOOD_SPADE,			Material.WOOD,
				Material.WOOD_PICKAXE,			Material.WOOD,
				Material.WOOD_HOE,				Material.WOOD,
				
				// Gold Items
				Material.GOLD_HELMET,			Material.GOLD_INGOT,
				Material.GOLD_CHESTPLATE,		Material.GOLD_INGOT,
				Material.GOLD_LEGGINGS,			Material.GOLD_INGOT,
				Material.GOLD_BOOTS,			Material.GOLD_INGOT,
				Material.GOLD_SWORD,			Material.GOLD_INGOT,
				Material.GOLD_AXE,				Material.GOLD_INGOT,
				Material.GOLD_SPADE,			Material.GOLD_INGOT,
				Material.GOLD_PICKAXE,			Material.GOLD_INGOT,
				Material.GOLD_HOE,				Material.GOLD_INGOT,
				
				// Chainmail Items
				Material.CHAINMAIL_HELMET,		Material.IRON_FENCE,
				Material.CHAINMAIL_CHESTPLATE,	Material.IRON_FENCE,
				Material.CHAINMAIL_LEGGINGS,	Material.IRON_FENCE,
				Material.CHAINMAIL_BOOTS,		Material.IRON_FENCE,
				
				// Stone Items
				Material.STONE_SWORD,			Material.STONE,
				Material.STONE_AXE,				Material.STONE,
				Material.STONE_SPADE,			Material.STONE,
				Material.STONE_PICKAXE,			Material.STONE,
				Material.STONE_HOE,				Material.STONE,
				
				// Iron Items
				Material.IRON_HELMET,			Material.IRON_INGOT,
				Material.IRON_CHESTPLATE,		Material.IRON_INGOT,
				Material.IRON_LEGGINGS,			Material.IRON_INGOT,
				Material.IRON_BOOTS,			Material.IRON_INGOT,
				Material.IRON_SWORD,			Material.IRON_INGOT,
				Material.IRON_AXE,				Material.IRON_INGOT,
				Material.IRON_SPADE,			Material.IRON_INGOT,
				Material.IRON_PICKAXE,			Material.IRON_INGOT,
				Material.IRON_HOE,				Material.IRON_INGOT,
				
				// Diamond Items
				Material.DIAMOND_HELMET,		Material.DIAMOND,
				Material.DIAMOND_CHESTPLATE,	Material.DIAMOND,
				Material.DIAMOND_LEGGINGS,		Material.DIAMOND,
				Material.DIAMOND_BOOTS,			Material.DIAMOND,
				Material.DIAMOND_SWORD,			Material.DIAMOND,
				Material.DIAMOND_AXE,			Material.DIAMOND,
				Material.DIAMOND_SPADE,			Material.DIAMOND,
				Material.DIAMOND_PICKAXE,		Material.DIAMOND,
				Material.DIAMOND_HOE,			Material.DIAMOND,

				// Misc Items
				Material.CARROT_STICK,			Material.STICK,
				Material.SHEARS,				Material.IRON_INGOT,
				Material.FLINT_AND_STEEL,		Material.IRON_INGOT,
				Material.FISHING_ROD,			Material.STICK,
				Material.BOW,					Material.STRING
				), new TypeToken<Map<Material, Material>>(){});
				
		this.writeConfig(Const.JSON_EXP_GAIN, MUtil.map(
				Material.LEATHER, 10.0,
				Material.WOOD, 10.0,
				Material.IRON_FENCE, 20.0,
				Material.IRON_INGOT, 30.0,
				Material.GOLD_INGOT, 40.0,
				Material.DIAMOND, 50.0
				), new TypeToken<Map<Material, Double>>(){});
	}
	
	@Override
	public String getId()
	{
		return "derius:smithing";
		
	}
	
	@Override
	public Plugin getPlugin()
	{
		return DeriusSmithing.get();
	}

	// -------------------------------------------- //
	// CONFIG GETTERS
	// -------------------------------------------- //
	
	public static int getRepairQualityBreak()
	{
		return get().readConfig(Const.JSON_REPAIR_QUALITY_BREAK, Integer.class);
	}
	
	public static int getMinLevelToHandle(Material material)
	{
		return get().readConfig(Const.JSON_MIN_LEVEL_TO_HANDLE, new TypeToken<Map<Material, Integer>>(){}).get(material);
	}
	
	public static Map<Material, Material> getMaterialToRepairType()
	{
		return get().readConfig(Const.JSON_MATERIAL_TO_REPAIRYPE, new TypeToken<Map<Material, Material>>(){});
	}
	
	public static double getReduceQualityLossPercentPerLevel()
	{
		return get().readConfig(Const.JSON_REDUCE_QUALITY_LOSS_PERCENT_PER_LEVELS, Double.class);
	}

	public static double getReduceQualityLossPercentMax()
	{
		return get().readConfig(Const.JSON_REDUCE_QUALITY_LOSS_PERCENT_MAX, Double.class);
	}

	public static int getRepairDamagePercentPerLevels()
	{
		return get().readConfig(Const.JSON_REPAIR_DAMAGE_LEVELS_PER_PERCENT, Integer.class);
	}
	
	public static Map<Material, Double> getExpGain()
	{
		return get().readConfig(Const.JSON_EXP_GAIN, new TypeToken<Map<Material, Double>>(){});
	}
}
