package com.kanomiya.mcmod.touchablecontainercraft.api.matter;

import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import com.kanomiya.mcmod.touchablecontainercraft.api.TouchableMatterCraftAPI;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.property.IMatterPropertyDispatcher;

/**
 * @author Kanomiya
 *
 */
public interface IMatter extends ICapabilityProvider, INBTSerializable<NBTTagCompound>, IMatterPropertyDispatcher
{
	String getDisplayName();
	void addInformation(List<String> tooltip);

	/**
	* @inheritDoc
	*/
	@Override
	default boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == TouchableMatterCraftAPI.capMatter;
	}

	/**
	* @inheritDoc
	*/
	@Override
	default <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (hasCapability(capability, facing)) ? (T) this : null;
	}


	class Storage<T extends IMatter> implements Capability.IStorage<T>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
		{
			if (capability == TouchableMatterCraftAPI.capMatter)
			{
				return instance.serializeNBT();
			}

			return null;
		}

		/**
		* @inheritDoc
		*/
		@Override
		public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
		{
			if (capability == TouchableMatterCraftAPI.capMatter && nbt instanceof NBTTagCompound)
			{
				instance.deserializeNBT((NBTTagCompound) nbt);
			}

		}

	}
}
