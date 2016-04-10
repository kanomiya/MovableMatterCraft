package com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model;

import com.kanomiya.mcmod.movablemattercraft.apix.client.render.IMatterModelRender;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;


/**
 * @author Kanomiya
 *
 */
public interface IMatterModel<T extends Entity, M extends IMatterModel> extends INBTSerializable<NBTTagCompound>
{
	IMatterModelRender<T, M> getRender();

	default void doRender(TextureManager renderEngine, T entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if (getRender() != null)
		{
			getRender().doRender(renderEngine, entity, (M) this, x, y, z, entityYaw, partialTicks);
		}

	}

}
