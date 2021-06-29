package com.denlaku.longan.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author tianx
 */
public class Collections {
    public static <T> int size(Collection<T> list) {
        return list == null ? 0 : list.size();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <T> boolean isNoneNull(Collection<T> collection) {
        if (collection == null) {
            return true;
        }
        for (T t : collection) {
            if (t == null) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean contains(Collection<T> collection, T value) {
        return collection != null ? collection.contains(value) : false;
    }

    public static <T> boolean containsAll(Collection<T> collection, List<T> values) {
        return collection != null ? collection.containsAll(values) : false;
    }

    public static <T> Collection<T> defaultIfNull(Collection<T> collection, List<T> defaultValue) {
        return collection != null ? collection : defaultValue;
    }

    public static <T> T first(Collection<T> collection) {
        return isEmpty(collection) ? null : collection.iterator().next();
    }

    public static <T, R> R firstMap(Collection<T> collection, Function<T, R> mapper) {
        return Objects.map(first(collection), mapper);
    }

    public static <T, R> List<R> toList(Collection<T> collection, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return Lists.empty();
        }
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Collection<T> collection, Predicate<? super T> filter, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return Lists.empty();
        }
        return collection.stream().filter(filter).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(Collection<T> collection, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return Sets.empty();
        }
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> toSet(Collection<T> collection, Predicate<? super T> filter, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return Sets.empty();
        }
        return collection.stream().filter(filter).map(mapper).collect(Collectors.toSet());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper) {
        if (isEmpty(collection)) {
            return Maps.empty();
        }
        return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> set, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper,
                                            BinaryOperator<V> mergeFunction) {
        if (isEmpty(set)) {
            return Maps.empty();
        }
        return set.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }
}
