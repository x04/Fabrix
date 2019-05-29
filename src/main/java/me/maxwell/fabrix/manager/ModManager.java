package me.maxwell.fabrix.manager;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.impl.*;

import java.util.Collection;
import java.util.Collections;

/**
 * @author lazy
 * @since 5/26/2019 at 1:38 AM
 */
public final class ModManager {
    private final ClassToInstanceMap<Mod> MOD_REGISTRY = new ImmutableClassToInstanceMap.Builder<Mod>()
            /** Player */
            .put(AutoSprintMod.class, new AutoSprintMod())

            /** Draw */
            .put(BrightnessMod.class, new BrightnessMod())
            .put(EspMod.class, new EspMod())

            /** Core */
            .put(CommandsMod.class, new CommandsMod())
            .put(OverlayMod.class, new OverlayMod())

            .build();

    public <T extends Mod> T getMod(Class<T> clazz) {
        return MOD_REGISTRY.getInstance(clazz);
    }

    public Mod getMod(String label) {
        return getMods().stream().filter(mod -> mod.getName().equalsIgnoreCase(label)).findFirst().orElse(null);
    }

    public Collection<Mod> getMods() {
        return Collections.unmodifiableCollection(MOD_REGISTRY.values());
    }
}