package me.maxwell.fabrix.mod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lazy
 * @since 5/26/2019 at 5:42 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModManifest {

    /**
     * @return the name of the mod.
     */
    String label();

    /**
     * @return the category of which the mod belongs to.
     */
    ModType type();
}
