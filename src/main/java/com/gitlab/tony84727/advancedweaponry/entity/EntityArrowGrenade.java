package com.gitlab.tony84727.advancedweaponry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityArrowGrenade extends Entity
{
	private final static double velocity = 0.6;
	protected Entity thrower;
	private int fuse = 3 * 20;

	public EntityArrowGrenade(World world)
	{
		super(world);
	}

	public EntityArrowGrenade(World world, Entity thrower)
	{
		super(world);
		this.thrower = thrower;
		final double pitch = thrower.rotationPitch / 180 * Math.PI;
		final double yaw = thrower.rotationYaw / 180 * Math.PI;
		final double x = -MathHelper.sin((float) yaw) * MathHelper.cos((float) pitch) * velocity;
		final double y = -MathHelper.sin((float) pitch) * velocity;
		final double z = MathHelper.cos((float) yaw) * MathHelper.cos((float) pitch) * velocity;
		setVelocity(x, y, z);
		setPosition(thrower.posX, thrower.posY, thrower.posZ);
		setNoGravity(false);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		final double nextX = posX + motionX;
		final double nextY = posY + motionY;
		final double nextZ = posZ + motionZ;
		setPosition(nextX, nextY, nextZ);
		if (--fuse <= 0)
		{
			explode();
		}
	}

	private void explode()
	{
		setDead();
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 2, 1, true);
		if (!world.isRemote)
		{
			for (int i = 0; i < 72; i++)
			{
				double x = MathHelper.nextDouble(rand, -1, 1);
				double y = MathHelper.nextDouble(rand, -1, 1);
				double z = MathHelper.nextDouble(rand, -1, 1);
				EntityArrow arrow = new EntityTippedArrow(world, posX, posY, posZ);
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
