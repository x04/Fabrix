package me.maxwell.fabrix.util.property.types;

import me.maxwell.fabrix.util.property.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author lazy
 * @since 5/26/2019 at 3:35 PM
 */
public class BooleanPropertyValue extends PropertyValue<Boolean> {

    public BooleanPropertyValue(String label, Field field, Object object, String description, String[] aliases) {
        super(label, field, object, description, aliases);
    }

    @Override
    public void fromString(String value) throws IllegalArgumentException{
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))
            setValue(value.equalsIgnoreCase("true"));
        else
            throw new IllegalArgumentException();
    }
}