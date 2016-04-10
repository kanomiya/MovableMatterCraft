package com.kanomiya.mcmod.movablemattercraft.api.matter;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;


/**
 * @author Kanomiya
 *
 */
public class MatterConvertEvent<T> extends Event
{
	protected T object;
	protected IMatter iMatter;

	public MatterConvertEvent(T object)
	{
		this.object = object;
	}

	public T getObject()
	{
		return object;
	}

	public IMatter getMatter()
	{
		return iMatter;
	}

	public void setMatter(IMatter iMatter)
	{
		this.iMatter = iMatter;
	}


	public static class Block extends MatterConvertEvent<IBlockState>
	{
		public Block(IBlockState blockState)
		{
			super(blockState);
		}

		public IBlockState getBlockState()
		{
			return getObject();
		}
	}

	public static class Item extends MatterConvertEvent<ItemStack>
	{
		public Item(ItemStack stack)
		{
			super(stack);
		}

		public ItemStack getItemStack()
		{
			return getObject();
		}
	}


}
