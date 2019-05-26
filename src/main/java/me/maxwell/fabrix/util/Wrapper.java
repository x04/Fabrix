package me.maxwell.fabrix.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.FontManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;

/**
 * @author lazy
 * @since 5/26/2019 at 2:11 AM
 */
public enum Wrapper {
    INSTANCE;

    Wrapper() { }

    public MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }

    public ClientPlayerEntity getPlayer() {
        return getMinecraft().player;
    }

    public ClientWorld getWorld() {
        return getMinecraft().world;
    }

    public FontManager getFontManager() { return getMinecraft().getFontManager(); }

    public TextRenderer getTextRenderer(Identifier identifier) { return getFontManager().getTextRenderer(identifier); }

    public TextRenderer getDefaultTextRenderer() { return getTextRenderer(MinecraftClient.DEFAULT_TEXT_RENDERER_ID); }
}
