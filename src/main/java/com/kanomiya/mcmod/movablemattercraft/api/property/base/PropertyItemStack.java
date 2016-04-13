package com.kanomiya.mcmod.movablemattercraft.api.property.base;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;

/**
 * @author Kanomiya
 *
 */
public class PropertyItemStack extends ISimpleProperty<ItemStack>
{
	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT(ItemStack value)
	{
		return value.serializeNBT();
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ItemStack deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagCompound) return ItemStack.loadItemStackFromNBT((NBTTagCompound) nbt);
		return null;
	}

}
