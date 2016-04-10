package com.kanomiya.mcmod.movablemattercraft.api.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public class MatterModelBakeEvent extends Event
{
	protected IMatter matter;

	public MatterModelBakeEvent(IMatter matter)
	{
		this.matter = matter;
	}

	public IMatter getMatter()
	{
		return matter;
	}

}
