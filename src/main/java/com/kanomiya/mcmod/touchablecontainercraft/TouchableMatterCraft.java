package com.kanomiya.mcmod.touchablecontainercraft;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.Matter;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.event.MatterModelBakeEvent;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.matter.ModelMatterBlock;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.matter.ModelMatterIngot;
import com.kanomiya.mcmod.touchablecontainercraft.client.render.matter.RenderMatter;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;
import com.kanomiya.mcmod.touchablecontainercraft.item.ItemMatter;
import com.kanomiya.mcmod.touchablecontainercraft.matter.form.DefaultMatterForms;
import com.kanomiya.mcmod.touchablecontainercraft.matter.type.DefaultMatterTypes;
import com.kanomiya.mcmod.touchablecontainercraft.network.PacketHandler;
import com.kanomiya.mcmod.touchablecontainercraft.registry.MatterRegistry;

@Mod(modid = TouchableMatterCraft.MODID)
public class TouchableMatterCraft
{
	public static final String SHORT_MODID = "touchablemattercraft";
	public static final String MODID = "com.kanomiya.mcmod." + TouchableMatterCraft.SHORT_MODID;

	@Mod.Instance(MODID)
	public static TouchableMatterCraft instance;

	public static CreativeTabs tab = new CreativeTabs(MODID) {
		@Override @SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return TouchableMatterCraft.itemMatter;
		}
	};

	public static ItemMatter itemMatter = new ItemMatter();

	public static Logger logger;


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		GameRegistry.register(itemMatter);

		int eId = -1;
		EntityRegistry.registerModEntity(EntityMatter.class, "entityMatter", ++eId, TouchableMatterCraft.instance, 64, 1, false);

		MatterRegistry.INSTANCE.registerDefaultTypes();
		MatterRegistry.INSTANCE.registerDefaultForms();
		MatterRegistry.INSTANCE.registerDefaultModels();

		if (event.getSide().isClient())
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityMatter.class, RenderMatter::new);
			// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMatter.class, new TESRMatter());
			ModelLoader.setCustomModelResourceLocation(TouchableMatterCraft.itemMatter, 0, new ModelResourceLocation(TouchableMatterCraft.SHORT_MODID + ":itemMatter", "inventory"));

			ForgeHooksClient.renderTileItem(itemMatter, 0);

			MatterRegistry.INSTANCE.registerDefaultRenders();


		}

		CapabilityManager.INSTANCE.register(Matter.class, new Matter.Storage(), () -> new Matter(DefaultMatterTypes.STONE, DefaultMatterForms.BLOCK));
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
		if (event.getItem() == TouchableMatterCraft.itemMatter) event.addCapability(new ResourceLocation(TouchableMatterCraft.MODID), new Matter(DefaultMatterTypes.STONE, DefaultMatterForms.BLOCK));

	}

	@SubscribeEvent
	public void onMatterModelBake(MatterModelBakeEvent event)
	{
		Matter matter = event.getMatter();
		event.setMatterModel(new ModelMatterBlock(Blocks.fire.getDefaultState()));

		if (matter.getMatterType() == DefaultMatterTypes.WOOD || matter.getMatterType().isVariantOf(DefaultMatterTypes.WOOD, true))
		{
			BlockPlanks.EnumType type = null;

			if (matter.getMatterType() == DefaultMatterTypes.WOOD_OAK) type = BlockPlanks.EnumType.OAK;
			else if (matter.getMatterType() == DefaultMatterTypes.WOOD_SPRUCE) type = BlockPlanks.EnumType.SPRUCE;
			else if (matter.getMatterType() == DefaultMatterTypes.WOOD_BIRCH) type = BlockPlanks.EnumType.BIRCH;
			else if (matter.getMatterType() == DefaultMatterTypes.WOOD_JUNGLE) type = BlockPlanks.EnumType.JUNGLE;
			else if (matter.getMatterType() == DefaultMatterTypes.WOOD_ACACIA) type = BlockPlanks.EnumType.ACACIA;
			else if (matter.getMatterType() == DefaultMatterTypes.WOOD_DARK_OAK) type = BlockPlanks.EnumType.DARK_OAK;

			if (type != null)
			{
				if (matter.getMatterForm() == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, type)));
			}
		}

		else if (matter.getMatterType() == DefaultMatterTypes.STONE || matter.getMatterType().isVariantOf(DefaultMatterTypes.STONE, true))
		{
			BlockStone.EnumType type = null;

			if (matter.getMatterType() == DefaultMatterTypes.STONE) type = BlockStone.EnumType.STONE;
			else if (matter.getMatterType() == DefaultMatterTypes.STONE_GRANITE) type = BlockStone.EnumType.GRANITE;
			else if (matter.getMatterType() == DefaultMatterTypes.STONE_DIORITE) type = BlockStone.EnumType.DIORITE;
			else if (matter.getMatterType() == DefaultMatterTypes.STONE_ANDESITE) type = BlockStone.EnumType.ANDESITE;

			if (type != null)
			{
				if (matter.getMatterForm() == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, type)));
			}
		}


		else if (matter.getMatterType() == DefaultMatterTypes.IRON)
		{
			if (matter.getMatterForm() == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.iron_block.getDefaultState()));
			else if (matter.getMatterForm() == DefaultMatterForms.INGOT) event.setMatterModel(new ModelMatterIngot(new ResourceLocation(TouchableMatterCraft.SHORT_MODID +":textures/matter/ingot/iron.png")));
		}

		else if (matter.getMatterType() == DefaultMatterTypes.GOLD)
		{
			if (matter.getMatterForm() == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.gold_block.getDefaultState()));
		}

		else if (matter.getMatterType() == DefaultMatterTypes.DIAMOND)
		{
			if (matter.getMatterForm() == DefaultMatterForms.BLOCK) event.setMatterModel(new ModelMatterBlock(Blocks.diamond_block.getDefaultState()));
		}

	}

}
