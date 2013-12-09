package cn.conon.droid.ztest;

import junit.framework.Test;
import android.test.AndroidTestCase;
import cn.conon.droid.utils.Logger;

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

	public void testLogTag() throws Exception {
		Logger.e(TAG, "format = %s", "args");
	}

	public static Test suite() {
		TestLogger suite = new TestLogger();
		return suite;
	}
}
