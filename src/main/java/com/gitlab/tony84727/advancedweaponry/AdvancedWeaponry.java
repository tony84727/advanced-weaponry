package com.gitlab.tony84727.advancedweaponry;

import com.gitlab.tony84727.advancedweaponry.item.Rock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
@Mod(modid=AdvancedWeaponry.ModID, name = AdvancedWeaponry.Name, version = AdvancedWeaponry.Version)
public class AdvancedWeaponry
{
	public static final String ModID = "advancedweaponry";
	public static final String Name = "Advanced Weaponry";
	public static final String Version  = "0.0.0";

	private static final Rock rock = new Rock();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {

	}
}
