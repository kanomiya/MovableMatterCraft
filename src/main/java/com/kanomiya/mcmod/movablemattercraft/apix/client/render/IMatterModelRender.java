package com.kanomiya.mcmod.movablemattercraft.apix.client.render;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model.IMatterModel;

/**
 *
 * Please Register into {@link MovableMatterCraftAPIX#renderRegistry}
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
