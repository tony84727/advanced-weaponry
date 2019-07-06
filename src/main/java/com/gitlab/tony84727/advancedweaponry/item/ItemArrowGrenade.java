package com.gitlab.tony84727.advancedweaponry.item;

import com.gitlab.tony84727.advancedweaponry.AdvancedWeaponry;
import com.gitlab.tony84727.advancedweaponry.entity.EntityArrowGrenade;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
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
		ItemStack current = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);

		return new ActionResult<>(EnumActionResult.SUCCESS, current);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
	{
		super.onItemUseFinish(stack, world, entityLiving);
		if (entityLiving instanceof EntityPlayer)
		{
			throwGrenade(world, stack, (EntityPlayer) entityLiving, 0);
		}
		return stack;
	}

	private ItemStack throwGrenade(World world, ItemStack stack, EntityPlayer player, int remainingFuse)
	{
		if (!world.isRemote)
		{
			EntityArrowGrenade entity = new EntityArrowGrenade(world, player, remainingFuse);
			world.spawnEntity(entity);
		}
		if (!player.capabilities.isCreativeMode)
		{
			stack.shrink(1);
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BOW;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
		{
			throwGrenade(world, stack, (EntityPlayer) entityLiving, timeLeft);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 60;
	}
}
