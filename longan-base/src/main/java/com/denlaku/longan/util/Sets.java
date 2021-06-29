package com.denlaku.longan.util;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author tianx
 */
public final class Sets {

    public static <T> Set<T> empty() {
        return ofSize(0);
    }

    public static <T> Set<T> ofSize(int size) {
        return new HashSet<>(size < 0 ? 0 : size);
    }

    public static <T> Set<T> of(T ...values) {
        if (values == null) {
            return empty();
        }
        Set<T> set = ofSize(values.length);
        for (T value : values) {
            set.add(value);
        }
        return set;
    }

    public static <T> Set<T> of(Collection<T> values) {
        if (values == null) {
            return empty();
        }
        Set<T> set = ofSize(values.size());
        for (T value : values) {
            set.add(value);
        }
        return set;
    }

    public static <T> boolean isEmpty(Set<T> set) {
        if (set == null || set.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(Set<T> set) {
        return !isEmpty(set);
    }

    public static <T> boolean isNoneNull(Set<T> set) {
        if (set == null) {
            return true;
        }
        for (T t : set) {
            if (t == null) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean contains(Set<T> set, T value) {
        return set != null ? set.contains(value) : false;
    }

    public static <T> boolean containsAll(Set<T> set, List<T> values) {
        return set != null ? set.containsAll(values) : false;
    }

    public static <T> T first(Set<T> set) {
        return isEmpty(set) ? null : set.iterator().next();
    }

    public static <T, R> R firstMap(Set<T> set, Function<T, R> mapper) {
        return Objects.map(first(set), mapper);
    }

    public static <T> void firstPresent(Set<T> set, Consumer<T> consumer) {
        Optional.ofNullable(first(set)).ifPresent(consumer);
    }

    public static <T> Set<T> filter(Set<T> set, Predicate<? super T> filter) {
        if (isEmpty(set)) {
            return empty();
        }
        return set.stream().filter(filter).collect(Collectors.toSet());
    }

    public static <T, R> List<R> toList(Set<T> set, Function<T, R> mapper) {
        if (isEmpty(set)) {
            return Lists.empty();
        }
        return set.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Set<T> set, Predicate<? super T> filter, Function<T, R> mapper) {
        if (isEmpty(set)) {
            return Lists.empty();
        }
        return set.stream().filter(filter).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(Set<T> set, Function<T, R> mapper) {
        if (isEmpty(set)) {
            return empty();
        }
        return set.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> toSet(Set<T> set, Predicate<? super T> filter, Function<T, R> mapper) {
        if (isEmpty(set)) {
            return empty();
        }
        return set.stream().filter(filter).map(mapper).collect(Collectors.toSet());
    }

    public static <T, K, V> Map<K, V> toMap(Set<T> set, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper) {
        if (isEmpty(set)) {
            return Maps.empty();
        }
        return set.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, K, V> Map<K, V> toMap(Set<T> set, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper,
                                            BinaryOperator<V> mergeFunction) {
        if (isEmpty(set)) {
            return Maps.empty();
        }
        return set.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    private Sets() {}
}
