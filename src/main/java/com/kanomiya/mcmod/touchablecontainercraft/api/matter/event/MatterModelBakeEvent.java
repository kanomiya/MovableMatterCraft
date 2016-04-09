package com.kanomiya.mcmod.touchablecontainercraft.api.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModel;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.Matter;

/**
 * @author Kanomiya
 *
 */
public class MatterModelBakeEvent extends Event
{
	protected Matter matter;
	protected IMatterModel matterModel;

	public MatterModelBakeEvent(Matter matter)
	{
		this.matter = matter;
	}

	public Matter getMatter()
	{
		return matter;
	}

	public void setMatterModel(IMatterModel matterModel)
	{
		this.matterModel = matterModel;
	}

	public IMatterModel getMatterModel()
	{
		return matterModel;
	}

}
