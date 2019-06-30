package com.gitlab.tony84727.advancedweaponry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityArrowGrenade extends Entity
{
	private final static double velocity = 1.2;
	private final static int arrowCount = 72;
	private final static double arrowYOffset = 0.5;

	protected EntityLivingBase thrower;
	private int fuse = 3 * 20;

	public EntityArrowGrenade(World world)
	{
		super(world);
	}

	public EntityArrowGrenade(World world, EntityLivingBase thrower)
	{
		super(world);
		this.thrower = thrower;
		final double pitch = thrower.rotationPitch / 180 * Math.PI;
		final double yaw = thrower.rotationYaw / 180 * Math.PI;
		final double x = -MathHelper.sin((float) yaw) * MathHelper.cos((float) pitch) * velocity;
		final double y = -MathHelper.sin((float) pitch) * velocity;
		final double z = MathHelper.cos((float) yaw) * MathHelper.cos((float) pitch) * velocity;
		setVelocity(x, y, z);
		setPosition(thrower.posX, thrower.posY + 1, thrower.posZ);
		setNoGravity(false);
		noClip = false;
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
		if (--fuse <= 0)
		{
			explode();
		}
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
				arrow.shoot(x, y, z, 2, 0);
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
