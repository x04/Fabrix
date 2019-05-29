package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.RenderEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModManifest;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Drawing;
import me.maxwell.fabrix.util.Wrapper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

/**
 * @author cc
 * @since 5/29/2019 at 12:21 AM
 */
@ModManifest(label = "ESP", type = ModType.DRAW)
public class EspMod extends Mod implements Listenable {
    @EventHandler
    private final Listener<RenderEvent> renderListener = new Listener<>(event -> {
        Wrapper.INSTANCE.getMinecraft().world.getEntities().forEach(entity -> entity.setGlowing(true));
    });

    @Override
    public void onEnable() { Fabrix.INSTANCE.getEventBus().subscribe(this); }

    @Override
    public void onDisable() {
        Wrapper.INSTANCE.getMinecraft().world.getEntities().forEach(entity -> entity.setGlowing(false));
        Fabrix.INSTANCE.getEventBus().unsubscribe(this);
    }
}
