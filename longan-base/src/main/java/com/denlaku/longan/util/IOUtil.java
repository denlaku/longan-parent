package com.denlaku.longan.util;

import java.io.Closeable;

/**
 * @author tianx
 */
public class IOUtil {

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
			}
		}
	}

	private IOUtil() {
	}
}
