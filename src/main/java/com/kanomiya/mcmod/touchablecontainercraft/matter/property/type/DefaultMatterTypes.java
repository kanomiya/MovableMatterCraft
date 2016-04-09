package com.kanomiya.mcmod.touchablecontainercraft.matter.property.type;


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

}
