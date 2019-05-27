package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.event.MoveEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModManifest;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Wrapper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

/**
 * @author lazy
 * @since 5/26/2019 at 2:21 AM
 */
@ModManifest(label = "AutoSprint", type = ModType.PLAYER)
public class AutoSprintMod extends Mod implements Listenable {

    @EventHandler
    private final Listener<MoveEvent> moveListener = new Listener<>(event -> {
        if (Wrapper.INSTANCE.getPlayer().forwardSpeed > 0.f)
            Wrapper.INSTANCE.getPlayer().setSprinting(true);
    });

    @Override
    public void onEnable() {
        Fabrix.INSTANCE.getEventBus().subscribe(this);
    }

    @Override
    public void onDisable() {
        Fabrix.INSTANCE.getEventBus().unsubscribe(this);
    }
}