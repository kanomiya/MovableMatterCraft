package com.kanomiya.mcmod.movablemattercraft.apix.matter.property.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterItemStack implements IMatterProperty<ItemStack>
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
