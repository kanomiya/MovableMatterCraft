package com.kanomiya.mcmod.movablemattercraft.matter.action;

import com.kanomiya.mcmod.movablemattercraft.api.matter.action.IMatterAction;

/**
 * @author Kanomiya
 *
 */
public abstract class SimpleMatterAction implements IMatterAction
{
	protected String unlocalizedName;

	public SimpleMatterAction(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getUnlocalizedName()
	{
		return "matter.action." + unlocalizedName + ".name";
	}

}
