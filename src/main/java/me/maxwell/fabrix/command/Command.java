package me.maxwell.fabrix.command;

import java.util.Arrays;

/**
 * @author lazy
 * @since 5/26/2019 at 7:01 PM
 */
public abstract class Command {
    /**
     * The command's manifest that stores basic information.
     */
    private CommandManifest manifest;

    public Command() {
        if (!getClass().isAnnotationPresent(CommandManifest.class)) {
            System.err.printf("Class \"%s\" is registered as a command, but does not have a CommandManifest annotation.", getClass().getSimpleName());
            return;
        }

        this.manifest = getClass().getAnnotation(CommandManifest.class);
    }

    /**
     * Executes the command.
     *
     * @param arguments the command's arguments.
     * @param raw       the raw command message.
     */
    public abstract void execute(String[] arguments, String raw);

    /**
     * @return the command's label.
     */
    public String getLabel() {
        return manifest.label();
    }

    /**
     * Checks if the given input matches the command's name or aliases.
     *
     * @param input the given name.
     * @return if the given input matches the command's name or aliases.
     */
    public boolean matches(String input) {
        if (input.equalsIgnoreCase(getLabel()))
            return true;

        return Arrays.stream(manifest.aliases()).anyMatch(input::equalsIgnoreCase);
    }
}
