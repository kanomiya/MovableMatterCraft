package com.kanomiya.mcmod.movablemattercraft.api.matter.action;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public interface IMatterAction
{
	boolean doAction(IMatter matter, int power);
	String getUnlocalizedName();
}
