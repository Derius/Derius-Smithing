package dk.muj.derius.smithing;

import com.massivecraft.massivecore.MassivePlugin;

public class DeriusSmithing extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DeriusSmithing i;
	public static DeriusSmithing get() { return i; }
	public DeriusSmithing() { i = this; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! super.preEnable()) return;
			
		SmithingSkill.get().register();
		Manufacture.get().register();
		Repair.get().register();
		Improving.get().register();
		
		super.postEnable();
	}
}
