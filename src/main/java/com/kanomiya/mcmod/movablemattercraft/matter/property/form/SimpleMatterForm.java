package com.kanomiya.mcmod.movablemattercraft.matter.property.form;

import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.IMatterForm;




/**
 * @author Kanomiya
 *
 */
public class SimpleMatterForm implements IMatterForm
{
	protected String unlocalizedName;
	protected int maxAmount;

	public SimpleMatterForm(String unlocalizedName, int maxAmount)
	{
		this.unlocalizedName = unlocalizedName;
		this.maxAmount = maxAmount;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getUnlocalizedName()
	{
		return unlocalizedName;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public int getMaxAmount()
	{
		return maxAmount;
	}

}
