package com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;
import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterType extends ISimpleProperty<IMatterType>
{
	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagString serializeNBT(IMatterType value)
	{
		ResourceLocation id = MovableMatterCraftAPIX.typeRegistry.inverse().get(value);
		return new NBTTagString(id == null ? "null" : id.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterType deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagString) return MovableMatterCraftAPIX.typeRegistry.get(new ResourceLocation(((NBTTagString) nbt).getString()));
		return null;
	}
}
