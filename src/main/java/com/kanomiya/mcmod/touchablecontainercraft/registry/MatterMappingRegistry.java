package com.kanomiya.mcmod.touchablecontainercraft.registry;

import java.util.function.Supplier;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.kanomiya.mcmod.touchablecontainercraft.TouchableMatterCraft;
import com.kanomiya.mcmod.touchablecontainercraft.api.TouchableMatterCraftAPI;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.Matter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.form.DefaultMatterForms;
import com.kanomiya.mcmod.touchablecontainercraft.matter.type.DefaultMatterTypes;


/**
 * @author Kanomiya
 *
 */
public class MatterMappingRegistry
{
	public static final MatterMappingRegistry INSTANCE = new MatterMappingRegistry();

	public BiMap<ItemStack, Supplier<Matter>> itemRegistry = HashBiMap.create();
	public BiMap<IBlockState, Supplier<Matter>> blockRegistry = HashBiMap.create();

	protected MatterMappingRegistry() {  }


	public Matter createFromBlockState(IBlockState blockState)
	{
		if (blockState == null) return null;

		if (blockRegistry.containsKey(blockState))
		{
			Matter matter = blockRegistry.get(blockState).get();
			if (matter != null) return matter;
		}

		return null;
	}

	public Matter createFromItemStack(ItemStack stack)
	{
		if (stack == null) return null;

		if (stack.getItem() instanceof ItemBlock)
		{
			IBlockState blockState = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

			Matter matter = createFromBlockState(blockState);
			if (matter != null) return matter;
		}

		for (ItemStack s: itemRegistry.keySet())
		{
			if (ItemStack.areItemsEqual(stack, s) && ItemStack.areItemStackTagsEqual(stack, s))
			{
				ItemStack matterStack = new ItemStack(TouchableMatterCraft.itemMatter, 1, 0);
				if (matterStack.hasCapability(TouchableMatterCraftAPI.capMatter, null))
				{
					Matter matter = matterStack.getCapability(TouchableMatterCraftAPI.capMatter, null);
					Matter newMatter = itemRegistry.get(s).get();

					matter.deserializeNBT(newMatter.serializeNBT());

					return matter;
				}

			}
		}

		return null;
	}

	public void registerDefaultItemMappings() // TODO 認識部分、Creator
	{
		itemRegistry.put(new ItemStack(Items.iron_ingot, 1, 0), () -> new Matter(DefaultMatterTypes.IRON, DefaultMatterForms.INGOT));
		itemRegistry.put(new ItemStack(Items.gold_ingot, 1, 0), () -> new Matter(DefaultMatterTypes.GOLD, DefaultMatterForms.INGOT));

	}

	public void registerDefaultBlockMappings()
	{
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK), () -> new Matter(DefaultMatterTypes.WOOD_OAK, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE), () -> new Matter(DefaultMatterTypes.WOOD_SPRUCE, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH), () -> new Matter(DefaultMatterTypes.WOOD_BIRCH, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE), () -> new Matter(DefaultMatterTypes.WOOD_JUNGLE, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA), () -> new Matter(DefaultMatterTypes.WOOD_ACACIA, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK), () -> new Matter(DefaultMatterTypes.WOOD_DARK_OAK, DefaultMatterForms.BLOCK));

		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), () -> new Matter(DefaultMatterTypes.STONE, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), () -> new Matter(DefaultMatterTypes.STONE_DIORITE, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), () -> new Matter(DefaultMatterTypes.STONE_GRANITE, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), () -> new Matter(DefaultMatterTypes.STONE_ANDESITE, DefaultMatterForms.BLOCK));

		blockRegistry.put(Blocks.iron_block.getDefaultState(), () -> new Matter(DefaultMatterTypes.IRON, DefaultMatterForms.BLOCK));

	}


}
