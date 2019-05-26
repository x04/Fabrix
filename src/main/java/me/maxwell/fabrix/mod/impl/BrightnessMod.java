package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Wrapper;

/**
 * @author lazy
 * @since 5/26/2019 at 2:21 AM
 */
public class BrightnessMod extends Mod {
    private double oldGamma;

    public BrightnessMod() {
        super("Brightness", ModType.DRAW);
    }

    @Override
    public void onEnable() {
        oldGamma = Wrapper.INSTANCE.getMinecraft().options.gamma;
        Wrapper.INSTANCE.getMinecraft().options.gamma = 1337;
    }

    @Override
    public void onDisable() {
        Wrapper.INSTANCE.getMinecraft().options.gamma = oldGamma;
    }
}