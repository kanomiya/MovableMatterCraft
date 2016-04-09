package com.kanomiya.mcmod.touchablecontainercraft.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import com.kanomiya.mcmod.touchablecontainercraft.api.matter.IMatter;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.machine.IMatterImporter;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;

/**
 * @author Kanomiya
 *
 */
public class TileEntityMatterCutter extends TileEntity implements IMatterImporter
{

	/**
	* @inheritDoc
	*/
	@Override
	public boolean importMatter(ItemStack matterStack, IMatter iMatter, EnumFacing facing)
	{
		// TODO: matter.setMatterForm();

		EntityMatter newEntity = new EntityMatter(worldObj, pos.getX(), pos.getY() +1.2d, pos.getZ(), matterStack);

		if (! worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld(newEntity);
		}

		matterStack.stackSize = 0;

		return true;
	}
}

