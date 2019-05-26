package me.maxwell.fabrix.util.property;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author lazy
 * @since 5/26/2019 at 3:34 PM
 */
public class PropertyValue<T> {

    /**
     * The parent property value.
     */
    private PropertyValue parent;

    /**
     * Label of the property.
     */
    protected String label;

    /**
     * Field that contains the value.
     */
    public Field field;

    /**
     * The object that field belongs to.
     */
    protected Object object;

    /**
     * The description of the property.
     */
    protected String description;

    /**
     * The aliases of the property.
     */
    protected String[] aliases;

    /**
     * The id of the property used in loading.
     */
    protected int id = -1;

    /**
     * The child properties of the property.
     */
    private Set<PropertyValue> children = new HashSet<>();

    /**
     * Make a new property value.
     *
     * @param label       The label of the property.
     * @param field       The field that contains the value.
     * @param object      The object where the field is.
     * @param description The description of the property.
     */
    public PropertyValue(String label, Field field, Object object, String description, String[] aliases) {
        this.label = label;
        this.field = field;
        this.object = object;
        this.description = description;
        this.aliases = aliases;
    }

    /**
     * @return the property's parent.
     */
    public PropertyValue getParent() {
        return parent;
    }

    /**
     * Sets the property's parent.
     *
     * @param parent The parent property.
     */
    public void setParent(PropertyValue parent) {
        this.parent = parent;
    }

    /**
     * Registers the id to the property.
     *
     * @param id The id of the property.
     * @return The instance of the property.
     */
    public PropertyValue<T> withId(int id) {
        this.id = id;
        return this;
    }


    /**
     * Returns all child properties in the property.
     *
     * @return all child properties in the property.
     */
    public Set<PropertyValue> getChildren() {
        return children;
    }

    /**
     * Sets all the child properties.
     *
     * @param children The child properties.
     */
    public void setChildren(Set<PropertyValue> children) {
        this.children = children;
    }

    /**
     * Returns the value of the field.
     *
     * @return value of property.
     */
    public T getValue() {
        try {
            boolean accessible = field.isAccessible();

            field.setAccessible(true);
            T value = (T) field.get(object);
            field.setAccessible(accessible);
            return value;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Sets the field value.
     *
     * @param value new field value.
     */
    public void setValue(T value) {
        try {
            boolean accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(accessible);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns if the given label is correct.
     *
     * @param label The label given.
     * @return If the given label is correct.
     */
    public boolean isLabel(String label) {
        if (label.equalsIgnoreCase(this.label))
            return true;

        return Arrays.stream(aliases).anyMatch(alias -> alias.equalsIgnoreCase(label));
    }

    public void fromString(String value) throws IllegalArgumentException { }

    /**
     * Returns the label of the property.
     *
     * @return label of the property.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the field that the property manipulates.
     *
     * @return field of the property.
     */
    public Field getField() {
        return field;
    }

    /**
     * Returns the object the property belongs to.
     *
     * @return object the property belongs to.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Returns the description of the property.
     *
     * @return description of the property.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The id of the property.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the full label.
     */
    public String getDisplayLabel() {
        // All the property names.
        List<String> names = new ArrayList<>();

        // The parent property.
        PropertyValue property = this;

        // Adds the name while the parent property isn't null.
        while (property != null) {
            // Adds the parent label.
            names.add(property.getLabel());

            // Sets the parent to the new parent value.
            property = property.parent;
        }

        // The full name.
        String full = "";

        // Creates full name.
        for (String name : names)
            full = String.format("-%s%s", name, full);

        return full.substring(1);
    }

    public boolean matches(String input) {
        return input.equalsIgnoreCase(this.getDisplayLabel()) || input.equalsIgnoreCase(getDisplayLabel().replaceAll("_", ""));
    }
}