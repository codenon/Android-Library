
package cn.conon.android.modules.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

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
    private static String TAG = LOG_PREFIX;

    /**
     * Whether the logs are enabled in debug builds or not.
     */
    private static final boolean ENABLE_LOGS_IN_DEBUG = true;
    /**
     * Whether the logs are enabled in release builds or not.
     */
    private static final boolean ENABLE_LOGS_IN_RELEASE = true;

    public static String makeLogTag(Class<?> cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    private static boolean canLog(final String tag, int level) {
        if (BuildConfig.DEBUG) {
            return ENABLE_LOGS_IN_DEBUG /*&& Log.isLoggable(tag, level)*/;
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
    public static void v(String format, Object... args) {
        vt(TAG, buildMessage(format, args));
    }

    public static void v(Throwable cause, String format, Object... args) {
        vt(TAG, buildMessage(format, args), cause);
    }

    public static void v(Intent intent, String format, Object... args) {
        vt(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void vt(final String tag, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, buildMessage(format, args));
        }
    }

    public static void vt(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, buildMessage(format, args), cause);
        }
    }

    public static void vt(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.VERBOSE)) {
            Log.v(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    public static void d(String format, Object... args) {
        dt(TAG, buildMessage(format, args));
    }

    public static void d(Throwable cause, String format, Object... args) {
        dt(TAG, buildMessage(format, args), cause);
    }

    public static void d(Intent intent, String format, Object... args) {
        dt(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void dt(final String tag, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildMessage(format, args));
        }
    }

    public static void dt(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildMessage(format, args), cause);
        }
    }

    public static void dt(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.DEBUG)) {
            Log.d(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    public static void i(String format, Object... args) {
        it(TAG, buildMessage(format, args));
    }

    public static void i(Throwable cause, String format, Object... args) {
        it(TAG, buildMessage(format, args), cause);
    }

    public static void i(Intent intent, String format, Object... args) {
        it(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void it(final String tag, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildMessage(format, args));
        }
    }

    public static void it(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildMessage(format, args), cause);
        }
    }

    public static void it(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.INFO)) {
            Log.i(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    public static void w(String format, Object... args) {
        wt(TAG, buildMessage(format, args));
    }

    public static void w(Throwable cause, String format, Object... args) {
        wt(TAG, buildMessage(format, args), cause);
    }

    public static void w(Intent intent, String format, Object... args) {
        wt(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void wt(final String tag, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildMessage(format, args));
        }
    }

    public static void wt(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildMessage(format, args), cause);
        }
    }

    public static void wt(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.WARN)) {
            Log.w(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    public static void e(String format, Object... args) {
        et(TAG, buildMessage(format, args));
    }

    public static void e(Throwable cause, String format, Object... args) {
        et(TAG, buildMessage(format, args), cause);
    }

    public static void e(Intent intent, String format, Object... args) {
        et(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void et(final String tag, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildMessage(format, args));
        }
    }

    public static void et(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildMessage(format, args), cause);
        }
    }

    public static void et(final String tag, Intent intent, String format, Object... args) {
        if (canLog(tag, Log.ERROR)) {
            Log.e(tag, buildIntent(buildMessage(format, args), intent));
        }
    }

    // -------------------------------------------------------------------------------------------

    public static void wtf(String format, Object... args) {
        wtft(TAG, buildMessage(format, args));
    }

    public static void wtf(Throwable cause, String format, Object... args) {
        wtft(TAG, buildMessage(format, args), cause);
    }

    public static void wtf(Intent intent, String format, Object... args) {
        wtft(TAG, buildIntent(buildMessage(format, args), intent));
    }

    public static void wtft(final String tag, String format, Object... args) {
        if (canLog(tag, Log.ASSERT)) {
            Log.wtf(tag, buildMessage(format, args));
        }
    }

    public static void wtft(final String tag, Throwable cause, String format, Object... args) {
        if (canLog(tag, Log.ASSERT)) {
            Log.wtf(tag, buildMessage(format, args), cause);
        }
    }

    public static void wtft(final String tag, Intent intent, String format, Object... args) {
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
        if ((!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
                || (!Environment.getExternalStorageDirectory().canWrite())) {
            return;
        }
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + filename;
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
        if ((!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
                || (!Environment.getExternalStorageDirectory().canWrite())) {
            return;
        }
        String directory = Environment.getExternalStorageDirectory().getPath() + File.separator + folder;
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdir();
        }
        String path = directory + File.separator + filename;
        writeBytes(path, str);
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------------------------
    private static String buildIntent(String msg, Intent intent) {
        StringBuffer sb = new StringBuffer();
        sb.append(msg);
        sb.append('\n');
        if (intent == null) {
            sb.append("Intent is null.");
            return sb.toString();
        }
        sb.append("Intent action:");
        sb.append(intent.getAction());
        sb.append('\n');

        // build extras
        Bundle extras = intent.getExtras();
        sb.append("Intent Bundle Extras:");
        sb.append(buildBundle(extras));
        return sb.toString();
    }

    private static StringBuffer buildBundle(Bundle bundle) {
        StringBuffer sb = new StringBuffer();
        sb.append('\n');
        if (bundle == null) {
            sb.append("bundle:null");
            return sb;
        }
        Set<String> keys = bundle.keySet();
        if (keys == null) {
            sb.append("bundle:bundle.keySet() == null");
            return sb;
        }
        if (keys.isEmpty()) {
            sb.append("bundle:bundle.keySet().isEmpty()");
            return sb;
        }
        //sb.append("extras:\n");
        for (String key : keys) {
            Object value = bundle.get(key);
            sb.append(key);
            sb.append("=");
            sb.append(value.getClass().getName());
            sb.append("-->");
            sb.append(value instanceof Bundle ? buildBundle((Bundle) value) : sb.append(value));
            sb.append('\n');
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
                TAG = makeLogTag(clazz);

                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + trace[i].getMethodName() + "[" + trace[i].getLineNumber() + "]";
                break;
            }
        }
        return String.format(Locale.US, "[%d,%s] %s: %s", Thread.currentThread().getId(), Thread.currentThread().getName(), caller, msg);
    }

    private static String formatMessage(String format, Object... args) {
        return String.format(format, args);
    }


    // -------------------------------------------------------------------------------------------

    private Logger() {
    }
}
