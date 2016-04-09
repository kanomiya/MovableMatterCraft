package com.kanomiya.mcmod.touchablecontainercraft.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public class TouchableMatterCraftAPI
{
	@CapabilityInject(IMatter.class)
	public static final Capability<IMatter> capMatter = null;

}
