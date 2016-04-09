package com.kanomiya.mcmod.touchablecontainercraft.api.matter.form;

import net.minecraft.util.text.translation.I18n;

/**
 * @author Kanomiya
 *
 */
public interface IMatterForm
{
	String getUnlocalizedName();

	default String getDisplayName()
	{
		return I18n.translateToLocal(getUnlocalizedName());
	}

	int getMaxAmount();

}
