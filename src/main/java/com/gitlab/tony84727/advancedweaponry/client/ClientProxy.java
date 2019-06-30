package com.gitlab.tony84727.advancedweaponry.client;

import com.gitlab.tony84727.advancedweaponry.CommonProxy;
import com.gitlab.tony84727.advancedweaponry.entity.EntityArrowGrenade;
import com.gitlab.tony84727.advancedweaponry.entity.EntityRock;
import com.gitlab.tony84727.advancedweaponry.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		e.getModLog().info("test");
		RenderingRegistry.registerEntityRenderingHandler(EntityRock.class,
				(manager) -> new RenderSnowball<>(manager, Items.Rock, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowGrenade.class,
				(manager -> new RenderSnowball<>(manager, Items.ArrowGrenade, Minecraft.getMinecraft().getRenderItem())));
	}
}
