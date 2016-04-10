package com.kanomiya.mcmod.movablemattercraft.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.movablemattercraft.client.render.IMatterModelRender;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.DefaultMatterModelRenders;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.matter.property.model.IMatterModel;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.DefaultMatterTypes;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.IMatterType;


/**
 * @author Kanomiya
 *
 */
public class MatterRegistry
{
	public static final BiMap<ResourceLocation, IMatterProperty> propertyRegistry = HashBiMap.create();

	public static final BiMap<ResourceLocation, IMatterType> typeRegistry = HashBiMap.create();
	public static final BiMap<ResourceLocation, IMatterForm> formRegistry = HashBiMap.create();

	public static final BiMap<ResourceLocation, Class<? extends IMatterModel>> modelRegistry = HashBiMap.create();

	@SideOnly(Side.CLIENT)
	public static final BiMap<ResourceLocation, IMatterModelRender> renderRegistry = HashBiMap.create();

	protected MatterRegistry() {  }

	public static void registerDefaultProperties()
	{
		propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "amount"), DefaultMatterProperties.AMOUNT);
		propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "form"), DefaultMatterProperties.FORM);
		propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "type"), DefaultMatterProperties.TYPE);
		propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "model"), DefaultMatterProperties.MODEL);
	}

	public static void registerDefaultTypes()
	{
		typeRegistry.put(new ResourceLocation("wood"), DefaultMatterTypes.WOOD);

		typeRegistry.put(new ResourceLocation("wood_oak"), DefaultMatterTypes.WOOD_OAK);
		typeRegistry.put(new ResourceLocation("wood_spruce"), DefaultMatterTypes.WOOD_SPRUCE);
		typeRegistry.put(new ResourceLocation("wood_birch"), DefaultMatterTypes.WOOD_BIRCH);
		typeRegistry.put(new ResourceLocation("wood_jungle"), DefaultMatterTypes.WOOD_JUNGLE);
		typeRegistry.put(new ResourceLocation("wood_acacia"), DefaultMatterTypes.WOOD_ACACIA);
		typeRegistry.put(new ResourceLocation("wood_dark_oak"), DefaultMatterTypes.WOOD_DARK_OAK);

		typeRegistry.put(new ResourceLocation("stone"), DefaultMatterTypes.STONE);
		typeRegistry.put(new ResourceLocation("stone_granite"), DefaultMatterTypes.STONE_GRANITE);
		typeRegistry.put(new ResourceLocation("stone_diorite"), DefaultMatterTypes.STONE_DIORITE);
		typeRegistry.put(new ResourceLocation("stone_andesite"), DefaultMatterTypes.STONE_ANDESITE);

		typeRegistry.put(new ResourceLocation("iron"), DefaultMatterTypes.IRON);
		typeRegistry.put(new ResourceLocation("gold"), DefaultMatterTypes.GOLD);
		typeRegistry.put(new ResourceLocation("diamond"), DefaultMatterTypes.DIAMOND);
	}

	public static void registerDefaultForms()
	{
		formRegistry.put(new ResourceLocation("block"), DefaultMatterForms.BLOCK);
		formRegistry.put(new ResourceLocation("ingot"), DefaultMatterForms.INGOT);
	}

	public static void registerDefaultModels()
	{
		modelRegistry.put(new ResourceLocation("block"), ModelMatterBlock.class);
		modelRegistry.put(new ResourceLocation("ingot"), ModelMatterIngot.class);
	}

	@SideOnly(Side.CLIENT)
	public static void registerDefaultRenders()
	{
		renderRegistry.put(new ResourceLocation("block"), DefaultMatterModelRenders.BLOCK);
		renderRegistry.put(new ResourceLocation("ingot"), DefaultMatterModelRenders.INGOT);
	}


}
