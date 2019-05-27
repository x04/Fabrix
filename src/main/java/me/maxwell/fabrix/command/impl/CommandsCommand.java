package me.maxwell.fabrix.command.impl;

import me.maxwell.fabrix.Fabrix;
import me.maxwell.fabrix.command.Command;
import me.maxwell.fabrix.command.CommandManifest;
import me.maxwell.fabrix.util.Wrapper;

import java.util.StringJoiner;

/**
 * @author lazy
 * @since 5/26/2019 at 11:51 PM
 */
@CommandManifest(label = "commands")
public class CommandsCommand extends Command {

    @Override
    public void execute(String[] arguments, String raw) {
        StringJoiner joiner = new StringJoiner(", ");

        Fabrix.INSTANCE.getCommandManager().getCommands().forEach(mod -> joiner.add(mod.getLabel()));
        Wrapper.INSTANCE.sendMessage("Commands: " + joiner);
    }
}