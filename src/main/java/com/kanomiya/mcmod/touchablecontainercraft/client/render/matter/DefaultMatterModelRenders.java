package com.kanomiya.mcmod.touchablecontainercraft.client.render.matter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.touchablecontainercraft.TouchableMatterCraft;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModelRender;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.ModelBox;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.ModelIngot;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
public class DefaultMatterModelRenders
{
	public static class ResourceLocations
	{
		public static final ResourceLocation resourceBox = new ResourceLocation(TouchableMatterCraft.SHORT_MODID, "textures/matter/modelBox.png");
	}

	public static class Models
	{
		public static final ModelBox modelBox = new ModelBox();
	}


	public static final IMatterModelRender BLOCK = new IMatterModelRender<EntityMatter, ModelMatterBlock>()
	{
		@Override
		public void doRender(TextureManager renderEngine, EntityMatter entity, ModelMatterBlock model, double x, double y, double z, float entityYaw, float partialTicks)
		{
			GlStateManager.pushMatrix();

			GlStateManager.translate(x -0.5d, y, z -0.5d);

			// GlStateManager.scale(0.0625d, 0.0625d, 0.0625d);
			// model.render(entity, 0,0,0,0,0, 1f);

			renderEngine.bindTexture(TextureMap.locationBlocksTexture);

			BlockRendererDispatcher disp = Minecraft.getMinecraft().getBlockRendererDispatcher();
			GlStateManager.translate(0d, 0, 1d);
			disp.renderBlockBrightness(model.getBlockState(), entity.getBrightness(partialTicks));

			// Tessellator tessellator = Tessellator.getInstance();
			// VertexBuffer vertexBuffer = tessellator.getBuffer();
			// disp.renderBlock(Blocks.end_stone.getDefaultState(), new BlockPos(x, y, z), Minecraft.getMinecraft().theWorld, vertexBuffer);

			GlStateManager.popMatrix();
		}
	};


	public static final IMatterModelRender INGOT = new IMatterModelRender<EntityMatter, ModelMatterIngot>()
	{
		protected final ModelIngot modelIngot = new ModelIngot();

		@Override
		public void doRender(TextureManager renderEngine, EntityMatter entity, ModelMatterIngot model, double x, double y, double z, float entityYaw, float partialTicks)
		{
			GlStateManager.pushMatrix();

			GlStateManager.translate(x -0.5d, y, z -0.5d);

			GlStateManager.scale(0.0625d, 0.0625d, 0.0625d);
			// model.render(entity, 0,0,0,0,0, 1f);

			// float brightness = entity.getBrightness(partialTicks);
			// GlStateManager.color(brightness, brightness, brightness, 1.0F);

			renderEngine.bindTexture(model.getTexture());

			modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);

			int amount = entity.getMatter().getAmount();
			if (2 <= amount)
			{
				GlStateManager.pushMatrix();

				GlStateManager.translate(0d, 0d, 4d);
				modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);

				if (3 <= amount)
				{
					GlStateManager.translate(0d, 0d, 4d);
					modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
				}

				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();

				if (4 <= amount)
				{
					GlStateManager.translate(0d, 3d, 2d);
					modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);


					if (5 <= amount)
					{
						GlStateManager.translate(0d, 0d, 4d);
						modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
					}
				}
				GlStateManager.popMatrix();

				if (6 <= amount)
				{
					GlStateManager.translate(0d, 6d, 4d);
					modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
				}

			}


			GlStateManager.popMatrix();
		}
	};


}
