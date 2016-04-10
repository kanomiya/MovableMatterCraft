package com.kanomiya.mcmod.movablemattercraft.matter.property.type;

import java.util.List;

import com.google.common.collect.Lists;



/**
 * @author Kanomiya
 *
 */
public class SimpleMatterType implements IMatterType
{
	protected String unlocalizedName;
	protected List<IMatterType> variants;

	public SimpleMatterType(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
		variants = Lists.newArrayList();
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getUnlocalizedName()
	{
		return "matter.type." + unlocalizedName + ".name";
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void registerVariant(IMatterType variant)
	{
		variants.add(variant);
	}


	/**
	* @inheritDoc
	*/
	@Override
	public List<IMatterType> getVariants(boolean exclusive)
	{
		if (exclusive)
		{
			List<IMatterType> result = Lists.newArrayList(variants.iterator());

			for (IMatterType type: variants)
			{
				List<IMatterType> exVariants = type.getVariants(true);
				if (exVariants != null && !exVariants.isEmpty()) exVariants.stream().filter((t) -> !result.contains(t)).forEach(result::add);;
			}

			return result;
		}

		return variants;
	}

	@Override
	public boolean isParentOf(IMatterType type, boolean exclusive)
	{
		for (IMatterType t: variants)
		{
			if (t == type) return true;
		}

		if (exclusive)
		{
			for (IMatterType t: variants)
			{
				List<IMatterType> exVariants = t.getVariants(true);
				for (IMatterType t2: exVariants)
				{
					if (t2 == type) return true;
				}
			}
		}

		return false;
	}


}
