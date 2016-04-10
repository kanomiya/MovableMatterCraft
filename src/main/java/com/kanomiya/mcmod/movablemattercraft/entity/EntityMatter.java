package com.kanomiya.mcmod.movablemattercraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.common.base.Optional;
import com.kanomiya.mcmod.movablemattercraft.MovableMatterCraft;
import com.kanomiya.mcmod.movablemattercraft.api.MovableMatterCraftAPI;
import com.kanomiya.mcmod.movablemattercraft.api.matter.IMatter;
import com.kanomiya.mcmod.movablemattercraft.matter.property.DefaultMatterProperties;
import com.kanomiya.mcmod.movablemattercraft.matter.property.form.IMatterForm;

/**
 * @author Kanomiya
 *
 */
public class EntityMatter extends Entity
{
	private static final DataParameter<Optional<ItemStack>> MATTER_STACK = EntityDataManager.<Optional<ItemStack>>createKey(EntityMatter.class, DataSerializers.OPTIONAL_ITEM_STACK);

	/**
	 * @param worldIn
	 */
	public EntityMatter(World worldIn) {
		super(worldIn);

		setPosition(0, 0, 0);
		setSize(1f, 1f);
		preventEntitySpawning = true;

	}

	public EntityMatter(World worldIn, double posX, double posY, double posZ)
	{
		this(worldIn);
		setPosition(posX, posY, posZ);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	public EntityMatter(World worldIn, double posX, double posY, double posZ, ItemStack matterStack)
	{
		this(worldIn, posX, posY, posZ);

		setMatterStack(matterStack);
	}

	public EntityMatter(World worldIn, double posX, double posY, double posZ, IMatter iMatter)
	{
		this(worldIn, posX, posY, posZ);

		ItemStack matterStack = new ItemStack(MovableMatterCraft.itemMatter, 1, 0);
		if (MovableMatterCraftAPI.hasMatter(matterStack, null))
		{
			MovableMatterCraftAPI.getMatter(matterStack, null).deserializeNBT(iMatter.serializeNBT());;
		}

		setMatterStack(matterStack);
	}

	/**
	* @inheritDoc
	*/
	@Override
	protected void entityInit()
	{
		dataWatcher.register(MATTER_STACK, Optional.of(new ItemStack(MovableMatterCraft.itemMatter, 1, 0)));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.03999999910593033D;
		noClip = pushOutOfBlocks(posX, (getEntityBoundingBox().minY + getEntityBoundingBox().maxY) / 2.0D, posZ);
		moveEntity(motionX, motionY, motionZ);
		boolean flag = (int)prevPosX != (int)posX || (int)prevPosY != (int)posY || (int)prevPosZ != (int)posZ;

		if (flag || ticksExisted % 25 == 0)
		{
			if (worldObj.getBlockState(new BlockPos(this)).getMaterial() == Material.lava)
			{
				motionY = 0.20000000298023224D;
				motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
				motionZ = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
				playSound(SoundEvents.entity_generic_burn, 0.4F, 2.0F + rand.nextFloat() * 0.4F);
			}

		}

		float f = 0.98F;
		if (onGround)
		{
			f = worldObj.getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(getEntityBoundingBox().minY) - 1, MathHelper.floor_double(posZ))).getBlock().slipperiness * 0.98F;
		}

		motionX *= f;
		motionY *= 0.9800000190734863D;
		motionZ *= f;

		if (onGround)
		{
			motionY *= -0.5D;
		}

		handleWaterMovement();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		super.attackEntityFrom(source, amount);

