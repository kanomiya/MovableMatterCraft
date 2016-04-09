package com.kanomiya.mcmod.touchablecontainercraft.api.matter.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.Matter;

/**
 * @author Kanomiya
 *
 */
public interface IMatterImporter
{
	boolean importMatter(ItemStack matterStack, Matter matter, EnumFacing facing);

}
