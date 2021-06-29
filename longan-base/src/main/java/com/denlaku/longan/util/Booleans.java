package com.denlaku.longan.util;

import java.util.Collection;

/**
 * @author tianx
 */
public final class Booleans {
    public static boolean isTrue(Object value) {
        return Boolean.TRUE.equals(value);
    }

    public static boolean isNotTrue(Object value) {
        return !isTrue(value);
    }

    public static boolean isFalse(Object value) {
        return Boolean.FALSE.equals(value);
    }

    public static boolean isNotFalse(Object value) {
        return !isFalse(value);
    }

    public static boolean isAllTrue(Object ...values) {
        if (values != null && values.length > 0) {
            for (Object value : values) {
                if (value == null || isFalse(value)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAllTrue(Collection<?> values) {
        return isAllTrue(values != null ? values.toArray() : null);
    }

    public static boolean isAllFalse(Object ...values) {
        if (values != null && values.length > 0) {
            for (Object value : values) {
                if (value == null || isTrue(value)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isAllFalse(Collection<?> values) {
        return isAllFalse(values != null ? values.toArray() : null);
    }

    public static int toInt(Boolean value) {
        return isTrue(value) ? 1 : 0;
    }

    public static int toInt(boolean value) {
        return value ? 1 : 0;
    }

    public static Integer toInteger(Boolean value) {
        if (value == null) {
            return null;
        }
        return toInteger(value.booleanValue());
    }

    public static Integer toInteger(boolean value) {
        return value ? 1 : 0;
    }

}
