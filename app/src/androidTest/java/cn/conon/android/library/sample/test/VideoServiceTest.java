package cn.conon.android.library.sample.test;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.List;

import cn.conon.android.library.sample.network.video.domain.Video;
import cn.conon.android.library.sample.network.video.service.VideoService;


public class VideoServiceTest extends AndroidTestCase {
    private static final String TAG = "VideoServiceTest";

    public void testVideos() throws Throwable {
        List<Video> videos = VideoService.getXMLLastVideos();
        for (Video video : videos) {
            Log.i(TAG, video.toString());
        }
    }
}
