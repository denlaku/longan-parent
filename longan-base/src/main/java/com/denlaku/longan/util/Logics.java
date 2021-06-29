package com.denlaku.longan.util;

/**
 * @author tianx
 */
public final class Logics {
    public static <R, K extends R, V extends R> R ifTrue(Boolean bool, K k, V v) {
        return Booleans.isTrue(bool) ? k: v;
    }

    public static <R, K extends R, V extends R> R ifNull(K k, V v) {
        return k == null ? v : k;
    }
}
