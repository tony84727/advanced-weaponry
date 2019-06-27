package com.gitlab.tony84727.advancedweaponry.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Rock extends Item
{
	public Rock()
	{
		setRegistryName("rock");
		setUnlocalizedName("rock");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
	{
		ItemStack itemStack = player.getHeldItem(handIn);
		if (!worldIn.isRemote)
		{
			final com.gitlab.tony84727.advancedweaponry.entity.Rock projectile = new com.gitlab.tony84727.advancedweaponry.entity.Rock(worldIn, player);
			projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2, 0);
			worldIn.spawnEntity(projectile);
		}
		worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!player.isCreative())
		{
			itemStack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
	}
}
