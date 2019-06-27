package com.gitlab.tony84727.advancedweaponry.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Items
{
	public static final Rock Rock = new Rock();

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> register)
	{
		register.getRegistry().registerAll(
				Rock
		);
	}
}
