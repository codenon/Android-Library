package cn.conon.droid.demo.ztest;

import junit.framework.Test;
import android.test.AndroidTestCase;
import cn.conon.droid.demo.utils.LogTag;

public class TestLogger extends AndroidTestCase {
	private String TAG = LogTag.makeLogTag(getClass());

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		LogTag.e(TAG, "setUp()");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		LogTag.e(TAG, "tearDown()");
	}

	public void testLogTag() throws Exception {
		LogTag.e(TAG, "format = %s", "args");
	}

	public static Test suite() {
		TestLogger suite = new TestLogger();
		return suite;
	}
}
