package com.hamitao.kids.camera.core;

import android.app.Activity;
import android.content.Intent;

import com.hamitao.kids.camera.Constant;
import com.hamitao.kids.camera.Photo;
import com.hamitao.kids.ui.activity.CameraActivity;

import java.util.Stack;

public class CameraManager {

    private static CameraManager mInstance;
    private Stack<Activity> cameras = new Stack<>();

    public static CameraManager getInstance() {
        if (mInstance == null) {
            synchronized (CameraManager.class) {
                if (mInstance == null)
                    mInstance = new CameraManager();
            }
        }
        return mInstance;
    }

    public void openCamera(Activity context) {
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivityForResult(intent, Constant.REQUEST_CROP);
    }

    public void processPhotoItem(Activity activity, Photo photo) {
        String imageUrl = photo.getImageUri();

//        Uri uri = photo.getImageUri().startsWith("file:") ? Uri.parse(photo
//                .getImageUri()) : Uri.parse("file://" + photo.getImageUri());

//        Intent intent = new Intent(activity, CropPhotoActivity.class);
//        intent.setData(uri);
//        activity.startActivityForResult(intent, Constant.REQUEST_CROP);
    }

    public void addActivity(Activity act) {
        cameras.add(act);
    }

    public void removeActivity(Activity act) {
        cameras.remove(act);
    }

}
