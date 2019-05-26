package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.RenderEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Drawing;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

/**
 * @author lazy
 * @since 5/26/2019 at 1:35 AM
 */
public class OverlayMod extends Mod implements Listenable {
    private int y;

    public OverlayMod() {
        super("Overlay", ModType.CORE);
    }

    @EventHandler
    private final Listener<RenderEvent> renderListener = new Listener<>(event -> {
        Drawing.INSTANCE.drawStringWithShadow("Fabrix", 4, 4, 0xFFFFFFFF);
    });

    @Override
    public void onEnable() {
        Fabrix.INSTANCE.getEventBus().subscribe(this);
    }
}
