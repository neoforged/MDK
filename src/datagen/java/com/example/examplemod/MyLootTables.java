package com.example.examplemod;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.function.BiConsumer;

public class MyLootTables extends LootTableProvider {

    public MyLootTables(GatherDataEvent event) {
        super(
                event.getGenerator().getPackOutput(),
                // specify registry names of the tables that are required to generate, or can leave empty
                Collections.emptySet(),
                // Sub providers which generate the loot
                ImmutableList.of(new SubProviderEntry(MyBlockLootProvider::new, LootContextParamSets.BLOCK))
        );
    }

    public static class MyBlockLootProvider implements LootTableSubProvider {

        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {
            dropSelf(ExampleMod.EXAMPLE_BLOCK, writer);
        }

        private void dropSelf(RegistryObject<Block> block, BiConsumer<ResourceLocation, LootTable.Builder> writer) {
            var pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block.get()));
            writer.accept(block.get().getLootTable(), LootTable.lootTable().withPool(pool));
        }
    }
}
