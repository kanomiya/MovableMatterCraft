package com.kanomiya.mcmod.movablemattercraft.api;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.movablemattercraft.client.render.IMatterModelRender;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.matter.property.model.IMatterModel;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.IMatterType;

/**
 * @author Kanomiya
 *
 */
public class MovableMatterCraftAPI
{
	@CapabilityInject(IMatter.class)
	public static final Capability<IMatter> capMatter = null;

	public static final BiMap<ResourceLocation, IMatterProperty> propertyRegistry = HashBiMap.create();

	public static final BiMap<ResourceLocation, IMatterType> typeRegistry = HashBiMap.create();
	public static final BiMap<ResourceLocation, IMatterForm> formRegistry = HashBiMap.create();

	public static final BiMap<ResourceLocation, Class<? extends IMatterModel>> modelRegistry = HashBiMap.create();

	@SideOnly(Side.CLIENT)
	public static final BiMap<ResourceLocation, IMatterModelRender> renderRegistry = HashBiMap.create();

	/**
	 * ICapabilityProviderがIMatterのインスタンスを持つかどうか判定する<br>
	 *
	 * @param provider ICapabilityProvider e.g. Entity, TileEntity, ItemStack
	 * @param facing 向き direction\
	 * @return if provider has IMatter Capability, true
	 */
	public static boolean hasMatter(ICapabilityProvider provider, EnumFacing facing)
	{
		return provider.hasCapability(MovableMatterCraftAPI.capMatter, facing);
	}

	/**
	 *
	 * ICapabilityProviderの持つIMatterのインスタンスを返す<br>
	 * ICapabilityProviderがIMatterのインスタンスを持たない場合、nullを返す
	 *
	 * @param provider ICapabilityProvider e.g. Entity, TileEntity, ItemStack
	 * @param facing 向き direction
	 * @return IMatter, or null
	 */
	public static IMatter getMatter(ICapabilityProvider provider, EnumFacing facing)
	{
		if (! hasMatter(provider, facing)) return null;
		return provider.getCapability(MovableMatterCraftAPI.capMatter, facing);
	}

}
