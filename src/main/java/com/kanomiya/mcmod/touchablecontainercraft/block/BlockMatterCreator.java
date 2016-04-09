package com.kanomiya.mcmod.touchablecontainercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.kanomiya.mcmod.touchablecontainercraft.TouchableMatterCraft;
import com.kanomiya.mcmod.touchablecontainercraft.api.TouchableMatterCraftAPI;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;
import com.kanomiya.mcmod.touchablecontainercraft.registry.MatterMappingRegistry;

/**
 * @author Kanomiya
 *
 */
public class BlockMatterCreator extends Block
{
	public BlockMatterCreator()
	{
		super(Material.rock);

		setCreativeTab(TouchableMatterCraft.tab);
		setRegistryName(new ResourceLocation(TouchableMatterCraft.MODID, "blockMatterCreator"));
		setUnlocalizedName("blockMatterCreator");
		setHardness(0.5f);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (heldItem != null)
		{
			ItemStack newStack = new ItemStack(TouchableMatterCraft.itemMatter, 1, 0);

			if (newStack.hasCapability(TouchableMatterCraftAPI.capMatter, null))
			{
				IMatter stackMatter = newStack.getCapability(TouchableMatterCraftAPI.capMatter, null);
				IMatter iMatter = MatterMappingRegistry.INSTANCE.createFromItemStack(heldItem);

				if (iMatter != null)
				{
					stackMatter.deserializeNBT(iMatter.serializeNBT());

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

					return true;
				}

			}
		}

		return false;
	}


}
