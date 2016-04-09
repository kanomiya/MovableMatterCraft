package com.kanomiya.mcmod.touchablecontainercraft.client.render.matter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModel;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModelRender;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
public class ModelMatterIngot implements IMatterModel<EntityMatter, ModelMatterIngot>
{
	protected ResourceLocation texture;

	public ModelMatterIngot()
	{
		this(new ResourceLocation("null"));
	}

	public ModelMatterIngot(ResourceLocation texture)
	{
		this.texture = texture;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IMatterModelRender<EntityMatter, ModelMatterIngot> getRender()
	{
		return DefaultMatterModelRenders.INGOT;
	}

	public ResourceLocation getTexture()
	{
		return texture;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setString("texture", texture.toString());

		return nbt;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		texture = new ResourceLocation(nbt.getString("texture"));
	}

}
