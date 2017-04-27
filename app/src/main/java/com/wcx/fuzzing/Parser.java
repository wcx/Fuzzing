package com.wcx.fuzzing;

import android.content.pm.PackageManager;

import com.jaredrummler.apkparser.ApkParser;
import com.jaredrummler.apkparser.model.AndroidComponent;
import com.jaredrummler.apkparser.model.AndroidManifest;
import com.jaredrummler.apkparser.model.IntentFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wcx on 17-4-26.
 */

public class Parser {
    private ApkParser parser = null;

    public Parser(PackageManager pm ,String packageName) throws PackageManager.NameNotFoundException {
        parser = ApkParser.create(pm, packageName);
    }

    public List getTarget() {

        try {
            AndroidManifest androidManifest = parser.getAndroidManifest();
            for (AndroidComponent component : androidManifest.getComponents()) {
                if (component.type == 1 && !component.intentFilters.isEmpty()) {
//                    Log.d("test", component.name);
                    for (IntentFilter intentFilter : component.intentFilters) {
                        List<IntentFilter.IntentData> dataList = intentFilter.dataList;
                        for (IntentFilter.IntentData data : dataList) {
//                            Log.d("test", data.mimeType);
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
    }
}
