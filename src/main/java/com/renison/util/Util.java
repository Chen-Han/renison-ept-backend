package com.renison.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

public class Util {
	private static Logger logger = Logger.getLogger(Util.class);
	public static final String SRC_PATH = "src/main/java/";
	public static final String MODEL_PACKAGE_PATH = "com/renison/model/";

	public static void forEachModel(Consumer<Class<?>> callback) throws IOException, ClassNotFoundException {
		Path modelDir = Paths.get(SRC_PATH, MODEL_PACKAGE_PATH);
		DirectoryStream<Path> dirStream = Files.newDirectoryStream(modelDir);
		for (Path file : dirStream) {
			if (Files.isDirectory(file) || !file.toString().endsWith(".java")) {
				continue;
			}
			String className = file.toString().substring(SRC_PATH.length());
			// Remove ".java" at the end of the file name to get the class name
			className = className.substring(0, className.length() - 5).replaceAll("/", ".");
			Class<?> modelClass = Class.forName(className);
			callback.accept(modelClass);
		}
	}

	public static String formatLocalDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static <T> void copyWithoutIdTime(T src, T dst) {
		BeanUtils.copyProperties(src, dst, "id", "createTimestamp", "updateTimestamp");
	}
}
