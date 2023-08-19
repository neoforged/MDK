package com.example.examplemod;


import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class MyBlockTags extends BlockTagsProvider {
    public MyBlockTags(GatherDataEvent event) {
        super(
                event.getGenerator().getPackOutput(),
                event.getLookupProvider(),
                ExampleMod.MODID,
                event.getExistingFileHelper()
        );
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ExampleMod.EXAMPLE_BLOCK.get());
    }
}
