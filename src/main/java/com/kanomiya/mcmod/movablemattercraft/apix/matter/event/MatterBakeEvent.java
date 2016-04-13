package com.kanomiya.mcmod.movablemattercraft.apix.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public abstract class MatterBakeEvent extends Event
{
	protected IMatter matter;

	public MatterBakeEvent(IMatter matter)
	{
		this.matter = matter;
	}

	public IMatter getMatter()
	{
		return matter;
	}


	public static class Model extends MatterBakeEvent
	{
		public Model(IMatter matter)
		{
			super(matter);
		}
	}


}
