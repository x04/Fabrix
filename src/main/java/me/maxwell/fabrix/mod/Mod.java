package me.maxwell.fabrix.mod;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lazy
 * @since 5/26/2019 at 1:34 AM
 */
@Getter
public abstract class Mod {
    public String name;
    public ModType type;

    @Setter
    public boolean running;

    public Mod(String name, ModType type) {
        this.name = name;
        this.type = type;
    }

    public void onEnable() { }

    public void onDisable() { }
}
