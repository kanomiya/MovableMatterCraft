package com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.api.property.ISimpleProperty;
import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterModel extends ISimpleProperty<IMatterModel>
{

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT(IMatterModel value)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		ResourceLocation id = MovableMatterCraftAPIX.modelRegistry.inverse().get(value);

		nbt.setString("id", id == null ? "null" : id.toString());
		nbt.setTag("args", value.serializeNBT());

		return nbt;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterModel deserializeNBT(NBTBase nbt)
	{
		if (nbt instanceof NBTTagCompound)
		{
			NBTTagCompound compound = (NBTTagCompound) nbt;

			Class<? extends IMatterModel> clazz = MovableMatterCraftAPIX.modelRegistry.get(new ResourceLocation(compound.getString("id")));
			if (clazz != null)
			{
				try
				{
					IMatterModel value = clazz.newInstance();
					value.deserializeNBT(compound.getCompoundTag("args"));

					return value;
				} catch (InstantiationException | IllegalAccessException e)
				{
					MovableMatterCraftAPIX.logger.error(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
