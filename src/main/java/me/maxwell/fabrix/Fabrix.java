package me.maxwell.fabrix;

import lombok.Getter;
import me.maxwell.fabrix.manager.ModManager;
import me.maxwell.fabrix.mod.ModType;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

/**
 * @author lazy
 * @since 5/26/2019 at 12:55 AM
 */
@Getter
public enum Fabrix {
    INSTANCE;

    Fabrix() { }

    public final EventBus eventBus = new EventManager();
    public final ModManager modManager = new ModManager();

    public void start() {
        /** Startup all core mods. */
        Fabrix.INSTANCE.getModManager().getMods().stream()
                .filter(mod -> mod.getType() == ModType.CORE)
                .forEach(mod -> mod.onEnable());
    }
}
