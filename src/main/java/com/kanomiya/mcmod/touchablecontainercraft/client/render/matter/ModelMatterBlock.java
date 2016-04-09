package com.kanomiya.mcmod.touchablecontainercraft.client.render.matter;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModel;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModelRender;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
public class ModelMatterBlock implements IMatterModel<EntityMatter, ModelMatterBlock>
{
	protected IBlockState blockState;

	public ModelMatterBlock()
	{
		this(Blocks.gravel.getDefaultState());
	}

	public ModelMatterBlock(IBlockState blockState)
	{
		this.blockState = blockState;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterModelRender<EntityMatter, ModelMatterBlock> getRender()
	{
		return DefaultMatterModelRenders.BLOCK;
	}

	public IBlockState getBlockState()
	{
		return blockState;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setInteger("blockState", Block.getStateId(blockState));

		return nbt;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		blockState = Block.getStateById(nbt.getInteger("blockState"));
	}

}
