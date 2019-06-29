package com.gitlab.tony84727.advancedweaponry.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class Items
{
	public static final ItemRock Rock = new ItemRock();
	public static final ItemArrowGrenade ArrowGrenade = new ItemArrowGrenade();

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> e)
	{
		registerItem(e.getRegistry(), Rock);
		registerItem(e.getRegistry(), ArrowGrenade);
	}

	private static void registerItem(IForgeRegistry<Item> registry,Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
		registry.register(item);
	}
}
