package com.gitlab.tony84727.advancedweaponry.item;

import com.gitlab.tony84727.advancedweaponry.AdvancedWeaponry;
import net.minecraft.item.Item;

public class ItemCompressedArrow extends Item
{
	public ItemCompressedArrow()
	{
		setUnlocalizedName("compressed arrow");
		setRegistryName(AdvancedWeaponry.ModID + ":compressed_arrow");
		setCreativeTab(AdvancedWeaponry.creativeTab);
	}
}
