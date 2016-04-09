package com.kanomiya.mcmod.touchablecontainercraft.api.matter;

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
	protected Matter matter;

	public MatterConvertEvent(T object)
	{
		this.object = object;
	}

	public T getObject()
	{
		return object;
	}

	public Matter getMatter()
	{
		return matter;
	}

	public void setMatter(Matter matter)
	{
		this.matter = matter;
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
