package cn.conon.android.library.sample.network.video.service;

import android.util.Xml;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.conon.android.library.sample.network.video.domain.Video;
import cn.conon.android.library.sample.network.video.utils.FormFile;
import cn.conon.android.library.sample.network.video.utils.SocketHttpRequester;
import cn.conon.android.library.sample.network.video.utils.StreamTool;
import cn.conon.android.library.utils.log.Logger;

public class VideoService {
    private static final String HOST = "http://192.168.0.195:8080/JEE-Sample/struts1/provider/video/";
    private static final String HOST_MANAGE = HOST + "manage.do";

    public static boolean saveFile(HashMap<String, String> params, File uploadFile) throws Exception {
        FormFile formFile = new FormFile(uploadFile, "videofile", "audio/mpeg");
        return SocketHttpRequester.post(HOST_MANAGE, params, formFile);
    }

    /**
     * 发送POST请求
     *
     * @param params 请求参数
     * @return 请求是否成功, true为请求成功, false为请求失败
     */
    public static boolean sendPOSTRequestHttpClient(HashMap<String, String> params) throws Exception {
        List<NameValuePair> parampair = new ArrayList<NameValuePair>();//存放请求参数
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parampair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parampair, "UTF-8");
        HttpPost post = new HttpPost(HOST_MANAGE);
        post.setEntity(entity);//设置实体数据
        DefaultHttpClient client = new DefaultHttpClient();//浏览器
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            return true;
        }
        return false;
    }

    /**
     * 发送POST请求
     *
     * @param params 请求参数
     * @return 请求是否成功, true为请求成功, false为请求失败
     */
    public static boolean sendPOSTRequest(HashMap<String, String> params) throws Exception {
        StringBuilder entity = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                entity.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "UTF-8")).append('&');
            }
            entity.deleteCharAt(entity.length() - 1);
        }
        byte[] entitydata = entity.toString().getBytes();
        HttpURLConnection conn = (HttpURLConnection) new URL(HOST_MANAGE).openConnection();
        conn.setConnectTimeout(5000);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);//允许对外输出实体数据
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(entitydata);
        if (conn.getResponseCode() == 200) {
            return true;
        }
        return false;
    }

    /**
     * 发送GET请求
     *
     * @param params 请求参数
     * @return 请求是否成功, true为请求成功, false为请求失败
     */
    public static boolean sendGETRequest(HashMap<String, String> params) throws Exception {
        StringBuilder url = new StringBuilder(HOST_MANAGE);
        if (params != null && !params.isEmpty()) {
            url.append('?');
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "UTF-8")).append('&');
            }
            url.deleteCharAt(url.length() - 1);
        }
        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            return true;
        }
        return false;
    }

    /**
     * 获取最新视频视讯
     *
     * @return
     * @throws Exception
     */
    public static List<Video> getXMLLastVideos() throws Exception {
        String path = HOST + "list.do?format=xml";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            return parseXML(inStream);
        }
        return null;
    }

    /**
     * 获取最新视频视讯
     *
     * @return
     * @throws Exception
     */
    public static List<Video> getJSONLastVideos() throws Exception {
        String path = HOST + "list.do?format=json";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            byte[] data = StreamTool.readStream(inStream);
            String json = new String(data);
            return parseJSON(json);
        }
        return null;
    }

    // [{id:23,title:"喜羊羊与灰太狼全集",timelength:45},{id:45,title:"实拍舰载直升东海救援演习",timelength:90},{id:78,title:"喀麦隆VS荷兰",timelength:20
    private static List<Video> parseJSON(String json) throws Exception {
        Logger.json(json);
        List<Video> videos = new ArrayList<Video>();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            videos.add(new Video(item.getInt("id"), item.getString("title"), item.getInt("timelength")));
        }
        return videos;
    }

    /*
      <?xml version="1.0" encoding="UTF-8" ?>
    - <videos>
        - <video id="23">
          <title>喜羊羊与灰太狼全集</title>
          <timelength>45</timelength>
          </video>
        - <video id="45">
          <title>实拍舰载直升东海救援演习</title>
          <timelength>90</timelength>
          </video>
        - <video id="78">
          <title>喀麦隆VS荷兰</title>
          <timelength>20</timelength>
          </video>
      </videos>
     */
    private static List<Video> parseXML(InputStream inStream) throws Exception {
        List<Video> videos = null;
        Video video = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inStream, "UTF-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    videos = new ArrayList<Video>();
                    break;

                case XmlPullParser.START_TAG:
                    if ("video".equals(parser.getName())) {
                        video = new Video();
                        video.setId(new Integer(parser.getAttributeValue(0)));
                    }
                    if (video != null) {
                        if ("title".equals(parser.getName())) {
                            video.setTitle(parser.nextText());
                        } else if ("timelength".equals(parser.getName())) {
                            video.setTimelength(new Integer(parser.nextText()));
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("video".equals(parser.getName())) {
                        videos.add(video);
                        video = null;
                    }
                    break;
            }
            event = parser.next();
        }
        return videos;
    }
}
