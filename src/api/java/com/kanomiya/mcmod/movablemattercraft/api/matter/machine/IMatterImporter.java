package com.kanomiya.mcmod.movablemattercraft.api.matter.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;

/**
 * @author Kanomiya
 *
 */
public interface IMatterImporter
{
	boolean importMatter(ItemStack matterStack, IMatter iMatter, EnumFacing facing);

}
