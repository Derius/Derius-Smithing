package dk.muj.derius.smithing.reciepts;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.massivecore.store.Entity;

import dk.muj.derius.api.player.DPlayer;

public class Recipe extends Entity<Recipe>
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	/*
	 * These fields address the recipe and how/when it can be researched.
	 */
	
	// Min-Level
	private int minLevel = 0;
	public int getMinLevel() { return this.minLevel; }
	public void setMinLevel(int minLevel) { this.minLevel = minLevel; }
	
	// List of materials, research items
	private List<Material> researchMaterials = new LinkedList<Material>();
	public List<Material> getResearchMaterials() { return this.researchMaterials; }
	public void setResearchMaterials(List<Material> researchMaterials) { this.researchMaterials = researchMaterials; }
	public void addResearchMaterials(Material material) { this.getResearchMaterials().add(material); }
	
	/*
	 * RESULT ITEM:
	 * These are the fields which construct the itemstack which is given to the user
	 * if he succeeds to craft this recipe.
	 */
	
	// Name
	private String name = "";
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	private ChatColor nameColor = null;
	public ChatColor getNameColor() { return this.nameColor; }
	public void setNameColor(ChatColor nameColor) { this.nameColor = nameColor; }
	
	// Weapon lore prefix (Spear/War-axe/Hammer etc. )
	private WeaponType type = null;
	public WeaponType getType() { return this.type; }
	public void setType(WeaponType type) { this.type = type; }
	
	private ChatColor typeColor = null;
	public ChatColor getTypeolor() { return this.typeColor; }
	public void setTypeColor(ChatColor typeColor) { this.typeColor = typeColor; }
	
	// Enchants
	private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
	public Map<Enchantment, Integer> getEnchantments() { return this.enchantments; }
	public void setEnchantments(Map<Enchantment, Integer> enchantments) { this.enchantments = enchantments; }
	public void addEnchantment(Enchantment ench, int level) { this.getEnchantments().put(ench, Integer.valueOf(level)); }
	
	private List<String> lore = new LinkedList<String>();
	public List<String> getLore() { return this.lore; }
	public void setLore(List<String> lore) { this.lore = lore; }
	public void addLore(String lore) { this.getLore().add(lore); }
	
	// Special properties
	// TODO: Do we want special enchants and that sort of stuff?
	
	/*
	 * CORE ITEM:
	 * These are the fields which construct the itemstack which is given to the user
	 * if he succeeds to research the recipe. He will need this to craft the item.
	 */
	
	private Material coreItem = Material.MAP;
	public Material getCoreItem() { return this.coreItem; }
	public void setCoreItem(Material coreItem) { this.coreItem = coreItem; }
	
	// Name
	private String coreName = "";
	public String getCoreName() { return this.coreName; }
	public void setCoreName(String coreName) { this.coreName = name; }
	
	private ChatColor coreNameColor = null;
	public ChatColor getCoreNameColor() { return this.coreNameColor; }
	public void setCoreNameColor(ChatColor coreNameColor) { this.coreNameColor = coreNameColor; }
	
	private List<String>  coreLore = new LinkedList<String>();
	public List<String> getCoreLore() { return this.coreLore; }
	public void setCoreLore(List<String> coreLore) { this.coreLore = coreLore; }
	public void addCoreLore(String coreLore) { this.getLore().add(coreLore); }

	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	public ItemStack getCore()
	{
		if (this.getCoreItem() == null) throw new UnsupportedOperationException("Can't create an item with Material null");
		ItemStack core = new ItemStack(this.getCoreItem());
		
		ItemMeta meta = core.getItemMeta();
		meta.setDisplayName(this.getCoreNameColor().toString() + this.getCoreName());
		meta.setLore(this.getCoreLore());
		
		core.setItemMeta(meta);
		
		return core;
	}
	
	public void storeFor(DPlayer dplayer)
	{
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKnownto(DPlayer dplayer)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
