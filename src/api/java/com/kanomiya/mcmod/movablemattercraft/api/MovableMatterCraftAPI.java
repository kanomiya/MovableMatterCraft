package com.kanomiya.mcmod.movablemattercraft.api;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public class MovableMatterCraftAPI
{
	@CapabilityInject(IMatter.class)
	public static final Capability<IMatter> capMatter = null;

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
