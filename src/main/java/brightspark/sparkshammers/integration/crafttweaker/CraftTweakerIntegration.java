package brightspark.sparkshammers.integration.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import net.minecraftforge.fml.common.Optional;

public class CraftTweakerIntegration {
	
	public static void init() {
		CraftTweakerAPI.registerClass(CTHammerCraftingTableRecipes.class);
	}

}
