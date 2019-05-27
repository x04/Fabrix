package me.maxwell.fabrix.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lazy
 * @since 5/26/2019 at 7:01 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandManifest {

    /**
     * @return the label of the command.
     */
    String label();

    /**
     * @return the command's aliases.
     */
    String[] aliases() default { };
}
