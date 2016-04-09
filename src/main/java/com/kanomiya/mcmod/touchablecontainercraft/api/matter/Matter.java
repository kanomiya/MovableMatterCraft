package com.kanomiya.mcmod.touchablecontainercraft.api.matter;

import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import com.kanomiya.mcmod.touchablecontainercraft.api.TouchableMatterCraftAPI;
import com.kanomiya.mcmod.touchablecontainercraft.api.client.render.IMatterModel;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.event.MatterModelBakeEvent;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.form.IMatterForm;
import com.kanomiya.mcmod.touchablecontainercraft.api.matter.type.IMatterType;
import com.kanomiya.mcmod.touchablecontainercraft.entity.EntityMatter;
import com.kanomiya.mcmod.touchablecontainercraft.registry.MatterRegistry;

/**
 * @author Kanomiya
 *
 */
public class Matter implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{
	protected IMatterType matterType;
	protected IMatterForm matterForm;

	protected IMatterModel<EntityMatter, IMatterModel> matterModel;

	protected int amount;

	public Matter(IMatterType matterType, IMatterForm matterForm)
	{
		this.matterType = matterType;
		this.matterForm = matterForm;
		amount = 1;
	}

	public IMatterType getMatterType()
	{
		return matterType;
	}


	public void setMatterTypeAndForm(IMatterType matterType, IMatterForm matterForm)
	{
		this.matterType = matterType;
		this.matterForm = matterForm;
		bakeModel();
	}

	public void setMatterType(IMatterType matterType)
	{
		setMatterTypeAndForm(matterType, matterForm);
	}


	public IMatterForm getMatterForm()
	{
		return matterForm;
	}

	public void setMatterForm(IMatterForm matterForm)
	{
		setMatterTypeAndForm(matterType, matterForm);
	}


	public void bakeModel()
	{
		MatterModelBakeEvent event = new MatterModelBakeEvent(this);
		MinecraftForge.EVENT_BUS.post(event);

		setMatterModel(event.getMatterModel());
	}

	public boolean hasMatterModel()
	{
		return matterModel != null;
	}

	public IMatterModel<EntityMatter, IMatterModel> getMatterModel()
	{
		return matterModel;
	}

	public void setMatterModel(IMatterModel<EntityMatter, IMatterModel> matterModel)
	{
		this.matterModel = matterModel;
	}



	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = Math.min(amount, matterForm.getMaxAmount());
	}

	public String getDisplayName()
	{
		String type = matterType == null ? "null" : matterType.getDisplayName();
		String form = matterForm == null ? "null" : matterForm.getDisplayName();

		return type + " <" + form + "> x" + amount;
	}

	public void addInformation(List<String> tooltip)
	{

	}

	/**
	* @inheritDoc
	*/
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == TouchableMatterCraftAPI.capMatter;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (hasCapability(capability, facing)) ? (T) this : null;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();

		ResourceLocation typeId = MatterRegistry.INSTANCE.typeRegistry.inverse().get(matterType);
		ResourceLocation formId = MatterRegistry.INSTANCE.formRegistry.inverse().get(matterForm);

		nbt.setString("typeId", typeId == null ? "null" : typeId.toString());
		nbt.setString("formId", formId == null ? "null" : formId.toString());
		nbt.setInteger("amount", amount);

		return nbt;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		matterType = MatterRegistry.INSTANCE.typeRegistry.get(new ResourceLocation(nbt.getString("typeId")));
		matterForm = MatterRegistry.INSTANCE.formRegistry.get(new ResourceLocation(nbt.getString("formId")));
		amount = nbt.getInteger("amount");

		bakeModel();
	}

	public static class Storage implements Capability.IStorage<Matter>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public NBTBase writeNBT(Capability<Matter> capability, Matter instance, EnumFacing side)
		{
			if (capability == TouchableMatterCraftAPI.capMatter)
			{
				return instance.serializeNBT();
			}

			return null;
		}

		/**
		* @inheritDoc
		*/
		@Override
		public void readNBT(Capability<Matter> capability, Matter instance, EnumFacing side, NBTBase nbt)
		{
			if (capability == TouchableMatterCraftAPI.capMatter && nbt instanceof NBTTagCompound)
			{
				instance.deserializeNBT((NBTTagCompound) nbt);
			}

		}

	}



}
