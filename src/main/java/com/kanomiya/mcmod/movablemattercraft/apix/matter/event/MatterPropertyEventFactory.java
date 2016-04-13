package com.kanomiya.mcmod.movablemattercraft.apix.matter.event;

import net.minecraftforge.common.MinecraftForge;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.property.IProperty;

/**
 * @author Kanomiya
 *
 */
public class MatterPropertyEventFactory
{

	public static <T> void fireChangeProperty(IMatter matter, IProperty<T> property, T value)
	{
		MatterPropertyChangeEvent<T> event = new MatterPropertyChangeEvent<T>(matter, property, value);
		MinecraftForge.EVENT_BUS.post(event);
	}

	public static void fireBakeModel(IMatter matter)
	{
		MatterBakeEvent.Model event = new MatterBakeEvent.Model(matter);
		MinecraftForge.EVENT_BUS.post(event);
	}




}
