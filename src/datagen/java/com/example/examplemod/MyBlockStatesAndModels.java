package com.example.examplemod;


import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class MyBlockStatesAndModels extends BlockStateProvider {
    public MyBlockStatesAndModels(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), ExampleMod.MODID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ExampleMod.EXAMPLE_BLOCK.get());
    }
}
