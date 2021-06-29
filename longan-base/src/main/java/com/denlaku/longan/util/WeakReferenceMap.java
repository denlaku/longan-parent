package com.denlaku.longan.util;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianx
 */
public final class WeakReferenceMap<K, V> extends ReferenceMap<K, V> {

	public WeakReferenceMap(Map<K, Reference<V>> delegate) {
		super(delegate);
	}

	public WeakReferenceMap() {
		super(new ConcurrentHashMap<>());
	}

	@Override
	Reference<V> fold(V value) {
		return new WeakReference<V>(value);
	}

}