package me.maxwell.fabrix.command.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.command.Command;
import me.maxwell.fabrix.command.CommandManifest;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.util.Wrapper;

/**
 * @author lazy
 * @since 5/26/2019 at 7:03 PM
 */
@CommandManifest(label = "toggle", aliases = { "t" })
public class ToggleCommand extends Command {

    @Override
    public void execute(String[] arguments, String raw) {
        if (arguments.length != 1) {
            Wrapper.INSTANCE.sendMessage("Not enough arguments.");
            return;
        }

        Mod mod = Fabrix.INSTANCE.getModManager().getMod(arguments[0]);

        if (mod == null) {
            Wrapper.INSTANCE.sendMessage("Mod \"" + arguments[0] + "\" not found.");
            return;
        }

        mod.toggle();

        if (mod.isRunning())
            Wrapper.INSTANCE.sendMessage(mod.getName() + " enabled.");
        else
            Wrapper.INSTANCE.sendMessage(mod.getName() + " disabled.");
    }
}
