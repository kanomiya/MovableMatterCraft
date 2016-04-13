package com.kanomiya.mcmod.movablemattercraft.apix.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.property.IProperty;

/**
 * @author Kanomiya
 *
 */
public class MatterPropertyChangeEvent<T> extends Event
{
	protected IMatter matter;
	protected IProperty<T> property;
	protected T value;

	public MatterPropertyChangeEvent(IMatter matter, IProperty<T> property, T value)
	{
		this.matter = matter;
		this.property = property;
		this.value = value;
	}

	public IMatter getMatter()
	{
		return matter;
	}

	public IProperty<T> getProperty()
	{
		return property;
	}

	public T getValue()
	{
		return value;
	}

}
