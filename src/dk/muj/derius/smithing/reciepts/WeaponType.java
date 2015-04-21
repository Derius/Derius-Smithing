package dk.muj.derius.smithing.reciepts;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.util.Txt;

public enum WeaponType
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	SHORT_SWORD("Short Sword"),
	LONG_SWORD("Long Sword"),
	SPEAR("Spear"),
	WAR_AXE("War axe"),
	HAMMER("Hammer"),
	GLEPHE("Glephe"),
	// TODO Add more as we wish
	
	
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private String lore;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	WeaponType(String lore)
	{
		this.lore = lore;
	}
	
	// -------------------------------------------- //
	// UTIl
	// -------------------------------------------- //
	
	public String getLore()
	{
		return getLoreWithColor(ChatColor.YELLOW);
	}
	
	public String getLoreWithColor(ChatColor color)
	{
		return Txt.parse("%s[%s]", color.toString(), this.lore);
	}
	
}
