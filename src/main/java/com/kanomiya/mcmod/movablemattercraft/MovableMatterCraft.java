package com.kanomiya.mcmod.movablemattercraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.api.matter.event.MatterConvertEvent;
import com.kanomiya.mcmod.movablemattercraft.api.matter.event.MatterModelBakeEvent;
import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.IMatterType;
import com.kanomiya.mcmod.movablemattercraft.block.BlockMatterConverter;
import com.kanomiya.mcmod.movablemattercraft.block.BlockMatterCutter;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.RenderMatter;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;
import com.kanomiya.mcmod.movablemattercraft.item.ItemMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.Matter;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.DefaultMatterTypes;
import com.kanomiya.mcmod.movablemattercraft.network.PacketHandler;

@Mod(modid = MovableMatterCraftAPIX.MODID)
public class MovableMatterCraft
{
	@Mod.Instance(MovableMatterCraftAPIX.MODID)
	public static MovableMatterCraft instance;

	public static CreativeTabs tab = new CreativeTabs(MovableMatterCraftAPIX.MODID) {
		@Override @SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return MovableMatterCraft.itemMatter;
		}
	};

	public static ItemMatter itemMatter = new ItemMatter();
	public static BlockMatterConverter blockMatterConverter = new BlockMatterConverter();
	public static BlockMatterCutter blockMatterCutter = new BlockMatterCutter();



	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MovableMatterCraftAPIX.logger = event.getModLog();

		GameRegistry.register(itemMatter);
		GameRegistry.register(blockMatterConverter);
		GameRegistry.register(new ItemBlock(blockMatterConverter).setRegistryName(blockMatterConverter.getRegistryName()));
		GameRegistry.register(blockMatterCutter);
		GameRegistry.register(new ItemBlock(blockMatterCutter).setRegistryName(blockMatterCutter.getRegistryName()));

		int eId = -1;
		EntityRegistry.registerModEntity(EntityMatter.class, "entityMatter", ++eId, MovableMatterCraft.instance, 64, 1, false);

		DefaultMatterTypes.registerDefaultMappings();

		MMCInits.initMMC();

		if (event.getSide().isClient())
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityMatter.class, RenderMatter::new);
			// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMatter.class, new TESRMatter());

			ModelLoader.setCustomModelResourceLocation(MovableMatterCraft.itemMatter, 0, new ModelResourceLocation(MovableMatterCraftAPIX.DOMAIN_NAME + ":itemMatter", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MovableMatterCraft.blockMatterConverter), 0, new ModelResourceLocation(MovableMatterCraftAPIX.DOMAIN_NAME + ":blockMatterConverter", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MovableMatterCraft.blockMatterCutter), 0, new ModelResourceLocation(MovableMatterCraftAPIX.DOMAIN_NAME + ":blockMatterCutter", "inventory"));

		}

		CapabilityManager.INSTANCE.register(Matter.class, new IMatter.Storage(), Matter::new);
		MinecraftForge.EVENT_BUS.register(this);

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		PacketHandler.init();


	}

	/*
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event)
	{
		event.getModelRegistry().putObject();
	}
	*/

	@SubscribeEvent
	public void onAttachItemCapabilities(AttachCapabilitiesEvent.Item event)
	{
		if (event.getItem() == MovableMatterCraft.itemMatter) event.addCapability(new ResourceLocation(MovableMatterCraftAPIX.MODID), new Matter());

	}

	@SubscribeEvent
	public void onMatterModelBake(MatterModelBakeEvent event)
	{
		IMatter matter = event.getMatter();
		matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.fire.getDefaultState()));

		IMatterType matterType = matter.getValue(DefaultMatterProperties.TYPE);
		IMatterForm matterForm = matter.getValue(DefaultMatterProperties.FORM);

		if (matterType == null || matterForm == null) return ;


		if (matterForm == DefaultMatterForms.INGOT)
		{
			String key = MovableMatterCraftAPIX.DOMAIN_NAME + ":textures/matter/ingot/" + matterType.getUnlocalizedName() + ".png";
			matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterIngot(new ResourceLocation(key)));

		}

		else if (matterType == DefaultMatterTypes.WOOD || matterType.isVariantOf(DefaultMatterTypes.WOOD, true))
		{
			BlockPlanks.EnumType type = null;

			if (matterType == DefaultMatterTypes.WOOD_OAK) type = BlockPlanks.EnumType.OAK;
			else if (matterType == DefaultMatterTypes.WOOD_SPRUCE) type = BlockPlanks.EnumType.SPRUCE;
			else if (matterType == DefaultMatterTypes.WOOD_BIRCH) type = BlockPlanks.EnumType.BIRCH;
			else if (matterType == DefaultMatterTypes.WOOD_JUNGLE) type = BlockPlanks.EnumType.JUNGLE;
			else if (matterType == DefaultMatterTypes.WOOD_ACACIA) type = BlockPlanks.EnumType.ACACIA;
			else if (matterType == DefaultMatterTypes.WOOD_DARK_OAK) type = BlockPlanks.EnumType.DARK_OAK;

			if (type != null)
			{
				if (matterForm == DefaultMatterForms.BLOCK) matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, type)));
			}
		}

		else if (matterType == DefaultMatterTypes.STONE || matterType.isVariantOf(DefaultMatterTypes.STONE, true))
		{
			BlockStone.EnumType type = null;

			if (matterType == DefaultMatterTypes.STONE) type = BlockStone.EnumType.STONE;
			else if (matterType == DefaultMatterTypes.STONE_GRANITE) type = BlockStone.EnumType.GRANITE;
			else if (matterType == DefaultMatterTypes.STONE_DIORITE) type = BlockStone.EnumType.DIORITE;
			else if (matterType == DefaultMatterTypes.STONE_ANDESITE) type = BlockStone.EnumType.ANDESITE;

			if (type != null)
			{
				if (matterForm == DefaultMatterForms.BLOCK) matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, type)));
			}
		}


		else if (matterType == DefaultMatterTypes.IRON)
		{
			if (matterForm == DefaultMatterForms.BLOCK) matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.iron_block.getDefaultState()));
		}

		else if (matterType == DefaultMatterTypes.GOLD)
		{
			if (matterForm == DefaultMatterForms.BLOCK) matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.gold_block.getDefaultState()));
		}

		else if (matterType == DefaultMatterTypes.DIAMOND)
		{
			if (matterForm == DefaultMatterForms.BLOCK) matter.withProperty(DefaultMatterProperties.MODEL, new ModelMatterBlock(Blocks.diamond_block.getDefaultState()));
		}

	}


	@SubscribeEvent
	public void onMatterConvertToItemStack(MatterConvertEvent.ToItemStack event)
	{
		IMatter matter = event.getMatter();
		IMatterType matterType = matter.getValue(DefaultMatterProperties.TYPE);
		IMatterForm matterForm = matter.getValue(DefaultMatterProperties.FORM);
		int amount = matter.getValue(DefaultMatterProperties.AMOUNT);

		if (matterType == null || matterForm == null || amount == 0) return ;


		if (matterType == DefaultMatterTypes.WOOD || matterType.isVariantOf(DefaultMatterTypes.WOOD, true))
		{
			BlockPlanks.EnumType type = DefaultMatterTypes.WOOD_MAPPINGS.inverse().get(matterType);

			if (type != null)
			{
				if (matterForm == DefaultMatterForms.BLOCK)
				{
					Block block = Blocks.planks;
					IBlockState blockState = block.getDefaultState().withProperty(BlockPlanks.VARIANT, type);
					ItemStack stack = new ItemStack(blockState.getBlock(), 1, block.getMetaFromState(blockState));
					stack.stackSize = Math.min(amount, stack.getMaxStackSize());
					matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

					event.setItemStack(stack);
				}
			}
		}

		else if (matterType == DefaultMatterTypes.STONE || matterType.isVariantOf(DefaultMatterTypes.STONE, true))
		{
			BlockStone.EnumType type = DefaultMatterTypes.STONE_MAPPINGS.inverse().get(matterType);

			if (type != null)
			{
				if (matterForm == DefaultMatterForms.BLOCK)
				{
					Block block = Blocks.stone;
					IBlockState blockState = block.getDefaultState().withProperty(BlockStone.VARIANT, type);
					ItemStack stack = new ItemStack(blockState.getBlock(), 1, block.getMetaFromState(blockState));
					stack.stackSize = Math.min(amount, stack.getMaxStackSize());
					matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

					event.setItemStack(stack);
				}
			}
		}


		else if (matterType == DefaultMatterTypes.IRON)
		{
			if (matterForm == DefaultMatterForms.BLOCK)
			{
				ItemStack stack = new ItemStack(Blocks.iron_block, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
			else if (matterForm == DefaultMatterForms.INGOT)
			{
				ItemStack stack = new ItemStack(Items.iron_ingot, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
		}

		else if (matterType == DefaultMatterTypes.GOLD)
		{
			if (matterForm == DefaultMatterForms.BLOCK)
			{
				ItemStack stack = new ItemStack(Blocks.gold_block, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
			else if (matterForm == DefaultMatterForms.INGOT)
			{
				ItemStack stack = new ItemStack(Items.gold_ingot, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
		}

		else if (matterType == DefaultMatterTypes.DIAMOND)
		{
			if (matterForm == DefaultMatterForms.BLOCK)
			{
				ItemStack stack = new ItemStack(Blocks.diamond_block, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
			else if (matterForm == DefaultMatterForms.INGOT)
			{
				ItemStack stack = new ItemStack(Items.diamond, 1);
				stack.stackSize = Math.min(amount, stack.getMaxStackSize());
				matter.withProperty(DefaultMatterProperties.AMOUNT, amount -stack.stackSize);

				event.setItemStack(stack);
			}
		}


	}

	@SubscribeEvent
	public void onMatterConvertToMatter(MatterConvertEvent.ToMatter event)
	{
		ItemStack stack = event.getItemStack();
		int amount = stack.stackSize;

		if (stack == null || amount == 0) return ;

		Item item = stack.getItem();
		int meta = item.getMetadata(stack);

		if (item instanceof ItemBlock)
		{
			if (item == Item.getItemFromBlock(Blocks.planks))
			{
				IBlockState blockState = Blocks.planks.getStateFromMeta(meta);

				if (blockState != null)
				{
					IMatterType type = DefaultMatterTypes.WOOD_MAPPINGS.get(blockState.getValue(BlockPlanks.VARIANT));

					if (type != null)
					{
						int actualAmount = Math.min(amount, DefaultMatterForms.BLOCK.getMaxAmount());
						stack.stackSize -= actualAmount;
						event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, type).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
					}
				}
			}

			else if (item == Item.getItemFromBlock(Blocks.stone))
			{
				IBlockState blockState = Blocks.stone.getStateFromMeta(meta);

				if (blockState != null)
				{
					IMatterType type = DefaultMatterTypes.STONE_MAPPINGS.get(blockState.getValue(BlockStone.VARIANT));

					if (type != null)
					{
						int actualAmount = Math.min(amount, DefaultMatterForms.BLOCK.getMaxAmount());
						stack.stackSize -= actualAmount;
						event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, type).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
					}
				}
			}

			else if (item == Item.getItemFromBlock(Blocks.iron_block))
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.BLOCK.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.IRON).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}

			else if (item == Item.getItemFromBlock(Blocks.gold_block))
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.BLOCK.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.GOLD).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}

			else if (item == Item.getItemFromBlock(Blocks.diamond_block))
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.BLOCK.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.DIAMOND).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.BLOCK).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}

		}

		else
		{
			if (item == Items.iron_ingot)
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.INGOT.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.IRON).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}
			else if (item == Items.gold_ingot)
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.INGOT.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.GOLD).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}

			else if (item == Items.diamond)
			{
				int actualAmount = Math.min(amount, DefaultMatterForms.INGOT.getMaxAmount());
				stack.stackSize -= actualAmount;
				event.setMatter(new Matter().withProperty(DefaultMatterProperties.TYPE, DefaultMatterTypes.DIAMOND).withProperty(DefaultMatterProperties.FORM, DefaultMatterForms.INGOT).withProperty(DefaultMatterProperties.AMOUNT, actualAmount));
			}

		}


	}




}
