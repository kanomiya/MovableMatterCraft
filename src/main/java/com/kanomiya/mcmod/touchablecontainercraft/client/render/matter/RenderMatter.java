package com.kanomiya.mcmod.touchablecontainercraft.client.render.matter;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.model.IMatterModel;

@SideOnly(Side.CLIENT)
public class RenderMatter extends Render<EntityMatter>
{

	public RenderMatter(RenderManager manager)
	{
		super(manager);
	}

	@Override
	public void doRender(EntityMatter entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if (entity.getMatter() == null) return ;

		bindEntityTexture(entity);
		IMatterModel<EntityMatter, IMatterModel> model = entity.getMatter().getValue(DefaultMatterProperties.MODEL);

		if (model != null)
		{
			model.doRender(renderManager.renderEngine, entity, x, y, z, entityYaw, partialTicks);
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	* @inheritDoc
	*/
	@Override
	protected ResourceLocation getEntityTexture(EntityMatter entity)
	{
		return null; // ??
	}


}