		if (! worldObj.isRemote && ! isDead)
		{
			if (worldObj.getGameRules().getBoolean("doEntityDrops"))
			{
				IMatter iMatter = getMatter();

				if (iMatter != null)
				{
					ItemStack drop = new ItemStack(MovableMatterCraft.itemMatter, 1, 0);
					if (MovableMatterCraftAPI.hasMatter(drop, null))
					{
						MovableMatterCraftAPI.getMatter(drop, null).deserializeNBT(iMatter.serializeNBT());
					}

					entityDropItem(drop, 1.0f);
				}
			}

			setDead();
			return true;
		}

		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return ! isDead;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn)
	{
		return entityIn.getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox()
	{
		return getEntityBoundingBox().expand(-0.1d, 0, -0.1d);
	}


	/**
	 *
	 * @inheritDoc
	 */
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, ItemStack stack, EnumHand hand)
	{

		if (stack != null)
		{
			if (MovableMatterCraftAPI.hasMatter(stack, null))
			{
				IMatter stackMatter = MovableMatterCraftAPI.getMatter(stack, null);
				IMatter myMatter = getMatter();

				int stackAmount = stackMatter.getValue(DefaultMatterProperties.AMOUNT);
				int myAmount = myMatter.getValue(DefaultMatterProperties.AMOUNT);
				IMatterForm myForm = myMatter.getValue(DefaultMatterProperties.FORM);

				if (myMatter.getValue(DefaultMatterProperties.TYPE) == stackMatter.getValue(DefaultMatterProperties.TYPE)
						&& myForm == stackMatter.getValue(DefaultMatterProperties.FORM)
						&& (myForm == null || myAmount +stackAmount <= myForm.getMaxAmount()))
				{
					if (! worldObj.isRemote)
					{
						myMatter.withProperty(DefaultMatterProperties.AMOUNT, stackAmount +myAmount);
						stackMatter.withProperty(DefaultMatterProperties.AMOUNT, 0);
					}

					stack.stackSize = 0;
					player.setHeldItem(hand, null);

					return EnumActionResult.SUCCESS;
				} else
				{
					EntityMatter newEntity = new EntityMatter(worldObj, posX, posY +1.0f, posZ, stack);

					if (! worldObj.isRemote)
					{
						worldObj.spawnEntityInWorld(newEntity);
					}

					stack.stackSize = 0;
					player.setHeldItem(hand, null);
				}
			}
		}

		if (player.isSneaking())
		{
			IMatter myMatter = getMatter();
			if (2 <= myMatter.getValue(DefaultMatterProperties.AMOUNT))
			{
				ItemStack newStack = new ItemStack(MovableMatterCraft.itemMatter, 1);

				if (MovableMatterCraftAPI.hasMatter(newStack, null))
				{
					MovableMatterCraftAPI.getMatter(newStack, null)
						.withProperty(DefaultMatterProperties.AMOUNT, 1)
						.withProperty(DefaultMatterProperties.FORM, myMatter.getValue(DefaultMatterProperties.FORM))
						.withProperty(DefaultMatterProperties.TYPE, myMatter.getValue(DefaultMatterProperties.TYPE));

					EntityMatter newEntity = new EntityMatter(worldObj, posX, posY +0.7d, posZ, newStack);
					newEntity.motionX = 0.3d < rand.nextDouble() ? 0.1d : 0d;
					newEntity.motionY = 0.1d + Math.max(rand.nextDouble() -0.6d, 0d);
					newEntity.motionZ = 0.3d < rand.nextDouble() ? 0.1d : 0d;

					if (! worldObj.isRemote)
					{
						worldObj.spawnEntityInWorld(newEntity);

						myMatter.withProperty(DefaultMatterProperties.AMOUNT, myMatter.getValue(DefaultMatterProperties.AMOUNT) -1);
					}

					return EnumActionResult.SUCCESS;
				}
			}

			return EnumActionResult.FAIL;
		}

		return EnumActionResult.PASS;
	}

	public boolean hasMatterStack()
	{
		return dataWatcher.get(MATTER_STACK).isPresent();
	}

	public ItemStack getMatterStack()
	{
		return dataWatcher.get(MATTER_STACK).get();
	}

	public void setMatterStack(ItemStack stack)
	{
		dataWatcher.set(MATTER_STACK, Optional.of(stack));
	}

	public IMatter getMatter()
	{
		return hasMatterStack() && MovableMatterCraftAPI.hasMatter(getMatterStack(), null) ? MovableMatterCraftAPI.getMatter(getMatterStack(), null) : null;
	}


	/**
	* @inheritDoc
	*/
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		if (nbt.hasKey("matterStack", NBT.TAG_COMPOUND)) setMatterStack(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("matterStack")));
	}

	/**
	* @inheritDoc
	*/
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		if (hasMatterStack()) nbt.setTag("matterStack", getMatterStack().serializeNBT());
	}

}
