package brightspark.sparkshammers.item;

import brightspark.sparkshammers.customTools.Tool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

/**
 * @author CreeperShift
 * brightspark.sparkshammers.item
 * Oct 15, 2016
 */
public class ItemHammerMana extends ItemAOE implements IManaUsingItem
{
	private static int damageToMana = 60; // This is the default value for
											// Terrasteel 1 Damage --> Mana
											// ratio

	public ItemHammerMana(Tool tool, boolean isExcavator)
    {
		super(tool, isExcavator);
	}

	/**
	 * Calculate mana cost and request it. If not enough, we damage the tool
	 * normally.
	 */
	public static void damageItem(ItemStack stack, int dmg, EntityLivingBase entity)
    {
		int manaToRequest = dmg * damageToMana;
		boolean manaRequested = entity instanceof EntityPlayer && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entity, manaToRequest, true);

		if (!manaRequested)
			stack.damageItem(dmg, entity);
	}

	/**
	 * Item uses Mana, so we display the Mana bar
	 */
	@Override
	public boolean usesMana(ItemStack stack)
    {
		return true;
	}

	/**
	 * 
	 * If we have a mana providing item such as a mana tablet available, do not
	 * damage the tool
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
		damageItem(stack, 1, player);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase player)
    {
		if (state.getBlockHardness(world, pos) != 0F)
			damageItem(stack, 1, player);
		return true;
	}

	/**
	 * 
	 * Handle Hammer/Excavator repair Null checks, check if we have enough mana
	 * Uses default Botania values (double the mana ratio)
	 */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if (!worldIn.isRemote && entityIn instanceof EntityPlayer && stack.getItemDamage() > 0
				&& ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entityIn, damageToMana * 2, true))
			stack.setItemDamage(stack.getItemDamage() - 1);
	}
}
