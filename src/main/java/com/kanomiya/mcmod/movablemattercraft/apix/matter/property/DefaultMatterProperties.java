package com.kanomiya.mcmod.movablemattercraft.apix.matter.property;

import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.PropertyMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.item.PropertyMatterItemStack;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model.PropertyMatterModel;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.PropertyMatterType;

/**
 * @author Kanomiya
 *
 */
public class DefaultMatterProperties
{
	public static final PropertyInt AMOUNT = new PropertyInt();
	public static final PropertyMatterType TYPE = new PropertyMatterType();
	public static final PropertyMatterForm FORM = new PropertyMatterForm();
	public static final PropertyMatterModel MODEL = new PropertyMatterModel();

	public static final PropertyMatterItemStack ITEMSTACK = new PropertyMatterItemStack();


}
