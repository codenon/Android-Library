package cn.conon.droid.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 * 
 * @author DDC
 * @date 2013-9-7 下午7:24:19
 */
public class LogTag {
	private final static String TAG = "cn.conon";
	private static boolean mIsRelease = false;

	private LogTag() {
	}

	public static boolean isRelease() {
		return mIsRelease;
	}

	public static void setIsRelease(boolean mIsRelease) {
		LogTag.mIsRelease = mIsRelease;
	}

	// ----------------------------------------------------------
	/**
	 * 
	 * @param TAG
	 * @param format
	 * @param args
	 */
	public static void v(String TAG, String format, Object... args) {
		if (isRelease()) {
			return;
		}
		Log.v(TAG, logFormat(format, args));
	}

	/**
	 * 
	 * 
	 * @param TAG
	 * @param format
	 * @param args
	 */
	public static void d(String TAG, String format, Object... args) {
		if (isRelease()) {
			return;
		}
		Log.d(TAG, logFormat(format, args));
	}

	/**
	 * 
	 * 
	 * @param TAG
	 * @param format
	 * @param args
	 */
	public static void i(String TAG, String format, Object... args) {
		if (isRelease()) {
			return;
		}
		Log.i(TAG, logFormat(format, args));
	}

	/**
	 * 
	 * 
	 * @param TAG
	 * @param format
	 * @param args
	 */
	public static void w(String TAG, String format, Object... args) {
		if (isRelease()) {
			return;
		}
		Log.w(TAG, logFormat(format, args));
	}

	/**
	 * 
	 * 
	 * @param TAG
	 * @param format
	 * @param args
	 */
	public static void e(String TAG, String format, Object... args) {
		if (isRelease()) {
			return;
		}
		Log.e(TAG, logFormat(format, args));
	}

	// ----------------------------------------------------------
	/**
	 * 
	 * @param TAG
	 * @param tr
	 */
	public static void e(String TAG, Throwable tr) {
		if (isRelease()) {
			return;
		}
		Log.e(TAG, tr.getMessage() + '\n' + Log.getStackTraceString(tr));
	}

	/**
	 * 
	 * @param TAG
	 * @param message
	 * @param tr
	 */
	public static void e(String TAG, String message, Throwable tr) {
		if (isRelease()) {
			return;
		}
		Log.e(TAG,
				message + '\n' + tr.getMessage() + '\n'
						+ Log.getStackTraceString(tr));
	}

	// ----------------------------------------------------------
	/**
	 * 将字符串写入SDcard文件中
	 * 
	 * @param filename
	 *            文件名
	 * @param str
	 *            要写入的字符串
	 */
	public static void writeFile(String filename, String str) {
		if (isRelease()) {
			return;
		}
		if ((!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()))
				|| (!Environment.getExternalStorageDirectory().canWrite())) {
			return;
		}
		String path = Environment.getExternalStorageDirectory().getPath() + "/"
				+ filename;

		writeBytes(path, str);
	}

	/**
	 * 将字符串写入SDcard文件夹中
	 * 
	 * @param folder
	 *            文件夹名
	 * @param filename
	 *            文件名
	 * @param str
	 *            要写入的字符串
	 */
	public static void writeFile(String folder, String filename, String str) {
		if (isRelease()) {
			return;
		}
		if ((!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()))
				|| (!Environment.getExternalStorageDirectory().canWrite())) {
			return;
		}
		String directory = Environment.getExternalStorageDirectory().getPath()
				+ "/" + folder;
		File directoryFile = new File(directory);
		if (!directoryFile.exists()) {
			directoryFile.mkdir();
		}
		String path = directory + "/" + filename;

		writeBytes(path, str);
	}

	/**
	 * 
	 * @param path
	 * @param str
	 */
	private static void writeBytes(String path, String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		byte[] cbs = str.getBytes();
		ByteArrayInputStream in = new ByteArrayInputStream(cbs);
		try {
			FileOutputStream out = new FileOutputStream(path, true);
			int read;
			byte[] buffer = new byte[4096];
			while ((read = in.read(buffer)) > 0) {
				out.write(buffer, 0, read);
			}
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------
	private static String prettyArray(String[] array) {
		try {
			if (array.length == 0) {
				return "[]";
			}
			StringBuilder sb = new StringBuilder("[");
			int len = array.length - 1;
			for (int i = 0; i < len; i++) {
				sb.append(array[i]);
				sb.append(", ");
			}
			sb.append(array[len]);
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			return "null";
		}
	}

	private static String logFormat(String format, Object... args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof String[]) {
				args[i] = prettyArray((String[]) args[i]);
			}
		}
		String s = String.format(format, args);
		s = "[" + Thread.currentThread().getId() + "] " + s;
		return s;
	}

	// ----------------------------------------------------------
	public static String makeLogTag(Class<?> cls) {
		return TAG + "_" + cls.getSimpleName();
	}
}
