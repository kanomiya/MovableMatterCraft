package com.kanomiya.mcmod.movablemattercraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.apix.MovableMatterCraftAPIX;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.form.IMatterForm;
import com.kanomiya.mcmod.movablemattercraft.apix.matter.property.type.IMatterType;
import com.kanomiya.mcmod.movablemattercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
public class ItemMatter extends Item
{

	public ItemMatter()
	{
		setRegistryName(new ResourceLocation(MovableMatterCraftAPIX.DOMAIN_NAME, "itemMagicMatter"));
		setUnlocalizedName("itemMatter");

		setCreativeTab(MovableMatterCraft.tab);
		setHasSubtypes(true);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{

		for (IMatterType type: MovableMatterCraftAPIX.typeRegistry.values())
		{
			for (IMatterForm form: MovableMatterCraftAPIX.formRegistry.values())
			{
				ItemStack stack = new ItemStack(this, 1, 0);

				if (MovableMatterCraftAPI.hasMatter(stack, null))
				{
					MovableMatterCraftAPI.getMatter(stack, null)
						.withProperty(DefaultMatterProperties.TYPE, type)
						.withProperty(DefaultMatterProperties.FORM, form);

					subItems.add(stack);
				}

			}
		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		if (MovableMatterCraftAPI.hasMatter(stack, null))
		{
			IMatter iMatter = MovableMatterCraftAPI.getMatter(stack, null);
			return iMatter.getDisplayName();
		}

		return super.getItemStackDisplayName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if (MovableMatterCraftAPI.hasMatter(stack, null))
		{
			IMatter iMatter = MovableMatterCraftAPI.getMatter(stack, null);
			iMatter.addInformation(tooltip, advanced);
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		float f = 1.0F;
		float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * f;
		float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * f;
		double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * f;
		double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * f + playerIn.getEyeHeight();
		double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * f;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3d vec3d1 = vec3d.addVector(f7 * d3, f6 * d3, f8 * d3);
		RayTraceResult raytraceresult = worldIn.rayTraceBlocks(vec3d, vec3d1, true);

		if (raytraceresult == null)
		{
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
		else
		{
			Vec3d vec3d2 = playerIn.getLook(f);
			boolean flag = false;
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getEntityBoundingBox().addCoord(vec3d2.xCoord * d3, vec3d2.yCoord * d3, vec3d2.zCoord * d3).expandXyz(1.0D));

			for (int i = 0; i < list.size(); ++i)
			{
				Entity entity = list.get(i);

				if (entity.canBeCollidedWith())
				{
					AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expandXyz(entity.getCollisionBorderSize());

					if (axisalignedbb.isVecInside(vec3d))
					{
						flag = true;
					}
				}
			}

			if (flag)
			{
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
			{
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			else
			{
				EntityMatter entity = new EntityMatter(worldIn, raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
				entity.rotationYaw = playerIn.rotationYaw;

				if (!worldIn.getCubes(entity, entity.getEntityBoundingBox().expandXyz(-0.1D)).isEmpty())
				{
					return new ActionResult(EnumActionResult.FAIL, itemStackIn);
				}
				else
				{
					if (!worldIn.isRemote)
					{
						ItemStack itemStack = itemStackIn.copy();
						itemStack.stackSize = 1;

						entity.setMatterStack(itemStack);
						worldIn.spawnEntityInWorld(entity);
					}

					if (!playerIn.capabilities.isCreativeMode)
					{
						--itemStackIn.stackSize;
					}

					playerIn.addStat(StatList.func_188057_b(this));
					return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
				}
			}
		}
	}

}
