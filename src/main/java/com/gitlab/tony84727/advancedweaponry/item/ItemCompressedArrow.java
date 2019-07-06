package com.gitlab.tony84727.advancedweaponry.item;

import com.gitlab.tony84727.advancedweaponry.AdvancedWeaponry;
import com.gitlab.tony84727.advancedweaponry.ModInfo;
import net.minecraft.item.Item;

public class ItemCompressedArrow extends Item
{
	public ItemCompressedArrow()
	{
		setUnlocalizedName("compressed_arrow");
		setRegistryName(ModInfo.ModID + ":compressed_arrow");
		setCreativeTab(AdvancedWeaponry.creativeTab);
	}
}
