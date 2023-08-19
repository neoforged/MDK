package com.example.examplemod;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class LocalizationKeys {
    @SuppressWarnings("unused") // used by minecraft without us having to directly reference
    public static LocalizationEntry EXAMPLE_BLOCK = new LocalizationEntry(
            () -> ExampleMod.EXAMPLE_BLOCK.get().getDescriptionId(),
            () -> "My example block"
    );

    @SuppressWarnings("unused") // used by minecraft without us having to directly reference
    public static LocalizationEntry EXAMPLE_ITEM = new LocalizationEntry(
            () -> ExampleMod.EXAMPLE_ITEM.get().getDescriptionId(),
            () -> "My example item"
    );

    public static LocalizationEntry CREATIVE_TAB = new LocalizationEntry(
            "item_group.examplemod.example_tab",
            "My example tab"
    );

    public static List<LocalizationEntry> getEntries() {
        // use reflection to get all the public static LocalizationEntry fields
        var rtn = new ArrayList<LocalizationEntry>();
        for (var field : LocalizationKeys.class.getFields()) {
            if (field.getType() == LocalizationEntry.class) {
                try {
                    rtn.add((LocalizationEntry) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return rtn;
    }

    public record LocalizationEntry(
            Supplier<String> key,
            Supplier<String> value
    ) {
        public LocalizationEntry(String key, String value) {
            this(() -> key, () -> value);
        }

        public TranslatableContents get(Object... args) {
            return new TranslatableContents(key.get(), null, args);
        }

        public TranslatableContents get() {
            return new TranslatableContents(key.get(), null, new Object[]{});
        }

        public String getString() {
            return I18n.get(key.get());
        }

        public String getString(Object... args) {
            return I18n.get(key.get(), args);
        }

        public MutableComponent getComponent() {
            return Component.translatable(key.get());
        }

        public MutableComponent getComponent(Object... args) {
            return Component.translatable(key.get(), args);
        }
    }
}
