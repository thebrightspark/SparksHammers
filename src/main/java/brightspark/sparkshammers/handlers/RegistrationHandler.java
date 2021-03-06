package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.Reference;
import brightspark.sparkshammers.entity.EntityFallingHammer;
import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.init.SHRecipes;
import brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber
public class RegistrationHandler
{
    @SubscribeEvent
    public static void addRegistry(RegistryEvent.NewRegistry event)
    {
        HammerCraftingManager.setRegistry(
            new RegistryBuilder<HammerShapedOreRecipe>()
                .setName(new ResourceLocation(Reference.MOD_ID, "hammer_crafting_recipes"))
                .setType(HammerShapedOreRecipe.class)
                .disableSaving()
                .allowModification()
                .create());
    }

    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        //Register all items
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
    public static void regEntities(RegistryEvent.Register<EntityEntry> event)
    {
        //Register all entities
        event.getRegistry().register(new EntityEntry(EntityFallingHammer.class, "falling_hammer").setRegistryName("falling_hammer"));
    }

    @SubscribeEvent
    public static void regVanillaRecipes(RegistryEvent.Register<IRecipe> event)
    {
        //Register all vanilla recipes
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        registry.registerAll(SHRecipes.getVanillaRecipes());
    }

    @SubscribeEvent
    public static void regSHRecipes(RegistryEvent.Register<HammerShapedOreRecipe> event)
    {
        //Register all hammer crafting recipes
        IForgeRegistry<HammerShapedOreRecipe> registry = event.getRegistry();
        registry.registerAll(SHRecipes.getSHRecipes());
    }
}
