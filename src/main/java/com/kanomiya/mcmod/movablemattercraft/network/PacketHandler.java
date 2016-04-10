package com.kanomiya.mcmod.movablemattercraft.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;

/**
 * @author Kanomiya
 *
 */
public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(MovableMatterCraftAPIX.MODID);

	public static void init()
	{
		int id = -1;
		INSTANCE.registerMessage(MessageEntityMatter.Handler.class, MessageEntityMatter.class, ++id, Side.CLIENT);

	}

}
