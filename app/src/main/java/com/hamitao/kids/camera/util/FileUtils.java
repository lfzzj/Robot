package com.hamitao.kids.camera.util;


import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {

    private static String BASE_PATH;

    private static FileUtils mInstance;

    public static FileUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (FileUtils.class) {
                if (mInstance == null) {
                    mInstance = new FileUtils(context);
                }
            }
        }
        return mInstance;
    }

    public String getPhotoSavedPath() {
        return BASE_PATH + "liteCamera";
    }

    public String getPhotoTempPath() {
        return BASE_PATH + "liteCamera";
    }

    public String getSystemPhotoPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera";
    }

    private FileUtils(Context context) {
        String sdcardState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
            BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/liteCamera/";
        } else {
            BASE_PATH = getCacheDirPath(context);
        }
    }

    public boolean mkDir(File file) {
        while (!file.getParentFile().exists()) {
            mkDir(file.getParentFile());
        }
        return file.mkdir();
    }

    public File getCacheDir(Context context) {
        return context.getCacheDir();
    }


    public String getFilesDirPath(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public String getCacheDirPath(Context context) {
        return getCacheDir(context).getAbsolutePath();
    }

}
