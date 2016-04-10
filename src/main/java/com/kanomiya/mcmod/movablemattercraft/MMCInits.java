package com.kanomiya.mcmod.movablemattercraft;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.DefaultMatterModelRenders;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.DefaultMatterTypes;

/**
 * @author Kanomiya
 *
 */
public class MMCInits
{

	public static void initMMC()
	{
		registerDefaultTypes();
		registerDefaultForms();
		registerDefaultModels();

		if (FMLCommonHandler.instance().getSide().isClient())
		{
			registerDefaultRenders();
		}

	}

	protected static void registerDefaultTypes()
	{
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood"), DefaultMatterTypes.WOOD);

		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_oak"), DefaultMatterTypes.WOOD_OAK);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_spruce"), DefaultMatterTypes.WOOD_SPRUCE);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_birch"), DefaultMatterTypes.WOOD_BIRCH);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_jungle"), DefaultMatterTypes.WOOD_JUNGLE);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_acacia"), DefaultMatterTypes.WOOD_ACACIA);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("wood_dark_oak"), DefaultMatterTypes.WOOD_DARK_OAK);

		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("stone"), DefaultMatterTypes.STONE);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("stone_granite"), DefaultMatterTypes.STONE_GRANITE);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("stone_diorite"), DefaultMatterTypes.STONE_DIORITE);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("stone_andesite"), DefaultMatterTypes.STONE_ANDESITE);

		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("iron"), DefaultMatterTypes.IRON);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("gold"), DefaultMatterTypes.GOLD);
		MovableMatterCraftAPIX.typeRegistry.put(new ResourceLocation("diamond"), DefaultMatterTypes.DIAMOND);
	}

	protected static void registerDefaultForms()
	{
		MovableMatterCraftAPIX.formRegistry.put(new ResourceLocation("block"), DefaultMatterForms.BLOCK);
		MovableMatterCraftAPIX.formRegistry.put(new ResourceLocation("ingot"), DefaultMatterForms.INGOT);
	}

	protected static void registerDefaultModels()
	{
		MovableMatterCraftAPIX.modelRegistry.put(new ResourceLocation("block"), ModelMatterBlock.class);
		MovableMatterCraftAPIX.modelRegistry.put(new ResourceLocation("ingot"), ModelMatterIngot.class);
	}

	@SideOnly(Side.CLIENT)
	protected static void registerDefaultRenders()
	{
		MovableMatterCraftAPIX.renderRegistry.put(new ResourceLocation("block"), DefaultMatterModelRenders.BLOCK);
		MovableMatterCraftAPIX.renderRegistry.put(new ResourceLocation("ingot"), DefaultMatterModelRenders.INGOT);
	}

}
