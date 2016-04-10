package com.kanomiya.mcmod.movablemattercraft.matter.property.type;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.registry.MatterRegistry;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterType implements IMatterProperty<IMatterType>
{
	@Override
	public void onAdded(IMatter matter, IMatterType value)
	{
		DefaultMatterProperties.MODEL.fireBakeModel(matter);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagString serializeNBT(IMatterType value)
	{
		ResourceLocation id = MatterRegistry.typeRegistry.inverse().get(value);
		return new NBTTagString(id == null ? "null" : id.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterType deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagString) return MatterRegistry.typeRegistry.get(new ResourceLocation(((NBTTagString) nbt).getString()));
		return null;
	}
}
