package com.denlaku.longan.util;

/**
 * @author tianx
 */
public class ExceptionUtil {

	public static Throwable getRoot(Throwable throwable) {
		Throwable root = throwable;
		if (root != null) {
			while (root.getCause() != null) {
				root = root.getCause();
			}
		}
		return root;
	}

	private ExceptionUtil() {
	}
}
