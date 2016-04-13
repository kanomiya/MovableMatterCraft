package com.kanomiya.mcmod.movablemattercraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * @author Kanomiya
 *
 */
public class MatterRecipe implements IRecipe
{

	/**
	* @inheritDoc
	*/
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public int getRecipeSize()
	{
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ItemStack getRecipeOutput()
	{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}

