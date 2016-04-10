package com.kanomiya.mcmod.movablemattercraft.client.render.matter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;
import com.kanomiya.mcmod.movablemattercraft.apix.client.render.IMatterModelRender;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.client.render.ModelBox;
import com.kanomiya.mcmod.movablemattercraft.client.render.ModelIngot;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
@SideOnly(Side.CLIENT)
public class DefaultMatterModelRenders
{
	@SideOnly(Side.CLIENT)
	public static class ResourceLocations
	{
		public static final ResourceLocation resourceBox = new ResourceLocation(MovableMatterCraftAPIX.DOMAIN_NAME, "textures/matter/modelBox.png");
	}

	@SideOnly(Side.CLIENT)
	public static class Models
	{
		public static final ModelBox modelBox = new ModelBox();
		public static final ModelIngot modelIngot = new ModelIngot();
	}


	@SideOnly(Side.CLIENT)
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


	@SideOnly(Side.CLIENT)
	public static final IMatterModelRender INGOT = new IMatterModelRender<EntityMatter, ModelMatterIngot>()
	{
		@Override
		public void doRender(TextureManager renderEngine, EntityMatter entity, ModelMatterIngot model, double x, double y, double z, float entityYaw, float partialTicks)
		{
			GlStateManager.pushMatrix();

			GlStateManager.translate(x, y, z);
			GlStateManager.translate(-0.34d, 0d, -0.34d);

			GlStateManager.scale(0.0625d, 0.0625d, 0.0625d);
			// model.render(entity, 0,0,0,0,0, 1f);

			// float brightness = entity.getBrightness(partialTicks);
			// GlStateManager.color(brightness, brightness, brightness, 1.0F);

			renderEngine.bindTexture(model.getTexture());

			Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);

			int amount = entity.getMatter().getValue(DefaultMatterProperties.AMOUNT);
			if (2 <= amount)
			{
				GlStateManager.pushMatrix();

				GlStateManager.translate(0d, 0d, 4d);
				Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);

				if (3 <= amount)
				{
					GlStateManager.translate(0d, 0d, 4d);
					Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
				}

				GlStateManager.popMatrix();

				GlStateManager.pushMatrix();
				if (4 <= amount)
				{
					double oz1 = (amount < 7) ? 0d : 2d;

					GlStateManager.translate(0d, 3d, oz1 +2d);
					Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);


					if (5 <= amount)
					{
						GlStateManager.translate(0d, 0d, 4d);
						Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
					}
				}
				GlStateManager.popMatrix();

				GlStateManager.pushMatrix();
				if (6 <= amount)
				{
					double oy1 = (amount < 7) ? 0d : -3d;
					double oz1 = (amount < 7) ? 0d : -4d;

					GlStateManager.translate(0d, oy1 +6d, oz1 +4d);
					Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
				}
				GlStateManager.popMatrix();

				if (7 <= amount)
				{
					GlStateManager.translate(0d, 6d, 6d);
					Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);

					if (8 <= amount)
					{
						GlStateManager.translate(0d, 0d, -4d);
						Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);


						if (9 <= amount)
						{
							GlStateManager.translate(0d, 3d, 2d);
							Models.modelIngot.render(entity, 0, 0, 0, 0, 0, 1f);
						}
					}
				}

			}


			GlStateManager.popMatrix();
		}
	};


}
