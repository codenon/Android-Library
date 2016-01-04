package cn.conon.android.library.sample.network.video;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.conon.android.library.sample.R;
import cn.conon.android.library.sample.network.video.domain.Video;
import cn.conon.android.library.sample.network.video.service.VideoService;
import cn.conon.android.library.utils.log.Logger;

public class GetVideoListXmlActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_video_get_list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Video> videos = VideoService.getXMLLastVideos();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView(videos);
                        }
                    });
                } catch (Exception e) {
                    Logger.e(e, "");
                }
            }
        }).start();

    }

    private void initView(List<Video> videos) {
        ListView listView = (ListView) this.findViewById(R.id.listView);
        List<HashMap<String, Object>> data = new ArrayList<>();
        for (Video video : videos) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("id", video.getId());
            item.put("title", video.getTitle());
            item.put("time_length", "时长:" + video.getTimelength());
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item_network_video_get_list,
                new String[]{"title", "time_length"}, new int[]{R.id.tv_title, R.id.tv_time_length});
        listView.setAdapter(adapter);

    }
}