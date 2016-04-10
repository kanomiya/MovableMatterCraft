package com.kanomiya.mcmod.movablemattercraft.apix;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.apix.client.render.IMatterModelRender;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.model.IMatterModel;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.IMatterType;

/**
 * @author Kanomiya
 *
 */
public class MovableMatterCraftAPIX
{
	public static final BiMap<ResourceLocation, IMatterType> typeRegistry = HashBiMap.create();
	public static final BiMap<ResourceLocation, IMatterForm> formRegistry = HashBiMap.create();

	public static final BiMap<ResourceLocation, Class<? extends IMatterModel>> modelRegistry = HashBiMap.create();

	@SideOnly(Side.CLIENT)
	public static final BiMap<ResourceLocation, IMatterModelRender> renderRegistry = HashBiMap.create();

	public static final String DOMAIN_NAME = "movablemattercraft";
	public static final String MODID = "com.kanomiya.mcmod." + DOMAIN_NAME;
	public static Logger logger;


	public static void initAPIX()
	{
		registerDefaultProperties();
	}

	protected static void registerDefaultProperties()
	{
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraftAPIX.MODID, "amount"), DefaultMatterProperties.AMOUNT);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraftAPIX.MODID, "form"), DefaultMatterProperties.FORM);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraftAPIX.MODID, "type"), DefaultMatterProperties.TYPE);
		MovableMatterCraftAPI.propertyRegistry.put(new ResourceLocation(MovableMatterCraftAPIX.MODID, "model"), DefaultMatterProperties.MODEL);
	}


}
