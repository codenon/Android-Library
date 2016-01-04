package cn.conon.android.library.sample.network.download;

import java.io.File;

import cn.conon.android.library.sample.R;
import cn.conon.android.library.sample.network.download.net.DownloadProgressListener;
import cn.conon.android.library.sample.network.download.net.FileDownloader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FileDownloadActivity extends Activity {
    private EditText pathText;
    private TextView resultView;
    private ProgressBar downloadbar;
    //用于往创建Hander对象所在的线程的消息队列发送消息
    private Handler handler = new MyHandler();

    private final class MyHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int size = msg.getData().getInt("size");
                    downloadbar.setProgress(size);
                    float amount = (float) downloadbar.getProgress() / (float) downloadbar.getMax(); //  0.1
                    int num = (int) (amount * 100);
                    resultView.setText(num + "%");
                    if (downloadbar.getProgress() == downloadbar.getMax()) {
                        Toast.makeText(FileDownloadActivity.this, R.string.success, Toast.LENGTH_LONG).show();
                    }
                    break;

                case -1:
                    Toast.makeText(FileDownloadActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_download);

        pathText = (EditText) this.findViewById(R.id.downloadpath);
        downloadbar = (ProgressBar) this.findViewById(R.id.downloadbar);
        resultView = (TextView) this.findViewById(R.id.resultView);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClientListen());
    }

    private final class ButtonClientListen implements View.OnClickListener {

        public void onClick(View v) {
            String path = pathText.getText().toString();
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = Environment.getExternalStorageDirectory();
                download(path, dir);
            } else {
                Toast.makeText(FileDownloadActivity.this, R.string.sdcarderror, Toast.LENGTH_LONG).show();
            }
        }
    }

    //download()执行的时间可能会超过一分钟,也就是说,该方法在主线程中需要执行超过一分钟,
    //那么在这一分钟内,主线程就无法处理其他工作,因为他正在忙于执行下载工作
    //假设用户此时点击了屏幕或按下按键等输入事件,主线程就无法就进行处理,因为此时他正在忙于执行下载工作
    //如果输入事件没能在5秒内得到处理,android系统就会认为程序无响应,跳出"应用无响应"错误对话框,要求用户强行关闭应用
    private void download(String path, File dir) {//100M  1M  >1分钟
        new Thread(new DownloadThread(path, dir)).start();
    }

    //在子线程里更新UI控件的值,更新后的值是不会被重绘到屏幕上的,原因是因为UI控件的重绘是由主线程(UI线程)负责的
    private final class DownloadThread implements Runnable {
        private String path;
        private File dir;

        public DownloadThread(String path, File dir) {
            this.path = path;
            this.dir = dir;
        }

        public void run() {
            FileDownloader loader = new FileDownloader(FileDownloadActivity.this, path, dir, 3);
            downloadbar.setMax(loader.getFileSize());//把进度条的最大刻度设置为文件总长度
            try {
                loader.download(new DownloadProgressListener() {
                    public void onDownloadSize(int size) {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.getData().putInt("size", size);
                        handler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                Message msg = new Message();
                msg.what = -1;
                handler.sendMessage(msg);
            }
        }
    }
}