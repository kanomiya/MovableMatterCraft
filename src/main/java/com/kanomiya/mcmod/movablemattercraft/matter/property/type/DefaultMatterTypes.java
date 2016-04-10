package com.kanomiya.mcmod.movablemattercraft.matter.property.type;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


/**
 * @author Kanomiya
 *
 */
public class DefaultMatterTypes
{
	public static final IMatterType WOOD = new SimpleMatterType("wood"); // BlockPlanks
	public static final IMatterType WOOD_OAK = new SimpleMatterType("wood_oak").derive(WOOD);
	public static final IMatterType WOOD_SPRUCE = new SimpleMatterType("wood_spruce").derive(WOOD);
	public static final IMatterType WOOD_BIRCH = new SimpleMatterType("wood_birch").derive(WOOD);
	public static final IMatterType WOOD_JUNGLE = new SimpleMatterType("wood_jungle").derive(WOOD);
	public static final IMatterType WOOD_ACACIA = new SimpleMatterType("wood_acacia").derive(WOOD);
	public static final IMatterType WOOD_DARK_OAK = new SimpleMatterType("wood_dark_oak").derive(WOOD);

	public static final IMatterType STONE = new SimpleMatterType("stone"); // BlockStone
	public static final IMatterType STONE_GRANITE = new SimpleMatterType("stone_granite").derive(STONE);
	public static final IMatterType STONE_DIORITE = new SimpleMatterType("stone_diorite").derive(STONE);
	public static final IMatterType STONE_ANDESITE = new SimpleMatterType("stone_andesite").derive(STONE);

	public static final IMatterType IRON = new SimpleMatterType("iron");
	public static final IMatterType GOLD = new SimpleMatterType("gold");
	public static final IMatterType DIAMOND = new SimpleMatterType("diamond");


	public static final BiMap<BlockPlanks.EnumType, IMatterType> WOOD_MAPPINGS = HashBiMap.create();
	public static final BiMap<BlockStone.EnumType, IMatterType> STONE_MAPPINGS = HashBiMap.create();

	public static void registerDefaultMappings()
	{
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.OAK, WOOD_OAK);
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.SPRUCE, WOOD_SPRUCE);
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.BIRCH, WOOD_BIRCH);
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.JUNGLE, WOOD_JUNGLE);
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.ACACIA, WOOD_ACACIA);
		WOOD_MAPPINGS.put(BlockPlanks.EnumType.DARK_OAK, WOOD_DARK_OAK);

		STONE_MAPPINGS.put(BlockStone.EnumType.STONE, STONE);
		STONE_MAPPINGS.put(BlockStone.EnumType.GRANITE, STONE_GRANITE);
		STONE_MAPPINGS.put(BlockStone.EnumType.DIORITE, STONE_DIORITE);
		STONE_MAPPINGS.put(BlockStone.EnumType.ANDESITE, STONE_ANDESITE);

	}


}
