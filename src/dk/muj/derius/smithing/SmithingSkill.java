package dk.muj.derius.smithing;

import org.bukkit.Material;

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
		
		this.addEarnExpDescs("Manufacture, repair or improve Armour and Items.");
		
		this.setIcon(Material.ANVIL);
		
		// Config
		this.writeConfig("repairQualityBreak", -5);
		
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
	
}
