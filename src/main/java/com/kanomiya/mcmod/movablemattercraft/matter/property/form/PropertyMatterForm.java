package com.kanomiya.mcmod.movablemattercraft.matter.property.form;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;

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
		ResourceLocation id = MovableMatterCraftAPI.formRegistry.inverse().get(value);
		return new NBTTagString(id == null ? "null" : id.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterForm deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagString) return MovableMatterCraftAPI.formRegistry.get(new ResourceLocation(((NBTTagString) nbt).getString()));
		return null;
	}

}
