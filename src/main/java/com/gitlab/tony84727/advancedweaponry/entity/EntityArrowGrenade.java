package com.gitlab.tony84727.advancedweaponry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityArrowGrenade extends Entity
{
	private final static double velocity = 1.2;
	private final static int arrowCount = 72;
	private final static double arrowYOffset = 0.5;
	private final static float arrowSpeed = 0.5f;

	private final static DataParameter<Integer> FUSE = EntityDataManager.createKey(EntityArrowGrenade.class, DataSerializers.VARINT);
	protected EntityLivingBase thrower;

	public EntityArrowGrenade(World world)
	{
		this(world, null, 3 * 20);
	}

	public EntityArrowGrenade(World world, @Nullable EntityLivingBase thrower, int remainingFuse)
	{
		super(world);
		if (thrower != null)
		{
			this.thrower = thrower;
			final double pitch = thrower.rotationPitch / 180 * Math.PI;
			final double yaw = thrower.rotationYaw / 180 * Math.PI;
			motionX = -MathHelper.sin((float) yaw) * MathHelper.cos((float) pitch) * velocity;
			motionY = -MathHelper.sin((float) pitch) * velocity;
			motionZ = MathHelper.cos((float) yaw) * MathHelper.cos((float) pitch) * velocity;
			setPosition(thrower.posX, thrower.posY + 1, thrower.posZ);
		}
		setNoGravity(false);
		noClip = false;
		dataManager.register(FUSE, remainingFuse);
	}

	public EntityArrowGrenade(World world, EntityLivingBase thrower)
	{
		this(world, thrower, 3 * 20);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		setSize(0.3f, 0.5f);
		motionY -= 0.03d;
		if (onGround)
		{
			motionX *= 0.02;
			motionY *= 0.02;
			motionZ *= 0.02;
		}
		move(MoverType.SELF, motionX, motionY, motionZ);
		world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, -motionX, -motionY, -motionZ);
		if (dataManager.get(FUSE) <= 0)
		{
			explode();
		}
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		final int fuse = dataManager.get(FUSE);
		dataManager.set(FUSE, fuse - 1);
	}

	private void explode()
	{
		setDead();
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 10, 1, true);
		if (!world.isRemote)
		{
			for (int i = 0; i < arrowCount; i++)
			{
				double x = MathHelper.nextDouble(rand, -1, 1);
				double y = MathHelper.nextDouble(rand, -1, 1);
				double z = MathHelper.nextDouble(rand, -1, 1);
				EntityTippedArrow arrow = new EntityTippedArrow(world, thrower);
				arrow.setPosition(posX, posY + arrowYOffset, posZ);
				arrow.shoot(x, y, z, arrowSpeed, 0);
				world.spawnEntity(arrow);
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
	}

	@Override
	protected void entityInit()
	{
	}
}
