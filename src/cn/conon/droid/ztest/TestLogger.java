
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
        Logger.v(TAG, "testLogTag() Intent", new Intent("testaction"));
        Logger.v(TAG, "Exception", new Exception("detailMessage"));
        // adb shell setprop log.tag.cn.conon_TestLogger DEBUG
        Logger.d(TAG, "format = %s", "args");
        Logger.d(TAG, "testLogTag() Intent", new Intent("testaction"));
        Logger.d(TAG, "Exception", new Exception("detailMessage"));
        Logger.i(TAG, "format = %s", "args");
        Logger.i(TAG, "testLogTag() Intent", new Intent("testaction"));
        Logger.i(TAG, "Exception", new Exception("detailMessage"));
        Logger.w(TAG, "format = %s", "args");
        Logger.w(TAG, "testLogTag() Intent", new Intent("testaction"));
        Logger.w(TAG, "Exception", new Exception("detailMessage"));
        Logger.e(TAG, "format = %s", "args");
        Logger.e(TAG, "testLogTag() Intent", new Intent("testaction"));
        Logger.e(TAG, "Exception", new Exception("detailMessage"));
    }

    public static Test suite() {
        TestLogger suite = new TestLogger();
        return suite;
    }
}
