package com.kanomiya.mcmod.movablemattercraft;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
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

import org.apache.logging.log4j.Logger;

import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.block.BlockMatterCreator;
import com.kanomiya.mcmod.movablemattercraft.block.BlockMatterCutter;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.movablemattercraft.client.render.matter.RenderMatter;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;
import com.kanomiya.mcmod.movablemattercraft.item.ItemMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.Matter;
import com.kanomiya.mcmod.movablemattercraft.matter.event.MatterModelBakeEvent;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.DefaultMatterForms;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.DefaultMatterTypes;
import com.kanomiya.mcmod.movablemattercraft.matter.property.type.IMatterType;
import com.kanomiya.mcmod.movablemattercraft.network.PacketHandler;
import com.kanomiya.mcmod.movablemattercraft.registry.MatterMappingRegistry;
import com.kanomiya.mcmod.movablemattercraft.registry.MatterRegistry;
import com.kanomiya.mcmod.movablemattercraft.tileentity.TileEntityMatterCutter;

@Mod(modid = MovableMatterCraft.MODID)
public class MovableMatterCraft
{
	public static final String SHORT_MODID = "movablemattercraft";
	public static final String MODID = "com.kanomiya.mcmod." + MovableMatterCraft.SHORT_MODID;

	@Mod.Instance(MODID)
	public static MovableMatterCraft instance;

	public static CreativeTabs tab = new CreativeTabs(MODID) {
		@Override @SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return MovableMatterCraft.itemMatter;
		}
	};

	public static ItemMatter itemMatter = new ItemMatter();
	public static BlockMatterCreator blockMatterCreator = new BlockMatterCreator();
	public static BlockMatterCutter blockMatterCutter = new BlockMatterCutter();

	public static Logger logger;


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		GameRegistry.register(itemMatter);
		GameRegistry.register(blockMatterCreator);
		GameRegistry.register(new ItemBlock(blockMatterCreator).setRegistryName(blockMatterCreator.getRegistryName()));
		GameRegistry.register(blockMatterCutter);
		GameRegistry.register(new ItemBlock(blockMatterCutter).setRegistryName(blockMatterCutter.getRegistryName()));

		GameRegistry.registerTileEntity(TileEntityMatterCutter.class, MovableMatterCraft.MODID + ":tileEntityMatterCutter");

		int eId = -1;
		EntityRegistry.registerModEntity(EntityMatter.class, "entityMatter", ++eId, MovableMatterCraft.instance, 64, 1, false);

		MatterRegistry.registerDefaultProperties();
		MatterRegistry.registerDefaultTypes();
		MatterRegistry.registerDefaultForms();
		MatterRegistry.registerDefaultModels();

		MatterMappingRegistry.INSTANCE.registerDefaultItemMappings();
		MatterMappingRegistry.INSTANCE.registerDefaultBlockMappings();

		if (event.getSide().isClient())
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityMatter.class, RenderMatter::new);
			// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMatter.class, new TESRMatter());
			ModelLoader.setCustomModelResourceLocation(MovableMatterCraft.itemMatter, 0, new ModelResourceLocation(MovableMatterCraft.SHORT_MODID + ":itemMatter", "inventory"));

			ForgeHooksClient.renderTileItem(itemMatter, 0);

			MatterRegistry.registerDefaultRenders();


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
		if (event.getItem() == MovableMatterCraft.itemMatter) event.addCapability(new ResourceLocation(MovableMatterCraft.MODID), new Matter());

	}

	@SubscribeEvent
	public void onMatterModelBake(MatterModelBakeEvent event)
	{
		IMatter matter = event.getMatter();
		event.setMatterModel(new ModelMatterBlock(Blocks.fire.getDefaultState()));

		IMatterType matterType = matter.getValue(DefaultMatterProperties.TYPE);
		IMatterForm matterForm = matter.getValue(DefaultMatterProperties.FORM);

		if (matterType == DefaultMatterTypes.WOOD || matterType.isVariantOf(DefaultMatterTypes.WOOD, true))
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
				if (matterForm == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, type)));
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
				if (matterForm == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, type)));
			}
		}


		else if (matterType == DefaultMatterTypes.IRON)
		{
			if (matterForm == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.iron_block.getDefaultState()));
			else if (matterForm == DefaultMatterForms.INGOT) event.setMatterModel(new ModelMatterIngot(new ResourceLocation(MovableMatterCraft.SHORT_MODID +":textures/matter/ingot/iron.png")));
		}

		else if (matterType == DefaultMatterTypes.GOLD)
		{
			if (matterForm == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.gold_block.getDefaultState()));
			else if (matterForm == DefaultMatterForms.INGOT) event.setMatterModel(new ModelMatterIngot(new ResourceLocation(MovableMatterCraft.SHORT_MODID +":textures/matter/ingot/gold.png")));
		}

		else if (matterType == DefaultMatterTypes.DIAMOND)
		{
			if (matterForm == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.diamond_block.getDefaultState()));
		}

	}

}
