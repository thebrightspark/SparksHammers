package brightspark.sparkshammers.integration.crafttweaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.sparkshammers.HammerCrafting")
public class CTHammerCraftingTableRecipes {

	public static class HammerCraftingAction extends AddRemoveAction {

		public HammerCraftingAction(ItemStack output, List<ItemStack> input) {
			this.output = output;
			this.input = input;
		}

		ItemStack output;

		List<ItemStack> input;

		/**
		 * Adds a recipe. Due to
		 * {@link net.minecraftforge.common.crafting.CraftingHelper} the ingredients
		 * need to be parsed individually.
		 */
		@Override
		protected void add() {
			HammerCraftingManager.REGISTRY.register(new HammerShapedOreRecipe(output, "ABCDE", "FGHIJ", "KLMN ", 
					'A', input.get(0),  'B', input.get(1),  'C', input.get(2),  'D', input.get(3), 'E', input.get(4), 
					'F', input.get(5),  'G', input.get(6),  'H', input.get(7),  'I', input.get(8), 'J', input.get(9), 
					'K', input.get(10), 'L', input.get(11), 'M', input.get(12), 'N', input.get(13)));
		}

		/**
		 * Only looks up and removes a recipe based on the output
		 */
		@Override
		protected void remove() {
			HammerShapedOreRecipe wanted = null;
			try {
				wanted = HammerCraftingManager.getRecipes().stream().filter(recipe -> recipe.getRecipeOutput().isItemEqual(output)).findFirst().get();
			} catch (NoSuchElementException e) {
				CraftTweakerAPI.logError(String.format("Tried removing %s from %s but no recipe with matching output found.", getDescription(), getRecipeType()));
			}

			if (wanted != null) {
				// Fine to cast as it is allowing modifications
				((IForgeRegistryModifiable) HammerCraftingManager.REGISTRY).remove(wanted.getRegistryName());
			}
		}

		// Returns the type
		@Override
		public String getRecipeType() {
			return "HammerCraftingTable";
		}

		// Returns the output's display-name for CrT's log
		@Override
		public String getDescription() {
			return output.getDisplayName();
		}

	}

	@ZenMethod
	static public void addRecipe(IItemStack _output, IItemStack[] _input) {
		ItemStack output = null;
		List<ItemStack> input = new ArrayList<ItemStack>();
		try {
			output = CraftTweakerMC.getItemStack(_output);
			Arrays.asList(_input).forEach(iitemstack -> input.add(CraftTweakerMC.getItemStack(iitemstack)));
		} catch (IllegalArgumentException e) {
			CraftTweakerAPI.logError("Unable to add invalid HammerCraftingTable recipe: " + e.getMessage());
		}
		if (output != null && input.size() == 14)
			CraftTweakerAPI.apply(new HammerCraftingAction(output, input).action_add);
		else
			CraftTweakerAPI.logError("Null output or incorrect input amount: " + _input.length + "/14");
	}

	@ZenMethod
	static public void removeRecipe(IItemStack _output) {
		ItemStack output = null;
		try {
			output = CraftTweakerMC.getItemStack(_output);
		} catch (IllegalArgumentException e) {
			CraftTweakerAPI.logError("Unable to remove invalid HammerCraftingTable recipe: " + e.getMessage());
		}
		if (output != null)
			CraftTweakerAPI.apply(new HammerCraftingAction(output, new ArrayList<ItemStack>()).action_remove);
	}

}
