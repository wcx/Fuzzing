package com.wcx.fuzzing;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jaredrummler.apkparser.ApkParser;
import com.jaredrummler.apkparser.model.AndroidComponent;
import com.jaredrummler.apkparser.model.AndroidManifest;
import com.jaredrummler.apkparser.model.IntentFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApkParser parser = null;
        try {
            parser = ApkParser.create(getPackageManager(), "com.alensw.PicFolder");
            AndroidManifest androidManifest = parser.getAndroidManifest();
            for (AndroidComponent component : androidManifest.getComponents()) {
                if (component.type == 1 && !component.intentFilters.isEmpty()) {
                    Log.d("test", component.name);
                    for (IntentFilter intentFilter : component.intentFilters) {
                        List<IntentFilter.IntentData> dataList = intentFilter.dataList;
                        for (IntentFilter.IntentData data : dataList) {
                            Log.d("test", data.mimeType);
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hhhh");
        Log.d("test", "onCreate: ");
    }
}
