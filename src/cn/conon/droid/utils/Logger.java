
package cn.conon.droid.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import cn.conon.droid.demo.BuildConfig;

/**
 * 日志工具类
 *
 * @author ddc
 * @date: 2013-9-7 下午7:24:19
 */
public class Logger {
    private static final String LOG_PREFIX = "cn.conon_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    /** Whether the logs are enabled in debug builds or not. */
    private static final boolean ENABLE_LOGS_IN_DEBUG = true;
    /** Whether the logs are enabled in release builds or not. */
    private static final boolean ENABLE_LOGS_IN_RELEASE = false;

    public static String makeLogTag(Class<?> cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX
                    + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH
                            - 1);
        }
        return LOG_PREFIX + str;
    }

    private static boolean canLog(final String tag, int level) {
        if (BuildConfig.DEBUG) {
            return ENABLE_LOGS_IN_DEBUG && Log.isLoggable(tag, level);
        }
        return ENABLE_LOGS_IN_RELEASE;
    }

    private static boolean canWriteFile() {
        if (BuildConfig.DEBUG) {
            return ENABLE_LOGS_IN_DEBUG;
        }
        return ENABLE_LOGS_IN_RELEASE;
    }

    // -------------------------------------------------------------------------------------------
    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void v(final String tag, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, logFormat(format, args));
        }
    }

    /**
     * @param tag
     * @param message
     * @param cause
     */
    public static void v(final String tag, String message, Throwable cause) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, message, cause);
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void d(final String tag, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, logFormat(format, args));
        }
    }

    /**
     * @param tag
     * @param message
     * @param cause
     */
    public static void d(final String tag, String message, Throwable cause) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, message, cause);
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void i(final String tag, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, logFormat(format, args));
        }
    }

    /**
     * @param tag
     * @param message
     * @param cause
     */
    public static void i(final String tag, String message, Throwable cause) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, message, cause);
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void w(final String tag, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, logFormat(format, args));
        }
    }

    /**
     * @param tag
     * @param message
     * @param cause
     */
    public static void w(final String tag, String message, Throwable cause) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, message, cause);
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void e(final String tag, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, logFormat(format, args));
        }
    }

    /**
     * @param tag
     * @param message
     * @param cause
     */
    public static void e(final String tag, String message, Throwable cause) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, message, cause);
        }
    }

    // -------------------------------------------------------------------------------------------
    /**
     * 将字符串写入SDcard文件中
     *
     * @param filename 文件名
     * @param str 要写入的字符串
     */
    public static void writeFile(String filename, String str) {
        if (!canWriteFile()) {
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
     * @param folder 文件夹名
     * @param filename 文件名
     * @param str 要写入的字符串
     */
    public static void writeFile(String folder, String filename, String str) {
        if (!canWriteFile()) {
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

    // -------------------------------------------------------------------------------------------
    /**
     * @param array
     * @return
     */
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

    /**
     * @param format
     * @param args
     * @return
     */
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

    // -------------------------------------------------------------------------------------------

    private Logger() {
    }
}
