
package cn.conon.android.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author codenon
 * @date 2013-9-8 上午12:01:11
 */
public class ToastHelper {
    private ToastHelper() {
    }

    public static void showText(final Context context, final String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showNotImplemented(final Context context) {
        showText(context, "Not yet implemented");
    }
}
