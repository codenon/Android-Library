package cn.conon.android.library.sample.test;

import org.junit.Test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLTest {


    @Test
    public void postXML() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><videos><video id=90><title>中国</title></video></videos>";
        byte[] data = xml.getBytes("UTF-8");
        String path = "http://localhost:8080/JEE-Sample/struts1/provider/video/manage.do?method=getXML";
        HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        if (conn.getResponseCode() == 200) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}