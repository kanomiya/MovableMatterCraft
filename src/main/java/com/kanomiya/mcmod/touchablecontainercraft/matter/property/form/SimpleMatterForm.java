package com.kanomiya.mcmod.touchablecontainercraft.matter.property.form;




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
		return "matter.form." + unlocalizedName + ".name";
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
