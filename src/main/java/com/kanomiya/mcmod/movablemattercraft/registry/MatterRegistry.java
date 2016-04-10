package com.kanomiya.mcmod.movablemattercraft.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.DefaultMatterModelRenders;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.DefaultMatterTypes;


/**
 * @author Kanomiya
 *
 */
public class MatterRegistry
{

	protected MatterRegistry() {  }

	public static void registerDefaultProperties()
	{
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "amount"), DefaultMatterProperties.AMOUNT);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "form"), DefaultMatterProperties.FORM);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "type"), DefaultMatterProperties.TYPE);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "model"), DefaultMatterProperties.MODEL);
	}

	public static void registerDefaultTypes()
	{
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood"), DefaultMatterTypes.WOOD);

		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_oak"), DefaultMatterTypes.WOOD_OAK);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_spruce"), DefaultMatterTypes.WOOD_SPRUCE);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_birch"), DefaultMatterTypes.WOOD_BIRCH);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_jungle"), DefaultMatterTypes.WOOD_JUNGLE);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_acacia"), DefaultMatterTypes.WOOD_ACACIA);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("wood_dark_oak"), DefaultMatterTypes.WOOD_DARK_OAK);

		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("stone"), DefaultMatterTypes.STONE);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("stone_granite"), DefaultMatterTypes.STONE_GRANITE);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("stone_diorite"), DefaultMatterTypes.STONE_DIORITE);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("stone_andesite"), DefaultMatterTypes.STONE_ANDESITE);

		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("iron"), DefaultMatterTypes.IRON);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("gold"), DefaultMatterTypes.GOLD);
		MovableMatterCraftAPI.typeRegistry.put(new ResourceLocation("diamond"), DefaultMatterTypes.DIAMOND);
	}

	public static void registerDefaultForms()
	{
		MovableMatterCraftAPI.formRegistry.put(new ResourceLocation("block"), DefaultMatterForms.BLOCK);
		MovableMatterCraftAPI.formRegistry.put(new ResourceLocation("ingot"), DefaultMatterForms.INGOT);
	}

	public static void registerDefaultModels()
	{
		MovableMatterCraftAPI.modelRegistry.put(new ResourceLocation("block"), ModelMatterBlock.class);
		MovableMatterCraftAPI.modelRegistry.put(new ResourceLocation("ingot"), ModelMatterIngot.class);
	}

	@SideOnly(Side.CLIENT)
	public static void registerDefaultRenders()
	{
		MovableMatterCraftAPI.renderRegistry.put(new ResourceLocation("block"), DefaultMatterModelRenders.BLOCK);
		MovableMatterCraftAPI.renderRegistry.put(new ResourceLocation("ingot"), DefaultMatterModelRenders.INGOT);
	}


}
