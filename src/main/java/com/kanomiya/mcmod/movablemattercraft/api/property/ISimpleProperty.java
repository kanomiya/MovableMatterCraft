package com.kanomiya.mcmod.movablemattercraft.api.property;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Kanomiya
 *
 */
public abstract class ISimpleProperty<V> implements IProperty<V>
{
	protected Map<IProperty, Object> properties;

	@Override
	public <T> boolean hasProperty(IProperty<T> property)
	{
		if (properties == null) return false;
		return properties.containsKey(property);
	}

	@Override
	public <T> T getValue(IProperty<T> property)
	{
		if (properties == null) return null;
		return (T) properties.get(property);
	}

	@Override
	public <T> ISimpleProperty<V> withProperty(IProperty<T> property, T value)
	{
		if (properties == null) properties = Maps.newHashMap();
		properties.put(property, value);
		return this;
	}

}
