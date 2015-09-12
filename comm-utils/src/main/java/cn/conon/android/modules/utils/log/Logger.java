package cn.conon.android.modules.utils.log;

import android.content.Intent;

import cn.conon.android.modules.utils.BuildConfig;

/**
 * Logger is a wrapper of {@link android.util.Log}
 * But more pretty, simple and powerful
 */
public final class Logger {

    private static final Printer PRINTER = new LoggerPrinter();

    //no instance
    private Logger() {
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return LoggerSettingsthe settings object
     */
    public static LoggerSettings init() {
        return PRINTER.init();
    }

    public static Printer t(String tag) {
        return PRINTER.t(tag, PRINTER.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        return PRINTER.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return PRINTER.t(tag, methodCount);
    }

    //==============================================================================================
    public static void v(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.v(message, args);
        }
    }

    public static void d(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.d(message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.i(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.w(message, args);
        }
    }

    public static void e(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.e(message, args);
        }

    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.e(throwable, message, args);
        }
    }

    public static void wtf(String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.wtf(message, args);
        }
    }

    //----------------------------------------------------------------------------------------------
    public static void d(Intent intent, String message, Object... args) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.d(intent, message, args);
        }
    }

    public static void object(Object object) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.object(object);
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.json(json);
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (isDebuggable()) {
            PRINTER.generateTag(Utils.generateTag());
            PRINTER.xml(xml);
        }
    }

    //==============================================================================================

    public static void writeFile(String filename, String message) {
        if (isDebuggable()) {
            PRINTER.writeFile(filename, message);
        }
    }

    public static void writeFile(String folder, String filename, String message) {
        if (isDebuggable()) {
            PRINTER.writeFile(folder, filename, message);
        }
    }
}