package me.maxwell.fabrix.util.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lazy
 * @since 5/26/2019 at 3:34 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {

    /**
     * Label of the property.
     *
     * @return the name of the property.
     */
    String label();

    /**
     * Aliases of the property.
     *
     * @return the aliases of the property.
     */
    String[] aliases() default { };

    /**
     * Description of the property.
     *
     * @return the description of the property.
     */
    String description() default "No description found for the property.";
}
