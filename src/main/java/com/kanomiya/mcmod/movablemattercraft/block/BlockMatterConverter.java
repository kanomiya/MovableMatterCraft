package com.kanomiya.mcmod.movablemattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.event.MatterConvertEvent;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;

/**
 * @author Kanomiya
 *
 */
public class BlockMatterConverter extends Block
{
	public BlockMatterConverter()
	{
		super(Material.rock);

		setCreativeTab(MovableMatterCraft.tab);
		setRegistryName(new ResourceLocation(MovableMatterCraft.MODID, "blockMatterConverter"));
		setUnlocalizedName("blockMatterConverter");
		setHardness(0.5f);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (heldItem != null)
		{
			if (MovableMatterCraftAPI.hasMatter(heldItem, null))
			{
				IMatter matter = MovableMatterCraftAPI.getMatter(heldItem, null);
				MatterConvertEvent.ToItemStack event = new MatterConvertEvent.ToItemStack(matter);
				MinecraftForge.EVENT_BUS.post(event);

				ItemStack stack = event.getItemStack();
				if (stack.stackSize <= 0) stack = null;
				if (matter.getValue(DefaultMatterProperties.AMOUNT) <= 0) playerIn.setHeldItem(hand, null);

				if (stack != null)
				{
					playerIn.setHeldItem(hand, null);

					if (! worldIn.isRemote)
					{
						InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() +1d, pos.getZ(), stack);
					}

				}

			} else
			{
				ItemStack newStack = new ItemStack(MovableMatterCraft.itemMatter, 1, 0);

				if (MovableMatterCraftAPI.hasMatter(newStack, null))
				{
					MatterConvertEvent.ToMatter event = new MatterConvertEvent.ToMatter(heldItem);
					MinecraftForge.EVENT_BUS.post(event);

					IMatter newMatter = event.getMatter();

					if (heldItem.stackSize <= 0) playerIn.setHeldItem(hand, null);
					if (newMatter.getValue(DefaultMatterProperties.AMOUNT) <= 0) newMatter = null;

					if (newMatter != null)
					{
						IMatter stackMatter = MovableMatterCraftAPI.getMatter(newStack, null);

						stackMatter.deserializeNBT(newMatter.serializeNBT());

						EntityMatter newEntity = new EntityMatter(worldIn, pos.getX() +hitX, pos.getY() +Math.min(hitY +0.5d, 1.2d), pos.getZ() +hitZ, newStack);
						newEntity.motionX = 0.3d < RANDOM.nextDouble() ? 0.1d : 0d;
						newEntity.motionY = 0.1d + Math.max(RANDOM.nextDouble() -0.6d, 0d);
						newEntity.motionZ = 0.3d < RANDOM.nextDouble() ? 0.1d : 0d;

						if (! worldIn.isRemote)
						{
							worldIn.spawnEntityInWorld(newEntity);
						}

						-- heldItem.stackSize;
						if (heldItem.stackSize == 0) playerIn.setHeldItem(hand, null);

					}
				}

			}


			return true;
		}

		return false;
	}


}
