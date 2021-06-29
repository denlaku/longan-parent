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
public final class Lists {

	public static <T> List<T> empty() {
		return ofSize(0);
	}

	public static <T> List<T> ofSize(int size) {
		return new ArrayList<>(size < 0 ? 0 : size);
	}

	public static <T> List<T> of(T ...values) {
		if (values == null) {
			return empty();
		}
		List<T> list = ofSize(values.length);
		for (T value : values) {
			list.add(value);
		}
		return list;
	}

	public static <T> List<T> of(Collection<T> values) {
		if (values == null) {
			return empty();
		}
		List<T> list = ofSize(values.size());
		values.addAll(values);
		return list;
	}

	public static <T> boolean isEmpty(List<T> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static <T> boolean isNotEmpty(List<T> list) {
		return !isEmpty(list);
	}

	public static <T> boolean isNoneNull(List<T> list) {
		if (list == null) {
			return true;
		}
		for (T t : list) {
			if (t == null) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean contains(List<T> list, T value) {
		return list != null ? list.contains(value) : false;
	}

	public static <T> boolean containsAll(List<T> list, List<T> values) {
		return list != null ? list.containsAll(values) : false;
	}

	public static <T> List<T> defaultIfNull(List<T> list, List<T> defaultValue) {
		return list != null ? list : defaultValue;
	}

	public static <T> List<T> emptyIfNull(List<T> list) {
		return list != null ? list : empty();
	}

	public static <T> T first(List<T> list) {
		return Collections.isEmpty(list) ? null : list.get(0);
	}

	public static <T> T last(List<T> list) {
		return Collections.isEmpty(list) ? null : list.get(list.size() - 1);
	}

	public static <T, R> R firstMap(List<T> list, Function<T, R> mapper) {
		return Objects.map(first(list), mapper);
	}

	public static <T> void firstPresent(List<T> list, Consumer<T> consumer) {
		Optional.ofNullable(first(list)).ifPresent(consumer);
	}

	public static <T> List<T> filter(List<T> list, Predicate<? super T> filter) {
		if (isEmpty(list)) {
			return empty();
		}
		return list.stream().filter(filter).collect(Collectors.toList());
	}

	public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
		if (isEmpty(list)) {
			return empty();
		}
		return list.stream().map(mapper).collect(Collectors.toList());
	}

	public static <T, R> List<R> toList(List<T> list, Predicate<? super T> filter, Function<T, R> mapper) {
		if (isEmpty(list)) {
			return null;
		}
		return list.stream().filter(filter).map(mapper).collect(Collectors.toList());
	}

	public static <T, R> Set<R> toSet(List<T> list, Function<T, R> mapper) {
		if (isEmpty(list)) {
			return Sets.empty();
		}
		return list.stream().map(mapper).collect(Collectors.toSet());
	}

	public static <T, R> Set<R> toSet(List<T> list, Predicate<? super T> filter, Function<T, R> mapper) {
		if (isEmpty(list)) {
			return Sets.empty();
		}
		return list.stream().filter(filter).map(mapper).collect(Collectors.toSet());
	}

	public static <T, K, V> Map<K, V> toMap(List<T> list, Function<? super T, ? extends K> keyMapper,
										  Function<? super T, ? extends V> valueMapper) {
		if (isEmpty(list)) {
			return Maps.empty();
		}
		return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
	}

	public static <T, K, V> Map<K, V> toMap(List<T> list, Function<? super T, ? extends K> keyMapper,
										  Function<? super T, ? extends V> valueMapper,
										  BinaryOperator<V> mergeFunction) {
		if (isEmpty(list)) {
			return Maps.empty();
		}
		return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
	}

	private Lists() {
	}
}
