package com.kanomiya.mcmod.movablemattercraft.api.matter.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public class MatterConvertEvent extends Event
{
	protected IMatter matter;
	protected ItemStack stack;

	public MatterConvertEvent(IMatter matter, ItemStack stack)
	{
		this.matter = matter;
		this.stack = stack;

	}

	public ItemStack getItemStack()
	{
		return stack;
	}

	public void setItemStack(ItemStack stack)
	{
		this.stack = stack;
	}

	public IMatter getMatter()
	{
		return matter;
	}

	public void setMatter(IMatter matter)
	{
		this.matter = matter;
	}


	public static class ToItemStack extends MatterConvertEvent
	{
		public ToItemStack(IMatter matter)
		{
			super(matter, null);
		}

	}

	public static class ToMatter extends MatterConvertEvent
	{
		public ToMatter(ItemStack stack)
		{
			super(null, stack);
		}

	}



}
