package com.gitlab.tony84727.advancedweaponry.client;

import com.gitlab.tony84727.advancedweaponry.CommonProxy;
import com.gitlab.tony84727.advancedweaponry.entity.EntityArrowGrenade;
import com.gitlab.tony84727.advancedweaponry.entity.EntityRock;
import com.gitlab.tony84727.advancedweaponry.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);

		RenderingRegistry.registerEntityRenderingHandler(EntityRock.class,
				(manager) -> new RenderSnowball<>(manager, Items.Rock, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowGrenade.class,
				(manager -> new RenderSnowball<>(manager, Items.ArrowGrenade, Minecraft.getMinecraft().getRenderItem())));
	}

	@SubscribeEvent
	public void onRegisterModel(ModelRegistryEvent e)
	{
		registerItemModel(Items.Rock);
		registerItemModel(Items.ArrowGrenade);
		registerItemModel(Items.CompressedArrow);
	}

	private static void registerItemModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
