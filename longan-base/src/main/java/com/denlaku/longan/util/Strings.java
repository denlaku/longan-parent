package com.denlaku.longan.util;

/**
 * @author tianx
 */
public final class Strings {

	public static boolean isEmpty(final String cs) {
		if (cs == null || cs.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(final String cs) {
		return !isEmpty(cs);
	}

	public static boolean isBlank(final String cs) {
		if (cs == null || cs.length() == 0) {
			return true;
		}
		return cs.trim().length() == 0;
	}

	public static boolean isNotBlank(final String cs) {
		return !isBlank(cs);
	}

	public static boolean isAllEmpty(final String ...cs) {
		if (cs != null) {
			for (String str : cs) {
				if (isNotEmpty(str)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isAnyEmpty(final String ...cs) {
		if (cs != null) {
			for (String str : cs) {
				if (isEmpty(str)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isNoneEmpty(final String ...cs) {
		if (cs != null) {
			for (String str : cs) {
				if (isEmpty(str)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isAllBlank(final String ...cs) {
		if (cs != null) {
			for (String str : cs) {
				if (isNotBlank(str)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isNoneBlank(final String ...cs) {
		if (cs != null) {
			for (String str : cs) {
				if (isBlank(str)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean equalsAny(String a, String ...values) {
		return Objects.equalsAny(a, values);
	}

	public static boolean startsWith(String str, String prefix) {
		return str == null ? false : str.startsWith(prefix);
	}

	public static boolean endsWith(String str, String suffix) {
		return str == null ? false : str.endsWith(suffix);
	}

	public static String defaultIfNull(String value, String defaultValue) {
		return value == null ? defaultValue : value;
	}

	public static String defaultIfEmpty(String value, String defaultValue) {
		return isEmpty(value) ? defaultValue : value;
	}

	private Strings() {
	}
}
