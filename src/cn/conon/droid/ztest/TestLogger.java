
package cn.conon.droid.ztest;

import junit.framework.Test;
import android.content.Intent;
import android.test.AndroidTestCase;
import cn.conon.droid.util.Logger;

public class TestLogger extends AndroidTestCase {
    private String TAG = Logger.makeLogTag(getClass());

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Logger.e(TAG, "setUp()");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Logger.e(TAG, "tearDown()");
    }

    public void testLogger() throws Exception {
        // adb shell setprop log.tag.cn.conon_TestLogger VEROBSE
        Logger.v(TAG, "format = %s", "args");
        Logger.v(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.v(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        // adb shell setprop log.tag.cn.conon_TestLogger DEBUG
        Logger.d(TAG, "format = %s", "args");
        Logger.d(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.d(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.i(TAG, "format = %s", "args");
        Logger.i(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.i(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.w(TAG, "format = %s", "args");
        Logger.w(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.w(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.e(TAG, "format = %s", "args");
        Logger.e(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.e(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.wtf(TAG, "format = %s", "args");
        Logger.wtf(TAG, new Intent("testaction"), "format = %s", "Intent");
        Logger.wtf(TAG, new Exception("detailMessage"), "format = %s", "Exception");
    }

    public static Test suite() {
        TestLogger suite = new TestLogger();
        return suite;
    }
}
