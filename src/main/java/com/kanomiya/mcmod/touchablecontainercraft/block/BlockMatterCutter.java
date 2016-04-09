package com.kanomiya.mcmod.touchablecontainercraft.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.kanomiya.mcmod.touchablecontainercraft.TouchableMatterCraft;
import com.kanomiya.mcmod.touchablecontainercraft.tileentity.TileEntityMatterCutter;

/**
 * @author Kanomiya
 *
 */
public class BlockMatterCutter extends BlockContainer
{
	public BlockMatterCutter()
	{
		super(Material.rock);

		setCreativeTab(TouchableMatterCraft.tab);
		setRegistryName(new ResourceLocation(TouchableMatterCraft.MODID, "blockMatterCutter"));
		setHardness(0.5f);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityMatterCutter();
	}



}
