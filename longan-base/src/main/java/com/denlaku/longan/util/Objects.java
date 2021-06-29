package com.denlaku.longan.util;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author tianx
 */
public final class Objects {

    public static boolean isAllNull(Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAllNull(Collection<?> values) {
        return isAllNull(values != null ? values.toArray() : null);
    }

    public static boolean isAnyNull(Object... values) {
        return !isAllNull(values);
    }

    public static boolean isAnyNull(Collection<?> values) {
        return !isAllNull(values);
    }

    public static boolean isNoneNull(Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNoneNull(Collection<?> values) {
        if (values != null) {
            for (Object value : values) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean equals(Object a, Object b) {
        return java.util.Objects.equals(a, b);
    }

    public static boolean deepEquals(Object a, Object b) {
        return java.util.Objects.deepEquals(a, b);
    }

    public static boolean equalsAny(Object a, Object... values) {
        if (values == null || values.length == 0) {
            return false;
        }
        for (Object value : values) {
            if (equals(a, value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static <T, R> R map(T t, Function<T, R> mapper) {
        return t == null ? null : mapper.apply(t);
    }

    public static <T> void ifPresent(T t, Consumer<T> consumer) {
        if (t != null) {
            consumer.accept(t);
        }
    }

    public static String toString(Object o) {
        return String.valueOf(o);
    }

    public static String toString(Object o, String nullDefault) {
        return o != null ? o.toString() : nullDefault;
    }

}
