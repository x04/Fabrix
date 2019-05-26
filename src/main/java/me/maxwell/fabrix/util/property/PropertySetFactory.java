package me.maxwell.fabrix.util.property;

import me.maxwell.fabrix.util.property.types.BooleanPropertyValue;
import me.maxwell.fabrix.util.property.types.EnumPropertyValue;
import me.maxwell.fabrix.util.property.types.NumberPropertyValue;
import me.maxwell.fabrix.util.property.types.StringPropertyValue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author lazy
 * @since 5/26/2019 at 3:34 PM
 */
public final class PropertySetFactory {

    /**
     * Adds all properties in a object.
     * <p>
     *
     * @param object object to be registered.
     * @return all properties found.
     */
    public static Set<PropertyValue> create(Object object) {
        Set<PropertyValue> values = new LinkedHashSet<>();

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Property.class) && getPropertyClass(field).isPresent() && !field.isAnnotationPresent(Child.class))
                .forEach(field -> values.add(createProperty(field, object).get()));

        return values;
    }

    /**
     * Creates
     *
     * @param field
     * @param parent
     * @return
     */
    private static Optional<PropertyValue> createProperty(Field field, Object parent) {
        Property property = field.getAnnotation(Property.class);

        // The class of the property.
        Class<? extends PropertyValue> propertyClass = getPropertyClass(field).get();

        try{
            PropertyValue propertyValue = (PropertyValue) propertyClass.getDeclaredConstructors()[0].newInstance(property.label(),
                    field, parent, property.description(), property.aliases());

            propertyValue.setChildren(findChildren(parent, propertyValue));

            return Optional.of(propertyValue);
        } catch (Exception unused) { }

        return Optional.empty();
    }

    /**
     * Finds all the children of the given property.
     *
     * @param object        The object to be searched.
     * @param propertyValue The parent.
     * @return all child properties found.
     */
    private static Set<PropertyValue> findChildren(Object object, PropertyValue propertyValue) {
        Set<PropertyValue> values = new LinkedHashSet<>();

        // Creates all of the children.
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Child.class)
                        && field.isAnnotationPresent(Child.class)
                        && getPropertyClass(field).isPresent()
                        && field.getAnnotation(Child.class).value().equalsIgnoreCase(propertyValue.getLabel()))
                .forEach(field -> {
                    PropertyValue child = createProperty(field, object).get();

                    child.setParent(propertyValue);
                    values.add(child);
                });

        return values;
    }

    /**
     * Gets the class the property should use.
     *
     * @param field The property's field.
     * @return the class the {@link PropertyValue} should use.
     */
    private static Optional<Class<? extends PropertyValue>> getPropertyClass(Field field) {
        switch (field.getType().getSimpleName().toLowerCase()) {
            case "boolean":
                return Optional.of(BooleanPropertyValue.class);
            case "string":
                return Optional.of(StringPropertyValue.class);
            case "integer":
            case "int":
            case "double":
            case "long":
            case "float":
                return Optional.of(NumberPropertyValue.class);
        }

        // Checks for enum type.
        if (field.getType().isEnum())
            return Optional.of(EnumPropertyValue.class);

        return Optional.empty();
    }
}
