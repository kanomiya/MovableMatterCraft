package com.kanomiya.mcmod.touchablecontainercraft.client.render;

import com.kanomiya.mcmod.touchablecontainercraft.matter.property.model.IMatterModel;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * Please Register into MatterRegistry
 *
 * @author Kanomiya
 *
 */
@FunctionalInterface
@SideOnly(Side.CLIENT)
public interface IMatterModelRender<T extends Entity, M extends IMatterModel>
{
	void doRender(TextureManager renderEngine, T entity, M model, double x, double y, double z, float entityYaw, float partialTicks);

}
