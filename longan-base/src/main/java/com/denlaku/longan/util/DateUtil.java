package com.denlaku.longan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianx
 */
public class DateUtil {

	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String format(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	private DateUtil() {
	}
}
