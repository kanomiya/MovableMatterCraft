package com.kanomiya.mcmod.movablemattercraft.api.property;

import net.minecraft.nbt.NBTBase;

/**
 * @author Kanomiya
 *
 */
public interface IProperty<T> extends IPropertyDispatcher
{
	NBTBase serializeNBT(T value);
	T deserializeNBT(NBTBase nbt);

}
