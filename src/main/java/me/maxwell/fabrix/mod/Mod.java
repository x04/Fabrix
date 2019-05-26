package me.maxwell.fabrix.mod;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lazy
 * @since 5/26/2019 at 1:34 AM
 */
@Getter
public abstract class Mod {
    /**
     * The mod's manifest containing it's information.
     */
    private ModManifest manifest;

    @Setter
    public boolean running;

    public Mod() {
        if (!getClass().isAnnotationPresent(ModManifest.class)) {
            System.err.printf("Class \"%s\" is registered as a mod, but does not have a ModManifest annotation.", getClass().getSimpleName());
            return;
        }

        this.manifest = getClass().getAnnotation(ModManifest.class);
    }

    public String getName() {
        return manifest.label();
    }

    public ModType getType() {
        return manifest.type();
    }

    public void onEnable() { }

    public void onDisable() { }
}
