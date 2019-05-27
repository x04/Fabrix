package me.maxwell.fabrix.mod.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.command.Command;
import me.maxwell.fabrix.event.SendChatEvent;
import me.maxwell.fabrix.mod.Mod;
import me.maxwell.fabrix.mod.ModManifest;
import me.maxwell.fabrix.mod.ModType;
import me.maxwell.fabrix.util.Wrapper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author lazy
 * @since 5/26/2019 at 7:22 PM
 */
@ModManifest(label = "Commands", type = ModType.CORE)
public class CommandsMod extends Mod implements Listenable {
    private final String COMMAND_PREFIX = "`";

    @EventHandler
    private final Listener<SendChatEvent> sendChatListener = new Listener<>(event -> {
        if (!event.getMessage().startsWith(COMMAND_PREFIX))
            return;

        // Removes the prefix from the command.
        String message = event.getMessage();
        message = message.substring(1);

        // Splits the message up by white space.
        String[] arguments = message.split("\\s+");

        if (arguments.length == 0) {
            Wrapper.INSTANCE.sendMessage("Not enough arguments.");
            return;
        }

        // Find our command.
        Optional<Command> command = Fabrix.INSTANCE.getCommandManager().getCommand(arguments[0]);

        // Returns if command is not present.
        if (!command.isPresent()) {
            Wrapper.INSTANCE.sendMessage("Command \"" + arguments[0] + "\" not found.");
            return;
        }

        try {
            // Executes the command.
            command.get().execute(Arrays.copyOfRange(arguments, 1, arguments.length), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    @Override
    public void onEnable() {
        Fabrix.INSTANCE.getEventBus().subscribe(this);
    }
}