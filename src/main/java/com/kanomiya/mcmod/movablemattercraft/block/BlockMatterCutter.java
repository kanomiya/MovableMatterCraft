package com.kanomiya.mcmod.movablemattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.action.IMatterAction;
import com.kanomiya.mcmod.movablemattercraft.api.matter.event.MatterActionEvent;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.action.DefaultMatterActions;

/**
 * @author Kanomiya
 *
 */
public class BlockMatterCutter extends Block
{
	public BlockMatterCutter()
	{
		super(Material.rock);

		setCreativeTab(MovableMatterCraft.tab);
		setRegistryName(new ResourceLocation(MovableMatterCraft.DOMAIN_NAME, "blockMatterCutter"));
		setUnlocalizedName("blockMatterCutter");
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
				MatterActionEvent event = new MatterActionEvent(matter, DefaultMatterActions.CUT, 1);

				if (! MinecraftForge.EVENT_BUS.post(event))
				{
					IMatterAction action = event.getAction();
					action.doAction(matter, event.getPower());
				}

			}

			return true;
		}

		return false;
	}


	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return FULL_BLOCK_AABB.expand(-0.01d, 0, -0.01d);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		// TODO: matter.setMatterForm();
		EnumFacing from = EnumFacing.getFacingFromVector((float) (entityIn.posX -pos.getX()), (float) (entityIn.posY -pos.getY() -0.1d), (float) (entityIn.posZ -pos.getZ()));
		if (from == EnumFacing.UP) return ;

		if (entityIn instanceof EntityMatter)
		{
			IMatter matter = ((EntityMatter) entityIn).getMatter();

			MatterActionEvent event = new MatterActionEvent(matter, DefaultMatterActions.CUT, 1);

			if (! MinecraftForge.EVENT_BUS.post(event))
			{
				IMatterAction action = event.getAction();
				if (action.doAction(matter, event.getPower()))
				{
					EntityMatter newEntity = new EntityMatter(worldIn, pos.getX(), pos.getY() +1.2d, pos.getZ(), matter);

					if (! worldIn.isRemote)
					{
						worldIn.spawnEntityInWorld(newEntity);
					}

					entityIn.setDead();
				}
			}

		}

	}


}
