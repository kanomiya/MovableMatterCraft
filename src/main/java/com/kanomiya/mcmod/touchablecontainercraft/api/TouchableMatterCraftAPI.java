package com.kanomiya.mcmod.touchablecontainercraft.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.Matter;

/**
 * @author Kanomiya
 *
 */
public class TouchableMatterCraftAPI
{
	@CapabilityInject(Matter.class)
	public static final Capability<Matter> capMatter = null;

}
