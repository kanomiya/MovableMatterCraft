package com.kanomiya.mcmod.movablemattercraft.matter.action;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.action.IMatterAction;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.IMatterForm;

/**
 * @author Kanomiya
 *
 */
public class DefaultMatterActions
{
	public static final IMatterAction CUT = new SimpleMatterAction("cut")
	{
		@Override
		public boolean doAction(IMatter matter, int power)
		{
			IMatterForm form = matter.getValue(DefaultMatterProperties.FORM);

			if (form == DefaultMatterForms.BLOCK)
			{
				matter.withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT);
				matter.withProperty(DefaultMatterProperties.AMOUNT, 9);

				return true;
			}

			return false;
		}
	};
}
