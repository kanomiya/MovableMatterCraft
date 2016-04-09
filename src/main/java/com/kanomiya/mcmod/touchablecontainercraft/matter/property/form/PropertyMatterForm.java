package com.kanomiya.mcmod.touchablecontainercraft.matter.property.form;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.touchablecontainercraft.registry.MatterRegistry;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterForm implements IMatterProperty<IMatterForm>
{
	@Override
	public void onAdded(IMatter matter, IMatterForm value)
	{
		DefaultMatterProperties.MODEL.fireBakeModel(matter);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagString serializeNBT(IMatterForm value)
	{
		ResourceLocation id = MatterRegistry.formRegistry.inverse().get(value);
		return new NBTTagString(id == null ? "null" : id.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterForm deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagString) return MatterRegistry.formRegistry.get(new ResourceLocation(((NBTTagString) nbt).getString()));
		return null;
	}

}
