package com.example.flowerwand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FlowerWandMod implements ModInitializer {
	public static final String MOD_ID = "flowerwand";

	public static final Item FLOWER_WAND = Registry.register(
			Registries.ITEM,
			Identifier.of(MOD_ID, "flower_wand"),
			new FlowerWandItem(new Item.Settings().maxCount(1))
	);

	@Override
	public void onInitialize() {
		// Show the wand in the creative menu (Tools tab)
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(FLOWER_WAND));
	}
}
