package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.init.SHRecipes;
import brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegistrationHandler
{
    //TODO: Create a new registry for the hammer crafting table recipes

    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> event)
    {
        //Register all items
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(SHItems.getItems());

        //Register item blocks
        registry.registerAll(SHBlocks.getItemBlocks());
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.registerAll(SHBlocks.getBlocks());

        GameRegistry.registerTileEntity(TileHammer.class, SHBlocks.blockHammer.getRegistryName().getResourcePath());
    }

    @SubscribeEvent
    public static void regRecipes(RegistryEvent.Register<IRecipe> event)
    {
        //Register all recipes
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        registry.registerAll(SHRecipes.getRecipes());
    }
}
