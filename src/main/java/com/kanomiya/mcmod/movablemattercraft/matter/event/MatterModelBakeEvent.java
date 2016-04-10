package com.kanomiya.mcmod.movablemattercraft.matter.event;

import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.property.model.IMatterModel;

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
