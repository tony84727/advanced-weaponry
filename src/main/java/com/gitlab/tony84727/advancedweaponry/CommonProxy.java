package com.gitlab.tony84727.advancedweaponry;

import com.gitlab.tony84727.advancedweaponry.item.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		MinecraftForge.EVENT_BUS.register(this);
		AdvancedWeaponry.creativeTab = new CreativeTabs(CreativeTabs.getNextID(), "advancedweaponry")
		{
			@Override
			public ItemStack getTabIconItem()
			{
				return new ItemStack(Items.Rock, 1);
			}
		};

		Items.Rock.setCreativeTab(AdvancedWeaponry.creativeTab);
	}

	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> e)
	{
		e.getRegistry().registerAll(
				Items.Rock,
				Items.ArrowGrenade,
				Items.CompressedArrow
		);
	}
}
