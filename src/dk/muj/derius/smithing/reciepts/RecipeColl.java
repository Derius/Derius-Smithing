package dk.muj.derius.smithing.reciepts;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;

import dk.muj.derius.smithing.Const;
import dk.muj.derius.smithing.DeriusSmithing;

public class RecipeColl extends Coll<Recipe>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static RecipeColl i = new RecipeColl();
	public static RecipeColl get() { return i; }
	private RecipeColl()
	{
		super(Const.RECIPE_COLLECTION, Recipe.class, MStore.getDb(), DeriusSmithing.get());
		this.setLowercasing(true);
		this.setCreative(false);
	}

	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
	}
	
}
