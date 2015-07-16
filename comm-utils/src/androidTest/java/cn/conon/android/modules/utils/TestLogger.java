package cn.conon.android.modules.utils;

import junit.framework.Test;

import android.content.Intent;
import android.os.Bundle;
import android.test.AndroidTestCase;

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
        Intent intent = new Intent("testaction");
        intent.putExtra("extra name", "extra value");
        Bundle bundle = new Bundle();
        bundle.putString("bundle name", "bundle value");
        intent.putExtra("extra Bundle", bundle);

        // adb shell setprop log.tag.cn.conon_TestLogger VEROBSE
        Logger.v(TAG, "format = %s", "args");
        Logger.v(TAG, intent, "format = %s", "Intent");
        Logger.v(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        // adb shell setprop log.tag.cn.conon_TestLogger DEBUG
        Logger.d(TAG, "format = %s", "args");
        Logger.d(TAG, intent, "format = %s", "Intent");
        Logger.d(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.i(TAG, "format = %s", "args");
        Logger.i(TAG, intent, "format = %s", "Intent");
        Logger.i(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.w(TAG, "format = %s", "args");
        Logger.w(TAG, intent, "format = %s", "Intent");
        Logger.w(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.e(TAG, "format = %s", "args");
        Logger.e(TAG, intent, "format = %s", "Intent");
        Logger.e(TAG, new Exception("detailMessage"), "format = %s", "Exception");
        Logger.wtf(TAG, "format = %s", "args");
        Logger.wtf(TAG, intent, "format = %s", "Intent");
        Logger.wtf(TAG, new Exception("detailMessage"), "format = %s", "Exception");
    }

    public static Test suite() {
        TestLogger suite = new TestLogger();
        return suite;
    }
}
