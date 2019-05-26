package me.maxwell.fabrix.util.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lazy
 * @since 5/26/2019 at 3:35 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Child {

    /**
     * @return the name of the property.
     */
    String value();
}

