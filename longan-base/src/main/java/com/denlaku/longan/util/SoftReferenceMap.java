package com.denlaku.longan.util;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianx
 */
public final class SoftReferenceMap<K, V> extends ReferenceMap<K, V> {

	public SoftReferenceMap(Map<K, Reference<V>> delegate) {
		super(delegate);
	}

	public SoftReferenceMap() {
		super(new ConcurrentHashMap<>());
	}

	@Override
	Reference<V> fold(V value) {
		return new SoftReference<V>(value);
	}

}