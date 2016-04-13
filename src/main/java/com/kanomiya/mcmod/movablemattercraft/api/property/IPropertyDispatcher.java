package com.kanomiya.mcmod.movablemattercraft.api.property;




/**
 * @author Kanomiya
 *
 */
public interface IPropertyDispatcher
{
	<T> boolean hasProperty(IProperty<T> property);

	<T> T getValue(IProperty<T> property);

	<T> IPropertyDispatcher withProperty(IProperty<T> property, T value);

}
