package com.example.examplemod;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

public class MyItemModels extends ItemModelProvider {
    public MyItemModels(
            GatherDataEvent event
    ) {
        super(event.getGenerator().getPackOutput(), ExampleMod.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
        justParent(ExampleMod.EXAMPLE_BLOCK_ITEM, ExampleMod.EXAMPLE_BLOCK);
        basicItem(ExampleMod.EXAMPLE_ITEM.get());
    }

    private void justParent(
            RegistryObject<? extends Item> item, RegistryObject<? extends Block> block
    ) {
        justParent(item, block, "");
    }

    private void justParent(
            RegistryObject<? extends Item> item, RegistryObject<? extends Block> block, String extra
    ) {
        withExistingParent(block.getId().getPath(), ExampleMod.MODID + ":block/" + item.getId().getPath() + extra);
    }
}
