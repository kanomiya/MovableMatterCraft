package com.kanomiya.mcmod.touchablecontainercraft.api.matter.property;

import net.minecraft.nbt.NBTBase;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public interface IMatterProperty<T>
{
	default void onAdded(IMatter matter, T value) {  }

	NBTBase serializeNBT(T value);
	T deserializeNBT(NBTBase nbt);

}
