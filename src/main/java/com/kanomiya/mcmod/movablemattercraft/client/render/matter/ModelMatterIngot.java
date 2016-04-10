package com.kanomiya.mcmod.movablemattercraft.client.render.matter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.kanomiya.mcmod.movablemattercraft.client.render.IMatterModelRender;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.property.model.IMatterModel;

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
