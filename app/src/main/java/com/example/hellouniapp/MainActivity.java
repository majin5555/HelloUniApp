package com.example.hellouniapp;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;

import org.json.JSONObject;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.feature.sdk.DCUniMPSDK;

public class MainActivity extends AppCompatActivity {

    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpUniapp(View view) {
        // 启动小程序并传入参数 "Hello uni microprogram"
        try {
            //注释
            String name = "__UNI__376C834";
            JSONObject arguments = new JSONObject();
            arguments.put("MSG", "Hello uni microprogram");
            DCUniMPSDK.getInstance().startApp(this, name, arguments);
            //DCUniMPSDK.getInstance().startApp(this,"__UNI__376C834","pages/tabbar/index/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpUniappFromsd(View view) {
        // 启动新线程下载软件
        String sdpath = "";
        // 判断SD卡是否存在，并且是否具有读写权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/wgt/";
        } else {
            sdpath = MainActivity.this.getCacheDir() + "/";
        }
        File file = new File(sdpath);
        // 判断文件目录是否存在 不存在 则创建
        if (! file.exists()) {
            file.mkdirs();
        } else {
            Log.d("majin", "sdpath ---" + sdpath);
            String wgtPath = sdpath + "__UNI__B92044B.wgt";
            Log.d("majin", "wgtPath ---" + wgtPath);
            fileName = FileUtils.getFileNameNoExtension(wgtPath);
            Log.d("majin", "fileName ---" + fileName);
            DCUniMPSDK.getInstance().releaseWgtToRunPathFromePath(fileName, wgtPath, callBack);
        }
    }

    //释放wgt 回调
    ICallBack callBack = new ICallBack() {
        @Override
        public Object onCallBack(int code, Object pArgs) {
            Log.d("majin", "释放wgt  code  " + code);
            if (code == 1) {//释放wgt完成
                try {
                    DCUniMPSDK.getInstance().startApp(MainActivity.this, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {//释放wgt失败
                Toast.makeText(MainActivity.this, "资源释放失败", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    };

    public void jumpText(View view) {
        try {
            DCUniMPSDK.getInstance().startApp(this, "__UNI__EA09775");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}