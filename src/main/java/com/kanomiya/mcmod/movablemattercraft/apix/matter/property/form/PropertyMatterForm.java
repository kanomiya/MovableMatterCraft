package com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;
import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterForm extends ISimpleProperty<IMatterForm>
{
	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagString serializeNBT(IMatterForm value)
	{
		ResourceLocation id = MovableMatterCraftAPIX.formRegistry.inverse().get(value);
		return new NBTTagString(id == null ? "null" : id.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterForm deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagString) return MovableMatterCraftAPIX.formRegistry.get(new ResourceLocation(((NBTTagString) nbt).getString()));
		return null;
	}

}
