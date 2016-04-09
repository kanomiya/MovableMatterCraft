package com.kanomiya.mcmod.touchablecontainercraft.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.model.IMatterModel;

/**
 * @author Kanomiya
 *
 */
public class MatterModelBakeEvent extends Event
{
	protected IMatter iMatter;
	protected IMatterModel matterModel;

	public MatterModelBakeEvent(IMatter iMatter)
	{
		this.iMatter = iMatter;
	}

	public IMatter getMatter()
	{
		return iMatter;
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
