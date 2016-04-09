package com.kanomiya.mcmod.touchablecontainercraft.registry;

import java.util.function.Supplier;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModel;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModelRender;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.form.IMatterForm;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.type.IMatterType;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.matter.DefaultMatterModelRenders;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.touchablecontainercraft.matter.form.DefaultMatterForms;
import com.kanomiya.mcmod.touchablecontainercraft.matter.type.DefaultMatterTypes;


/**
 * @author Kanomiya
 *
 */
public class MatterRegistry
{
	public static final MatterRegistry INSTANCE = new MatterRegistry();

	public BiMap<ResourceLocation, IMatterType> typeRegistry = HashBiMap.create();
	public BiMap<ResourceLocation, IMatterForm> formRegistry = HashBiMap.create();

	public BiMap<ResourceLocation, Supplier<IMatterModel>> modelRegistry = HashBiMap.create();

	@SideOnly(Side.CLIENT)
	public BiMap<ResourceLocation, IMatterModelRender> renderRegistry = HashBiMap.create();

	protected MatterRegistry() {  }

	public void registerDefaultTypes()
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

	public void registerDefaultForms()
	{
		formRegistry.put(new ResourceLocation("block"), DefaultMatterForms.BLOCK);
		formRegistry.put(new ResourceLocation("ingot"), DefaultMatterForms.INGOT);
	}

	public void registerDefaultModels()
	{
		modelRegistry.put(new ResourceLocation("block"), ModelMatterBlock::new);
	}

	@SideOnly(Side.CLIENT)
	public void registerDefaultRenders()
	{
		renderRegistry.put(new ResourceLocation("block"), DefaultMatterModelRenders.BLOCK);
		renderRegistry.put(new ResourceLocation("ingot"), DefaultMatterModelRenders.INGOT);
	}


}
