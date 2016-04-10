package com.kanomiya.mcmod.movablemattercraft.api.matter.property;




/**
 * @author Kanomiya
 *
 */
public interface IMatterPropertyDispatcher
{
	// TODO: getType getForm -> Matter#withProperty getValue

	<T> boolean hasProperty(IMatterProperty<T> property);

	<T> T getValue(IMatterProperty<T> property);

	<T> IMatterPropertyDispatcher withProperty(IMatterProperty<T> property, T value);

}
