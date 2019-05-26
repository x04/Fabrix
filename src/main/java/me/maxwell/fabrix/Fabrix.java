package me.maxwell.fabrix;

import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

/**
 * @author lazy
 * @since 5/26/2019 at 12:55 AM
 */
public enum Fabrix {
    INSTANCE;

    Fabrix() { }

    public final EventBus EVENT_BUS = new EventManager();

    public void start() { }
}
