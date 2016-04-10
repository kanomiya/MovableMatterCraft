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
		MatterRegistry.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "amount"), DefaultMatterProperties.AMOUNT);
		MatterRegistry.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "form"), DefaultMatterProperties.FORM);
		MatterRegistry.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "type"), DefaultMatterProperties.TYPE);
		MatterRegistry.propertyRegistry.put(new ResourceLocation(MovableMatterCraft.MODID, "model"), DefaultMatterProperties.MODEL);
	}

	public static void registerDefaultTypes()
	{
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood"), DefaultMatterTypes.WOOD);

		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_oak"), DefaultMatterTypes.WOOD_OAK);
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_spruce"), DefaultMatterTypes.WOOD_SPRUCE);
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_birch"), DefaultMatterTypes.WOOD_BIRCH);
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_jungle"), DefaultMatterTypes.WOOD_JUNGLE);
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_acacia"), DefaultMatterTypes.WOOD_ACACIA);
		MatterRegistry.typeRegistry.put(new ResourceLocation("wood_dark_oak"), DefaultMatterTypes.WOOD_DARK_OAK);

		MatterRegistry.typeRegistry.put(new ResourceLocation("stone"), DefaultMatterTypes.STONE);
		MatterRegistry.typeRegistry.put(new ResourceLocation("stone_granite"), DefaultMatterTypes.STONE_GRANITE);
		MatterRegistry.typeRegistry.put(new ResourceLocation("stone_diorite"), DefaultMatterTypes.STONE_DIORITE);
		MatterRegistry.typeRegistry.put(new ResourceLocation("stone_andesite"), DefaultMatterTypes.STONE_ANDESITE);

		MatterRegistry.typeRegistry.put(new ResourceLocation("iron"), DefaultMatterTypes.IRON);
		MatterRegistry.typeRegistry.put(new ResourceLocation("gold"), DefaultMatterTypes.GOLD);
		MatterRegistry.typeRegistry.put(new ResourceLocation("diamond"), DefaultMatterTypes.DIAMOND);
	}

	public static void registerDefaultForms()
	{
		MatterRegistry.formRegistry.put(new ResourceLocation("block"), DefaultMatterForms.BLOCK);
		MatterRegistry.formRegistry.put(new ResourceLocation("ingot"), DefaultMatterForms.INGOT);
	}

	public static void registerDefaultModels()
	{
		MatterRegistry.modelRegistry.put(new ResourceLocation("block"), ModelMatterBlock.class);
		MatterRegistry.modelRegistry.put(new ResourceLocation("ingot"), ModelMatterIngot.class);
	}

	@SideOnly(Side.CLIENT)
	public static void registerDefaultRenders()
	{
		MatterRegistry.renderRegistry.put(new ResourceLocation("block"), DefaultMatterModelRenders.BLOCK);
		MatterRegistry.renderRegistry.put(new ResourceLocation("ingot"), DefaultMatterModelRenders.INGOT);
	}


}
