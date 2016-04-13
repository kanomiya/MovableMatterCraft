package com.kanomiya.mcmod.movablemattercraft.api.property.base;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;

/**
 * @author Kanomiya
 *
 */
public class PropertyBool extends ISimpleProperty<Boolean>
{

	/**
	* @inheritDoc
	*/
	@Override
	public NBTBase serializeNBT(Boolean value)
	{
		return new NBTTagByte((byte) (value ? 1 : 0));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Boolean deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagByte) return ((NBTTagByte) nbt).getByte() == 1 ? true : false;
		return false;
	}


}
