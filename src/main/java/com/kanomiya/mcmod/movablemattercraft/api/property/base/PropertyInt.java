package com.kanomiya.mcmod.movablemattercraft.api.property.base;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;

/**
 * @author Kanomiya
 *
 */
public class PropertyInt extends ISimpleProperty<Integer>
{

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagInt serializeNBT(Integer value)
	{
		return new NBTTagInt(value);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Integer deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagInt) return ((NBTTagInt) nbt).getInt();
		return 0;
	}


}
