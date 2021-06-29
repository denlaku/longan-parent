package com.denlaku.longan.util;

import java.math.BigDecimal;

/**
 * @author tianx
 */
public class Types {

    public static Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            if (scale >= -100 && scale <= 100) {
                return decimal.longValue();
            }
            return decimal.longValueExact();
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            String strVal = (String) value;
            try {
                return Long.parseLong(strVal);
            } catch (NumberFormatException ex) {
                //
            }
        }
        return null;
    }

    public static long toLongValue(Object value) {
        Long aLong = toLong(value);
        return aLong != null ? aLong.longValue() : 0;
    }

    public static Integer toInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            if (scale >= -100 && scale <= 100) {
                return decimal.intValue();
            }
            return decimal.intValueExact();
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                String strVal = (String) value;
                return Integer.parseInt(strVal);
            } catch (NumberFormatException ex) {
                //
            }
        } else if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue() ? 1 : 0;
        }
        return null;
    }

    public static int toIntValue(Object value) {
        Integer integer = toInteger(value);
        return integer != null ? integer.intValue() : 0;
    }

}
