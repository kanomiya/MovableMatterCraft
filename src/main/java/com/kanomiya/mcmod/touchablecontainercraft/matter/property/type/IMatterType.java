package com.kanomiya.mcmod.touchablecontainercraft.matter.property.type;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.util.text.translation.I18n;

/**
 * @author Kanomiya
 *
 */
public interface IMatterType
{
	String getUnlocalizedName();

	default String getDisplayName()
	{
		return I18n.translateToLocal(getUnlocalizedName());
	}

	void registerVariant(IMatterType variant);

	@Nonnull
	List<IMatterType> getVariants(boolean exclusive);

	boolean isParentOf(IMatterType type, boolean exclusive);

	default boolean isVariantOf(IMatterType type, boolean exclusive)
	{
		return type.isParentOf(this, exclusive);
	}

	/**
	 *
	 * 親の変形として登録します
	 *
	 * @param parent 親
	 * @return this 自分自身
	 */
	default IMatterType derive(IMatterType parent)
	{
		parent.registerVariant(this);
		return this;
	}

}
