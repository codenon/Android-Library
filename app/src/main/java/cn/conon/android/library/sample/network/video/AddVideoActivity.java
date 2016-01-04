package cn.conon.android.library.sample.network.video;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.conon.android.library.sample.R;
import cn.conon.android.library.sample.network.video.service.VideoService;
import cn.conon.android.library.utils.log.Logger;

public class AddVideoActivity extends Activity {
    private EditText titleText;
    private EditText timelengthText;
    private EditText videofileText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_video_add);

        titleText = (EditText) this.findViewById(R.id.title);
        timelengthText = (EditText) this.findViewById(R.id.timelength);
        videofileText = (EditText) this.findViewById(R.id.videofile);

    }

    public void onClickSendGet(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b;
                try {
                    b = VideoService.sendGETRequest(getParams());
                } catch (Exception e) {
                    b = false;
                    Logger.e(e, "");
                }
                final boolean result = b;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultToast(result);
                    }
                });
            }
        }).start();
    }

    public void onClickSendPost(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b;
                try {
                    b = VideoService.sendPOSTRequest(getParams());
                } catch (Exception e) {
                    b = false;
                    Logger.e(e, "");
                }
                final boolean result = b;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultToast(result);
                    }
                });
            }
        }).start();
    }

    public void onClickSendPostHttpClient(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b;
                try {
                    b = VideoService.sendPOSTRequestHttpClient(getParams());
                } catch (Exception e) {
                    b = false;
                    Logger.e(e, "");
                }
                final boolean result = b;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultToast(result);
                    }
                });
            }
        }).start();
    }

    public void onClickSaveFile(View v) {

        String filename = videofileText.getText().toString();

        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                    Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                final File uploadFile = new File(Environment.getExternalStorageDirectory(), filename);
                if (uploadFile.exists()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean b;
                            try {
                                b = VideoService.saveFile(getParams(), uploadFile);
                            } catch (Exception e) {
                                b = false;
                                Logger.e(e, "");
                            }
                            final boolean result = b;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resultToast(result);
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(AddVideoActivity.this, R.string.filenoexist, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(AddVideoActivity.this, R.string.sdcarderror, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Logger.e(e, "");
            Toast.makeText(AddVideoActivity.this, R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    private void resultToast(boolean b) {
        if (b) {
            Toast.makeText(AddVideoActivity.this, R.string.success, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddVideoActivity.this, R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    private HashMap<String, String> getParams() throws Exception {
        String title = titleText.getText().toString();
        String timelength = timelengthText.getText().toString();


        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("timelength", timelength);
        params.put("method", "save");
        return params;
    }
}