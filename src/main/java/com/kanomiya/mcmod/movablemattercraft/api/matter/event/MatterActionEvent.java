package com.kanomiya.mcmod.movablemattercraft.api.matter.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.action.IMatterAction;

/**
 * @author Kanomiya
 *
 */
@Cancelable
public class MatterActionEvent extends Event
{
	protected IMatter matter;
	protected IMatterAction action;
	protected int power;

	public MatterActionEvent(IMatter matter, IMatterAction action, int power)
	{
		this.matter = matter;
		this.action = action;
		this.power = power;
	}

	public IMatter getMatter()
	{
		return matter;
	}

	public IMatterAction getAction()
	{
		return action;
	}

	public void setAction(IMatterAction action)
	{
		this.action = action;
	}

	public int getPower()
	{
		return power;
	}

	public void setPower(int power)
	{
		this.power = power;
	}

}
