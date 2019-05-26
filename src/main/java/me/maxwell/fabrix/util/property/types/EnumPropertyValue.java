package me.maxwell.fabrix.util.property.types;

import me.maxwell.fabrix.util.property.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author lazy
 * @since 5/26/2019 at 3:35 PM
 */
public class EnumPropertyValue extends PropertyValue<Enum> {

    public EnumPropertyValue(String label, Field field, Object object, String description, String[] aliases) {
        super(label, field, object, description, aliases);
    }

    @Override
    public void fromString(String value) throws IllegalArgumentException{
        if (!field.getType().isEnum())
            return;

        Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), value.toUpperCase());

        setValue(enumValue);
    }

    public Enum[] values() {
        Class<? extends Enum> clazz = getValue().getClass();
        return clazz.getEnumConstants();
    }
}
