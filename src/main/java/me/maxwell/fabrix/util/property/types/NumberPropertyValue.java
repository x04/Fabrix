package me.maxwell.fabrix.util.property.types;

import lombok.Getter;
import me.maxwell.fabrix.util.property.Clamp;
import me.maxwell.fabrix.util.property.Increment;
import me.maxwell.fabrix.util.property.PropertyValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lazy
 * @since 5/26/2019 at 3:35 PM
 */
public class NumberPropertyValue<T extends Number> extends PropertyValue<T> {
    private T increment = null;

    @Getter private T min = null;
    @Getter private T max = null;

    public NumberPropertyValue(String label, Field field, Object object, String description, String[] aliases) {
        super(label, field, object, description, aliases);

        if (field.isAnnotationPresent(Increment.class)) {
            Increment increment = field.getAnnotation(Increment.class);

            this.increment = (T) getAbsoluteNumberValue(increment.value());
        } else {
            this.increment = (T) getAbsoluteNumberValue("1");
        }

        if (field.isAnnotationPresent(Clamp.class)) {
            Clamp clamp = field.getAnnotation(Clamp.class);

            this.min = (T) getAbsoluteNumberValue(clamp.min());
            this.max = (T) getAbsoluteNumberValue(clamp.max());
        } else {
            this.min = (T) getAbsoluteNumberValue("-1000");
            this.max = (T) getAbsoluteNumberValue("1000");
        }
    }

    @Override
    public void setValue(T value) {
        if (value.doubleValue() > max.doubleValue())
            value = max;

        if (value.doubleValue() < min.doubleValue())
            value = min;

        Object val = value;

        switch (field.getType().getSimpleName().toLowerCase()) {
            case "int":
            case "integer":
                break;
            case "float":
                val = round(value.floatValue(), 2);
                break;
            case "double":
                val = round(value.doubleValue(), 2);
                break;
            case "long":
                break;
        }

        try {
            boolean accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(object, val);
            field.setAccessible(accessible);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void increase() {
        try {
            switch (field.getType().getSimpleName().toLowerCase()) {
                case "int":
                case "integer":
                    setValue((T) (Integer) (getValue().intValue() + increment.intValue()));
                    break;
                case "float":
                    setValue((T) (Float) (getValue().floatValue() + increment.floatValue()));
                    break;
                case "double":
                    setValue((T) (Double) (getValue().doubleValue() + increment.doubleValue()));
                    break;
                case "long":
                    setValue((T) (Long) (getValue().longValue() + increment.longValue()));
                    break;
            }
        } catch (Exception unused) { }
    }

    public void decrease() {
        try {
            switch (field.getType().getSimpleName().toLowerCase()) {
                case "int":
                case "integer":
                    setValue((T) (Integer) (getValue().intValue() - increment.intValue()));
                    break;
                case "float":
                    setValue((T) (Float) (getValue().floatValue() - increment.floatValue()));
                    break;
                case "double":
                    setValue((T) (Double) (getValue().doubleValue() - increment.doubleValue()));
                    break;
                case "long":
                    setValue((T) (Long) (getValue().longValue() - increment.longValue()));
                    break;
            }
        } catch (Exception unused) { }
    }

    @Override
    public void fromString(String value) throws IllegalArgumentException{
        if (getAbsoluteNumberValue(value) == null)
            throw new IllegalArgumentException();

        setValue((T) getAbsoluteNumberValue(value));
    }

    public Number getAbsoluteNumberValue(String text) {
        try {
            switch (field.getType().getSimpleName().toLowerCase()) {
                case "int":
                case "integer":
                    return Integer.parseInt(text);
                case "float":
                    return Float.parseFloat(text);
                case "double":
                    return Double.parseDouble(text);
                case "long":
                    return Long.parseLong(text);
                default:
                    return null;
            }
        } catch (Exception exception) {
            return null;
        }
    }

    private static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static float round(float value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    private static float round(long value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
