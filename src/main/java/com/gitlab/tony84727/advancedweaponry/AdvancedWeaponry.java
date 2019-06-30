package com.gitlab.tony84727.advancedweaponry;

import com.gitlab.tony84727.advancedweaponry.entity.EntityArrowGrenade;
import com.gitlab.tony84727.advancedweaponry.entity.EntityRock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber
@Mod(modid = AdvancedWeaponry.ModID, name = AdvancedWeaponry.Name, version = AdvancedWeaponry.Version)
public class AdvancedWeaponry
{
	public static final String ModID = "advancedweaponry";
	public static final String Name = "Advanced Weaponry";
	public static final String Version = "0.1.0";

	public static CreativeTabs creativeTab;

	@SidedProxy(clientSide = "com.gitlab.tony84727.advancedweaponry.client.ClientProxy", serverSide = "com.gitlab.tony84727.advancedweaponry.CommonProxy")
	private static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		proxy.preInit(e);
		MinecraftForge.EVENT_BUS.register(proxy);
	}

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityEntry> e)
	{
		e.getRegistry().registerAll(
				EntityEntryBuilder
						.create()
						.id("rock", 0)
						.name("rock")
						.tracker(32, 10, true)
						.entity(EntityRock.class)
						.build(),
				EntityEntryBuilder
						.create()
						.id("arrowGrenade", 1)
						.name("arrow grenade")
						.tracker(32, 10, true)
						.entity(EntityArrowGrenade.class)
						.build()
		);
	}
}
