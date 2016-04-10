package com.kanomiya.mcmod.movablemattercraft.network;

import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @author Kanomiya
 *
 */
public class MessageEntityMatter implements IMessage {

	public static class Handler implements IMessageHandler<MessageEntityMatter, IMessage>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public IMessage onMessage(MessageEntityMatter message, MessageContext ctx)
		{
			Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityId);

			if (entity instanceof EntityMatter)
			{
				((EntityMatter) entity).setMatterStack(message.matterStack);
			}

			return null;
		}

	}

	protected int entityId;
	protected ItemStack matterStack;

	public MessageEntityMatter()
	{

	}

	public MessageEntityMatter(Entity entity, ItemStack matterStack)
	{
		entityId = entity.getEntityId();
		this.matterStack = matterStack;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void fromBytes(ByteBuf buf)
	{
		entityId = buf.readInt();
		matterStack = ByteBufUtils.readItemStack(buf);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(entityId);
		ByteBufUtils.writeItemStack(buf, matterStack);
	}


}
