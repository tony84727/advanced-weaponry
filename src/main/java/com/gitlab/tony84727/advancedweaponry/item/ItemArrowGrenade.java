package com.gitlab.tony84727.advancedweaponry.item;

import com.gitlab.tony84727.advancedweaponry.AdvancedWeaponry;
import com.gitlab.tony84727.advancedweaponry.entity.EntityArrowGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemArrowGrenade extends Item
{
	public ItemArrowGrenade()
	{
		setUnlocalizedName("arrow_grenade");
		setRegistryName(AdvancedWeaponry.ModID + ":arrow_grenade");
		setCreativeTab(AdvancedWeaponry.creativeTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (!worldIn.isRemote)
		{
			EntityArrowGrenade entity = new EntityArrowGrenade(worldIn, playerIn);
			worldIn.spawnEntity(entity);
		}
		ItemStack current = playerIn.getHeldItem(handIn);
		if (!playerIn.capabilities.isCreativeMode)
		{
			current.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, current);
	}

	;
}
