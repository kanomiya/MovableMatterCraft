package com.kanomiya.mcmod.movablemattercraft.apix.matter.property;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;

import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;

/**
 * @author Kanomiya
 *
 */
public class PropertyInt implements IMatterProperty<Integer>
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
