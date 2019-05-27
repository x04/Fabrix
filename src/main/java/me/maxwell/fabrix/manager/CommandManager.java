package me.maxwell.fabrix.manager;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import me.maxwell.fabrix.command.Command;
import me.maxwell.fabrix.command.impl.CommandsCommand;
import me.maxwell.fabrix.command.impl.ModsCommand;
import me.maxwell.fabrix.command.impl.ToggleCommand;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author lazy
 * @since 5/26/2019 at 6:59 PM
 */
public final class CommandManager {
    private final ClassToInstanceMap<Command> CMD_REGISTRY = new ImmutableClassToInstanceMap.Builder<Command>()
            /** Commands */
            .put(CommandsCommand.class, new CommandsCommand())
            .put(ModsCommand.class, new ModsCommand())
            .put(ToggleCommand.class, new ToggleCommand())

            .build();

    public <T extends Command> T getCommand(Class<T> clazz) {
        return CMD_REGISTRY.getInstance(clazz);
    }

    public Optional<Command> getCommand(String label) {
        return getCommands().stream().filter(command -> command.matches(label)).findFirst();
    }

    public Collection<Command> getCommands() {
        return Collections.unmodifiableCollection(CMD_REGISTRY.values());
    }
}
