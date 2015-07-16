
package cn.conon.android.modules.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 *
 * @author codenon
 * @date: 2013-9-7 下午7:24:19
 */
public class Logger {
    private static final String LOG_PREFIX = "cn.conon_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    /**
     * Whether the logs are enabled in debug builds or not.
     */
    private static final boolean ENABLE_LOGS_IN_DEBUG = true;
    /**
     * Whether the logs are enabled in release builds or not.
     */
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
//        if (BuildConfig.DEBUG) {
//            return ENABLE_LOGS_IN_DEBUG && Log.isLoggable(tag, level);
//        }
//        return ENABLE_LOGS_IN_RELEASE;
        return true;
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
            Log.v(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void v(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void v(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void d(final String tag, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void d(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void d(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void i(final String tag, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void i(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void i(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void w(final String tag, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void w(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void w(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void e(final String tag, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void e(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void e(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    /**
     * @param tag
     * @param format
     * @param args
     */
    public static void wtf(final String tag, String format, Object... args) {
        if (canLog(tag, Log.ASSERT)) {
            Log.wtf(tag, buildMessage(format, args));
        }
    }

    /**
     * @param tag
     * @param cause
     * @param format
     * @param args
     */
    public static void wtf(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.ASSERT)) {
            Log.wtf(tag, buildMessage(format, args), cause);
        }
    }

    /**
     * @param tag
     * @param intent
     * @param format
     * @param args
     */
    public static void wtf(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.ASSERT)) {
            Log.wtf(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    /**
     * 将字符串写入SDcard文件中
     *
     * @param filename 文件名
     * @param str      要写入的字符串
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
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator
                + filename;

        writeBytes(path, str);
    }

    /**
     * 将字符串写入SDcard文件夹中
     *
     * @param folder   文件夹名
     * @param filename 文件名
     * @param str      要写入的字符串
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
                + File.separator + folder;
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdir();
        }
        String path = directory + File.separator + filename;

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
     * @param msg
     * @param intent
     * @return
     */
    private static String buildIntent(String msg, Intent intent) {
        StringBuffer sb = new StringBuffer();
        sb.append(msg);
        sb.append('\n');
        if (intent == null) {
            sb.append("Intent is null.");
            return sb.toString();
        }
        sb.append("Intent action=" + intent.getAction() + '\n');

        // build extras
        Bundle extras = intent.getExtras();
        sb.append(buildBundle(extras));
        return sb.toString();
    }

    /**
     * build extras
     *
     * @param extras
     * @return
     */
    private static StringBuffer buildBundle(Bundle extras) {
        StringBuffer sb = new StringBuffer();
        if (extras != null) {
            Set<String> keys = extras.keySet();
            if (keys == null || (keys != null && keys.isEmpty())) {
                sb.append("extras:none");
            } else {
                sb.append("extras:\n");
                for (String key : keys) {
                    Object value = extras.get(key);
                    if (value instanceof Bundle) {
                        sb.append(key + "=" + buildBundle((Bundle) value) + '\n');
                    } else {
                        sb.append(key + "=" + value + '\n');
                    }
                }
            }
        } else {
            sb.append("    extras: none");
        }
        return sb;
    }

    /**
     * Formats the caller's provided message and prepends useful info like
     * calling thread ID and method name.
     */
    private static String buildMessage(String format, Object... args) {
        String msg = formatMessage(format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

        String caller = "<unknown>";
        // Walk up the stack looking for the first caller outside of VolleyLog.
        // It will be at least two frames up, so start there.
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(Logger.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d,%s] %s: %s",
                Thread.currentThread().getId(), Thread.currentThread().getName(), caller, msg);
    }

    /**
     * @param format
     * @param args
     * @return
     */
    private static String formatMessage(String format, Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String[]) {
                args[i] = prettyArray((String[]) args[i]);
            }
        }
        return String.format(format, args);
    }

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

    // -------------------------------------------------------------------------------------------

    private Logger() {
    }
}
