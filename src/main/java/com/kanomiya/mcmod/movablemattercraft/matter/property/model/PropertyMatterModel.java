package com.kanomiya.mcmod.movablemattercraft.matter.property.model;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.event.MatterModelBakeEvent;
import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.movablemattercraft.registry.MatterRegistry;

/**
 * @author Kanomiya
 *
 */
public class PropertyMatterModel implements IMatterProperty<IMatterModel>
{

	public void fireBakeModel(IMatter matter)
	{
		MatterModelBakeEvent event = new MatterModelBakeEvent(matter);
		MinecraftForge.EVENT_BUS.post(event);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT(IMatterModel value)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		ResourceLocation id = MatterRegistry.modelRegistry.inverse().get(value);

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

			Class<? extends IMatterModel> clazz = MatterRegistry.modelRegistry.get(new ResourceLocation(compound.getString("id")));
			if (clazz != null)
			{
				try
				{
					IMatterModel value = clazz.newInstance();
					value.deserializeNBT(compound.getCompoundTag("args"));

					return value;
				} catch (InstantiationException | IllegalAccessException e)
				{
					MovableMatterCraft.logger.error(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
