package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.RenderEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModType;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;

/**
 * @author lazy
 * @since 5/26/2019 at 1:35 AM
 */
public class OverlayMod extends Mod implements Listenable {

    public OverlayMod() {
        super("Overlay", ModType.CORE);
    }

    @EventHandler
    private Listener<RenderEvent> renderListener = new Listener<>(event -> {
        MinecraftClient.getInstance().getFontManager().getTextRenderer(MinecraftClient.DEFAULT_TEXT_RENDERER_ID).drawWithShadow("Fabrix", 4, 4, 0xFFFFFFFF);
    });

    @Override
    public void onEnable() {
        Fabrix.INSTANCE.getEventBus().subscribe(this);
    }
}
