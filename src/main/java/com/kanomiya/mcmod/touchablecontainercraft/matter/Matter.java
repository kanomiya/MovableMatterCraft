package com.kanomiya.mcmod.touchablecontainercraft.matter;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.Maps;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.property.IMatterProperty;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.touchablecontainercraft.registry.MatterRegistry;

/**
 * @author Kanomiya
 *
 */
public class Matter implements IMatter
{
	protected Map<IMatterProperty, Object> properties;

	public Matter()
	{
		properties = Maps.newHashMap();

		withProperty(DefaultMatterProperties.AMOUNT, 1);
	}

	@Override
	public <T> boolean hasProperty(IMatterProperty<T> property)
	{
		return properties.containsKey(property);
	}

	@Override
	public <T> T getValue(IMatterProperty<T> property)
	{
		return (T) properties.get(property);
	}

	@Override
	public <T> Matter withProperty(IMatterProperty<T> property, T value)
	{
		properties.put(property, value);
		property.onAdded(this, value);
		return this;
	}

	@Override
	public String getDisplayName()
	{
		String type = !hasProperty(DefaultMatterProperties.TYPE) ? "null" : getValue(DefaultMatterProperties.TYPE).getDisplayName();
		String form = !hasProperty(DefaultMatterProperties.FORM) ? "null" : getValue(DefaultMatterProperties.FORM).getDisplayName();

		return type + " <" + form + ">" + (!hasProperty(DefaultMatterProperties.AMOUNT) ? "x" +getValue(DefaultMatterProperties.AMOUNT) : "");
	}

	@Override
	public void addInformation(List<String> tooltip)
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

		for (IMatterProperty property: properties.keySet())
		{
			ResourceLocation id = MatterRegistry.propertyRegistry.inverse().get(property);
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

			IMatterProperty property = MatterRegistry.propertyRegistry.get(id);
			Object value = property.deserializeNBT(nbtProperties.getTag(key));

			if (property != null && value != null) withProperty(property, value);
		}

	}



}
