package com.kanomiya.mcmod.movablemattercraft.matter;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.Maps;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.property.IProperty;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.event.MatterPropertyEventFactory;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.DefaultMatterProperties;

/**
 * @author Kanomiya
 *
 */
public class Matter implements IMatter
{
	protected Map<IProperty, Object> properties;

	public Matter()
	{
		properties = Maps.newHashMap();

		withProperty(DefaultMatterProperties.AMOUNT, 1);
	}

	@Override
	public <T> boolean hasProperty(IProperty<T> property)
	{
		return properties.containsKey(property);
	}

	@Override
	public <T> T getValue(IProperty<T> property)
	{
		return (T) properties.get(property);
	}

	@Override
	public <T> Matter withProperty(IProperty<T> property, T value)
	{
		properties.put(property, value);
		MatterPropertyEventFactory.fireChangeProperty(this, property, value);
		return this;
	}

	@Override
	public String getDisplayName()
	{
		String type = !hasProperty(DefaultMatterProperties.TYPE) ? "null" : getValue(DefaultMatterProperties.TYPE).getDisplayName();
		String form = !hasProperty(DefaultMatterProperties.FORM) ? "null" : getValue(DefaultMatterProperties.FORM).getDisplayName();

		return type + " <" + form + ">" + (!hasProperty(DefaultMatterProperties.AMOUNT) ? "" : " x" + getValue(DefaultMatterProperties.AMOUNT));
	}

	@Override
	public void addInformation(List<String> tooltip, boolean advanced)
	{

	}

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagCompound nbtProperties = new NBTTagCompound();

		for (IProperty property: properties.keySet())
		{
			ResourceLocation id = MovableMatterCraftAPI.propertyRegistry.inverse().get(property);
			nbtProperties.setTag(id == null ? "null" : id.toString(), property.serializeNBT(properties.get(property)));
		}

		nbt.setTag("properties", nbtProperties);

		return nbt;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbtProperties = nbt.getCompoundTag("properties");

		for (String key: nbtProperties.getKeySet())
		{
			ResourceLocation id = new ResourceLocation(key);

			IProperty property = MovableMatterCraftAPI.propertyRegistry.get(id);

			if (property != null)
			{
				Object value = property.deserializeNBT(nbtProperties.getTag(key));
				if (value != null) withProperty(property, value);
			}
		}

	}



}
