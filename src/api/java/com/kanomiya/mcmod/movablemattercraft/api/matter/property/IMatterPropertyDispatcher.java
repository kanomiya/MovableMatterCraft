package com.kanomiya.mcmod.movablemattercraft.api.matter.property;




/**
 * @author Kanomiya
 *
 */
public interface IMatterPropertyDispatcher
{
	<T> boolean hasProperty(IMatterProperty<T> property);

	<T> T getValue(IMatterProperty<T> property);

	<T> IMatterPropertyDispatcher withProperty(IMatterProperty<T> property, T value);

}
