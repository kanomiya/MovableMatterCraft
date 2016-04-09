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
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.Matter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.touchablecontainercraft.matter.property.type.DefaultMatterTypes;


/**
 * @author Kanomiya
 *
 */
public class MatterMappingRegistry
{
	public static final MatterMappingRegistry INSTANCE = new MatterMappingRegistry();

	public BiMap<ItemStack, Supplier<IMatter>> itemRegistry = HashBiMap.create();
	public BiMap<IBlockState, Supplier<IMatter>> blockRegistry = HashBiMap.create();

	protected MatterMappingRegistry() {  }


	public IMatter createFromBlockState(IBlockState blockState)
	{
		if (blockState == null) return null;

		if (blockRegistry.containsKey(blockState))
		{
			IMatter iMatter = blockRegistry.get(blockState).get();
			if (iMatter != null) return iMatter;
		}

		return null;
	}

	public IMatter createFromItemStack(ItemStack stack)
	{
		if (stack == null) return null;

		if (stack.getItem() instanceof ItemBlock)
		{
			IBlockState blockState = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

			IMatter iMatter = createFromBlockState(blockState);
			if (iMatter != null) return iMatter;
		}

		for (ItemStack s: itemRegistry.keySet())
		{
			if (ItemStack.areItemsEqual(stack, s) && ItemStack.areItemStackTagsEqual(stack, s))
			{
				ItemStack matterStack = new ItemStack(TouchableMatterCraft.itemMatter, 1, 0);
				if (matterStack.hasCapability(TouchableMatterCraftAPI.capMatter, null))
				{
					IMatter iMatter = matterStack.getCapability(TouchableMatterCraftAPI.capMatter, null);
					IMatter newMatter = itemRegistry.get(s).get();

					iMatter.deserializeNBT(newMatter.serializeNBT());

					return iMatter;
				}

			}
		}

		return null;
	}

	public void registerDefaultItemMappings() // TODO 認識部分、Creator
	{
		itemRegistry.put(new ItemStack(Items.iron_ingot, 1, 0), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.IRON).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT));
		itemRegistry.put(new ItemStack(Items.gold_ingot, 1, 0), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.GOLD).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT));

	}

	public void registerDefaultBlockMappings()
	{
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_OAK).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_SPRUCE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_BIRCH).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_JUNGLE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_ACACIA).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.WOOD_DARK_OAK).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));

		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.STONE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.STONE_DIORITE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.STONE_GRANITE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));;
		blockRegistry.put(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), () -> new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.STONE_ANDESITE).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));

		blockRegistry.put(Blocks.iron_block.getDefaultState(), () ->  new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.IRON).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));
		blockRegistry.put(Blocks.gold_block.getDefaultState(), () ->  new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.GOLD).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK));

	}


}
