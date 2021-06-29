package com.denlaku.longan.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author tianx
 */
public final class Numbers {

    public static Integer multiply(Integer a, Integer b) {
        if (a == null || b == null) {
            return null;
        }
        return a.intValue() * b.intValue();
    }

    public static Long multiply(Long a, Long b) {
        if (a == null || b == null) {
            return null;
        }
        return a.longValue() * b.longValue();
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return null;
        }
        return a.multiply(b);
    }

    public static BigInteger multiply(BigInteger a, BigInteger b) {
        if (a == null || b == null) {
            return null;
        }
        return a.multiply(b);
    }

    public static BigDecimal toBigDecimal(String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        return new BigDecimal(value.trim());
    }

    public static Long toLong(String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        return Long.parseLong(value.trim());
    }

    public static Integer toInteger(String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        return Integer.parseInt(value.trim());
    }
}
