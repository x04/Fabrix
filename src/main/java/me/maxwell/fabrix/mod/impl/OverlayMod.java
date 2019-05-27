package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.RenderEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModManifest;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Drawing;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

/**
 * @author lazy
 * @since 5/26/2019 at 1:35 AM
 */
@ModManifest(label = "Overlay", type = ModType.CORE)
public class OverlayMod extends Mod implements Listenable {
    private final int BASE_OFFSET = 14;
    private int y;

    @EventHandler
    private final Listener<RenderEvent> renderListener = new Listener<>(event -> {
        Drawing.INSTANCE.drawStringWithShadow("Fabrix", 4, 4, 0xAAAAAAFF);

        Fabrix.INSTANCE.getModManager().getMods().stream()
                .filter(Mod::isRunning)
                .filter(mod -> mod.getType() != ModType.CORE)
                .forEach(mod -> {
                    Drawing.INSTANCE.drawStringWithShadow(mod.getName(), 4, y, 0xFFFFFFFF);
                    y += 10;
                });

        /** Reset the offset so it can be calculated again. */
        y = BASE_OFFSET;
    });

    @Override
    public void onEnable() {
        Fabrix.INSTANCE.getEventBus().subscribe(this);
    }
}
