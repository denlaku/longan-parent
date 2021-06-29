package com.denlaku.longan.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianx
 */
public final class Maps {

	private static final int MIN_SIZE = 2;
	private static final float LOAD_FACTOR = 0.75f;

	public static <K, V> Map<K, V> empty() {
		return ofSize(MIN_SIZE);
	}

	public static <K, V> Map<K, V> ofSize(int size) {
		return of(size, LOAD_FACTOR);
	}

	public static <K, V> Map<K, V> of(int size, float loadFactor) {
		int finalSize = size <= MIN_SIZE ? MIN_SIZE : size;
		float finalLoadFactor = loadFactor <= 0 || loadFactor > 1 ? LOAD_FACTOR : loadFactor;
		return new HashMap<>(finalSize, finalLoadFactor);
	}

	public static <K, V> Map<K, V> of(K[] keys, V[] values) {
		int l1 = keys.length;
		int l2 = values.length;
		int len = l1 < l2 ? l1 : l2;
		Map<K, V> map = ofSize(len);
		for (int i = 0; i < len; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}

	public static <K, V> Map<K, V> of(List<K> keys, List<V> values) {
		int l1 = keys.size();
		int l2 = values.size();
		int len = l1 < l2 ? l1 : l2;
		Map<K, V> map = ofSize(len);
		for (int i = 0; i < len; i++) {
			map.put(keys.get(i), values.get(i));
		}
		return map;
	}

	public static <K, V> int size(Map<K, V> map) {
		if (map == null) {
			return 0;
		}
		return map.size();
	}

	public static <K, V> Collection<V> values(Map<K, V> map) {
		return map != null ? map.values() : Lists.of();
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		if (map == null) {
			return true;
		}
		return map.isEmpty();
	}

	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	public static <K, V> boolean containsKey(Map<K, V> map, Object key) {
		return map != null ? map.containsKey(key) : false;
	}

	public static <K, V> boolean containsValue(Map<K, V> map, Object key) {
		return map != null ? map.containsValue(key) : false;
	}

	public static <K, V> V get(Map<K, V> map, Object key) {
		return get(map, key, null);
	}

	public static <K, V> V get(Map<K, V> map, Object key, V defaultValue) {
		if (map != null) {
			return map.get(key);
		}
		return defaultValue;
	}

	public static <K, V> String getString(Map<K, V> map, Object key) {
		V v = get(map, key);
		if (v != null) {
			return v.toString();
		}
		return null;
	}

	public static <K, V> Boolean getBoolean(Map<K, V> map, Object key) {
		V v = get(map, key);
		if (v != null) {
			if (v instanceof Boolean) {
				return (Boolean)v;
			}
			return Boolean.valueOf(v.toString());
		}
		return null;
	}

	public static <K, V> boolean getBooleanValue(Map<K, V> map, Object key) {
		Boolean bool = getBoolean(map, key);
		return bool != null ? bool.booleanValue() : false;
	}

	public static <K, V> Long getLong(Map<K, V> map, Object key) {
		V v = get(map, key);
		return Types.toLong(v);
	}

	public static <K, V> Long getLongValue(Map<K, V> map, Object key) {
		V v = get(map, key);
		return Types.toLongValue(v);
	}

	public static <K, V> Integer getInteger(Map<K, V> map, Object key) {
		V v = get(map, key);
		return Types.toInteger(v);
	}

	public static <K, V> int getIntValue(Map<K, V> map, Object key) {
		V v = get(map, key);
		return Types.toIntValue(v);
	}

	public static <K, V> Map<K, V> defaultIfNull(Map<K, V> value, Map<K, V> defaultValue) {
		return value != null ? value : defaultValue;
	}

	private Maps() {
	}
}
