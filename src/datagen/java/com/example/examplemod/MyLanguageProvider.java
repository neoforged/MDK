package com.example.examplemod;


import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyLanguageProvider extends LanguageProvider {
    public MyLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), ExampleMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<String> seen = new HashSet<>();
        for (var entry : LocalizationKeys.getEntries()) {
            add(entry.key().get(), entry.value().get());
            seen.add(entry.key().get());
        }
        // Let's check to ensure we haven't forgotten any entries
        // Won't cover everything, but is better than nothing
        List<String> unmapped = new ArrayList<>();
        ForgeRegistries.ITEMS
                .getEntries()
                .stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(ExampleMod.MODID))
                .filter(entry -> !seen.contains(entry.getValue().getDescriptionId()))
                .map(entry -> entry.getValue().toString())
                .forEach(unmapped::add);
        ForgeRegistries.BLOCKS
                .getEntries()
                .stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(ExampleMod.MODID))
                .filter(entry -> !seen.contains(entry.getValue().getDescriptionId()))
                .map(entry -> entry.getValue().toString())
                .forEach(unmapped::add);
        if (!unmapped.isEmpty()) {
            throw new IllegalStateException("Unmapped entries: " + String.join(", ", unmapped));
        }
    }
}
