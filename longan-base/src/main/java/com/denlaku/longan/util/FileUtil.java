package com.denlaku.longan.util;

import java.io.File;

/**
 * @author tianx
 */
public class FileUtil {

	public static void delete(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f: files) {
				delete(f);
			}
		}
		file.delete();
	}
	
	private FileUtil() {}
}
