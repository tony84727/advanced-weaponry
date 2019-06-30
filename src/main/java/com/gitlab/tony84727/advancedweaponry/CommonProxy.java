package com.gitlab.tony84727.advancedweaponry;

import com.gitlab.tony84727.advancedweaponry.item.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
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
}
