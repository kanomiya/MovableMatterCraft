package com.kanomiya.mcmod.movablemattercraft.apix.matter.property;

import com.kanomiya.mcmod.movablemattercraft.api.property.IProperty;
import com.kanomiya.mcmod.movablemattercraft.api.property.base.PropertyBool;
import com.kanomiya.mcmod.movablemattercraft.api.property.base.PropertyInt;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.PropertyMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model.IMatterModel;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model.PropertyMatterModel;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.IMatterType;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.PropertyMatterType;

/**
 *
 * MatterProperties
 *
 * @author Kanomiya
 *
 */
public class DefaultMatterProperties
{
	public static final IProperty<Integer> AMOUNT = new PropertyInt();
	public static final IProperty<IMatterType> TYPE = new PropertyMatterType().withProperty(Advanced.BAKE_MODEL, true);
	public static final IProperty<IMatterForm> FORM = new PropertyMatterForm().withProperty(Advanced.BAKE_MODEL, true);
	public static final IProperty<IMatterModel> MODEL = new PropertyMatterModel();


	/**
	 *
	 * Internal Properties of Matter Properties
	 *
	 * @author Kanomiya
	 *
	 */
	public static class Advanced
	{
		public static final IProperty<Boolean> BAKE_MODEL = new PropertyBool();

	}

}
