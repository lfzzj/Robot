package com.hamitao.kids.ui.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.camera.Photo;
import com.hamitao.kids.camera.brdcompat.BitmapRegionDecoderCompat;
import com.hamitao.kids.camera.core.CameraHelper;
import com.hamitao.kids.camera.util.FileUtils;
import com.hamitao.kids.camera.util.IOUtil;
import com.hamitao.kids.camera.util.ImageUtils;
import com.hamitao.kids.camera.util.StringUtils;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.utils.GlideUtil;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.WindowUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;

public class CameraActivity extends BaseMsgActivity {

    private CameraHelper mCameraHelper;
    private Camera.Parameters parameters = null;
    private Camera cameraInst = null;
    private int PHOTO_SIZE = 2000;
    private int mCurrentCameraId = 0;


    @BindView(R.id.take_picture)
    Button takePicture;
    @BindView(R.id.focus_index)
    View focusIndex;
    @BindView(R.id.camera_surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.iv_photo_preview)
    ImageView ivPhotoPreview;

    private IDevicePresenter devicePresenter;
    private int screenWidth = 240;
    private int screenHeight = 320;

    @Override
    public void setActivityView() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera);
    }

    @Override
    public void initData() {

        //隐藏弹窗
        WindowUtils.hidePopupWindow();
        takePicture.setBackgroundResource(R.drawable.icon_album_taking_pic);

        devicePresenter = new DevicePresenterImpl(mContext);
        mCameraHelper = new CameraHelper(this);
        initView();
        initEvent();
        ivPhotoPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setKeepScreenOn(true);
        surfaceView.setFocusable(true);
        surfaceView.getHolder().addCallback(new SurfaceCallback());
    }

    private void initEvent() {
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cameraInst.takePicture(null, null, new CustomPictureCallback());
                } catch (Throwable t) {
                    t.printStackTrace();
                    Logger.d(TAG, "拍照失败");
                    try {
                        cameraInst.startPreview();
                    } catch (Throwable e) {

                    }
                }
            }
        });

    }

    private final class CustomPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            new SavePicTask(data).execute();
            camera.startPreview();
        }
    }

    private class SavePicTask extends AsyncTask<Void, Void, String> {

        private byte[] data;

        protected void onPreExecute() {
            showProgressDialog("处理中...");
        }

        SavePicTask(byte[] data) {
            this.data = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                return saveToSDCard(data);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (StringUtils.isNotEmpty(result)) {
                dismissProgressDialog();
                Photo photo = new Photo(result, System.currentTimeMillis());
                Intent i = new Intent();
                i.setData(Uri.parse(photo.getImageUri()));
                setResult(RESULT_OK, i);
                String photoName = TimeUtil.getCurrentTime() + ".jpg";
//                photoName = PhotoBitmapUtils.amendRotatePhoto(photoName, mContext);

                //设置阿里云保存路径
                String ossPath = BaseConstant.CAMERA_PHOTO_ALBUM_ADDRESS + BaseConstant.CAMERA_DEVICE + deviceManager.getDeviceId() + "/" + photoName;
                //将照片上传到阿里云
                MyApp.getInstance().getOssManager().photographUpload(ossPath, photo.getImageUri());

                GlideUtil.loadImage(mContext, photo.getImageUri(), ivPhotoPreview);

                //在此将oss路径上传到服务器
                devicePresenter.addPhoto(MyApp.getInstance().getSpManager().getTerminalId(), photoName, ossPath);


            } else {
                Logger.d(TAG, "拍照失败，请稍后重试");
            }
        }
    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {

        public void surfaceDestroyed(SurfaceHolder holder) {
            try {
                if (cameraInst != null) {
                    Log.e(TAG, "Camera release");
                    cameraInst.stopPreview();
                    cameraInst.release();
                    cameraInst = null;
                }
            } catch (Exception e) {
                Log.e(TAG, "Camera was closed");
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (null == cameraInst) {
                try {
                    cameraInst = Camera.open(0);
                    cameraInst.setPreviewDisplay(holder);
                    initCamera();
                    cameraInst.setDisplayOrientation(90);
                    cameraInst.startPreview();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            autoFocus();
        }
    }

    private void autoFocus() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (cameraInst == null) {
                    return;
                }
                cameraInst.autoFocus(new Camera.AutoFocusCallback() {

                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            initCamera();
                        }
                    }
                });
            }
        };
    }


    private void initCamera() {
        parameters = cameraInst.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();//获取所有支持的camera尺寸
        Camera.Size optionSize = ScreenUtil.getOptimalPreviewSize(sizeList, screenWidth, screenHeight);//获取一个最为适配的屏幕尺寸
//        Camera.Size optionSize = ScreenUtil.getPreviewSize(sizeList, ScreenUtil.getScreenValue(mContext), ScreenUtil.getScreenWhScale(mContext));
        parameters.setPreviewSize(optionSize.width, optionSize.height);//把只存设置给parameters
        parameters.setPictureSize(optionSize.width, optionSize.height);
        cameraInst.setParameters(parameters);//把parameters设置给camera上

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
        } else {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        setDisplay(parameters, cameraInst);

        cameraInst.startPreview();
        cameraInst.cancelAutoFocus();
    }

    private static final String TAG = "Camera";


    private void setDisplay(Camera.Parameters parameters, Camera camera) {
        if (Build.VERSION.SDK_INT >= 8) {
            setDisplayOrientation(camera, 0);
        } else {
            parameters.setRotation(90);
        }
    }

    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation",
                    new Class[]{int.class});
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, new Object[]{i});
            }
        } catch (Exception e) {
            Log.e("Came_e", "图像出错");
        }
    }

    public String saveToSDCard(byte[] data) throws IOException {
        Bitmap croppedImage;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        PHOTO_SIZE = options.outHeight > options.outWidth ? options.outWidth : options.outHeight;
        int height = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
        options.inJustDecodeBounds = false;
        Rect r;
        if (mCurrentCameraId == 1) {
            r = new Rect(height - PHOTO_SIZE, 0, height, PHOTO_SIZE);
        } else {
            r = new Rect(0, 0, options.outWidth, options.outHeight);
        }
        try {
            croppedImage = decodeRegionCrop(data, r);
        } catch (Exception e) {
            return null;
        }
        String imagePath = ImageUtils.saveToFile(this, FileUtils.getInstance(this).getSystemPhotoPath(), true,
                croppedImage);
        croppedImage.recycle();

        return imagePath;
    }

    private Bitmap decodeRegionCrop(byte[] data, Rect rect) {
        InputStream is = null;
        System.gc();
        Bitmap croppedImage = null;
        try {
            is = new ByteArrayInputStream(data);
            BitmapRegionDecoderCompat decoder = BitmapRegionDecoderCompat.newInstance(is, false);

            try {
                croppedImage = decoder.decodeRegion(rect, new BitmapFactory.Options());
            } catch (IllegalArgumentException e) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(is);
        }

        Matrix m = new Matrix();
        m.setRotate(90, PHOTO_SIZE / 2, PHOTO_SIZE / 2);
        if (mCurrentCameraId == 1) {
            m.postScale(1, -1);
        }
        Bitmap rotatedImage = Bitmap.createBitmap(croppedImage, 0, 0, screenWidth, screenHeight, m, true);
        if (rotatedImage != croppedImage && croppedImage != null)
            croppedImage.recycle();

        return rotatedImage;
    }
}
