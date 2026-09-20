package net.mrgoddavid.minecraftthestoriesmod.utils;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 9/19/2026
 */
public final class TooltipLineBreaker {

    private final Component component;
    private final int lineLength;

    public TooltipLineBreaker(int lineLength, final String translatableKey) {
        this.lineLength = lineLength;
        this.component = Component.translatable(translatableKey);
    }

    public Component[] linebreak() {
        String description = component.getString();
        List<Component> components = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (String word : description.split(" ")) {
            if (!line.isEmpty() && line.length() + 1 + word.length() > lineLength) {
                components.add(Component.literal(line.toString()));
                line.setLength(0);
            }
            if (!line.isEmpty()) {
                line.append(" ");
            }
            line.append(word);
        }
        if (!line.isEmpty()) {
            components.add(Component.literal(line.toString()));
        }
        return components.toArray(new Component[0]);
    }
}
