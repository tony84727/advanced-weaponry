package com.gitlab.tony84727.advancedweaponry.client;

import com.gitlab.tony84727.advancedweaponry.CommonProxy;
import com.gitlab.tony84727.advancedweaponry.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	public static void preInit(FMLPreInitializationEvent e)
	{
		RenderingRegistry.registerEntityRenderingHandler(com.gitlab.tony84727.advancedweaponry.entity.Rock.class,
				(manager) -> new RenderSnowball(manager, Items.Rock, Minecraft.getMinecraft().getRenderItem()));
	}
}
