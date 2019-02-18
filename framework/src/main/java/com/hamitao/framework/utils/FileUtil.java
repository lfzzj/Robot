package com.hamitao.framework.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.hamitao.framework.constant.BaseConstant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileUtil {
    /**
     * 需要知道当前SD卡的目录，Environment.getExternalStorageDierctory()
     */

    private String SDPATH;

    private static String USER_CHAT_RECORD = BaseConstant.PATH_BASE_SD + "record/";

    public static String getUserChatRecord() {
        return USER_CHAT_RECORD;
    }

    public String getSdPath() {
        return SDPATH;
    }

    public FileUtil() { // 目录名/sdcard
        SDPATH = BaseConstant.PATH_BASE_SD;
    }

    // 在sdcard卡上创建文件
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }


    // 判断sd卡上的文件夹是否存在
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteAppointFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteAppointFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public void deleteFile(String fileName) {
        File file = new File(SDPATH + fileName);
        file.delete();
    }

    //删除录音文件
    public static void deleteRecordFile() {
        deleteFiles(new File(USER_CHAT_RECORD));
    }

    public static void deleteFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFiles(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 将一个inputstream里面的数据写入SD卡中 第一个参数为目录名 第二个参数为文件名
     */
    public File write2SDFromInput(String path, InputStream inputstream) {
        File file = null;
        OutputStream output = null;
        try {
            file = createSDFile(path);
            output = new FileOutputStream(file);
            // 4k为单位，每4K写一次
            byte buffer[] = new byte[4 * 1024];
            int temp = 0;
            while ((temp = inputstream.read(buffer)) != -1) {
                // 获取指定信,防止写入没用的信息
                output.write(buffer, 0, temp);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    /**
     * 从sd卡获取图片资源
     *
     * @return
     */
    private List<String> getImagePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<String>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "image";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     *
     * @param fName 文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }


    /**
     * 存放当前文件夹下所有文件的路径的集合
     **/
    private static ArrayList<String> paths = new ArrayList<String>();

    /**
     * 获取指定文件夹下面的所有文件目录
     */
    public static Map<String, Bitmap> getAllFileDirectories(String filePath) {
        File baseFile = new File(filePath);

        Map<String, Bitmap> maps = new TreeMap<String, Bitmap>();
        if (baseFile != null && baseFile.exists()) {
            paths = getFileList(baseFile);
            if (!paths.isEmpty()) {
                for (int i = 0; i < paths.size(); i++) {
                    Bitmap bitmap = BitmapFactory.decodeFile(paths.get(i));
                    maps.put(paths.get(i), bitmap);
                }
            }
        }
        return maps;
    }


    //根据路径获取该路径下的所有文件以及文件夹
    public static ArrayList<String> getAllFileByPath(String filePath) {
        ArrayList<String> paths = new ArrayList<String>();
        File baseFile = new File(filePath);
        if (baseFile != null && baseFile.exists()) {
            paths = getFileList(baseFile);
            if (!paths.isEmpty()) {
                return paths;
            }
        }
        return null;
    }

    /**
     * 描述： 获取所有文件列表列表
     */
    private static ArrayList<String> getFileList(File file) {
        ArrayList<String> list = new ArrayList<String>();

        File[] files = file.listFiles();
        for (File f : files) {
            list.add(f.getAbsolutePath());
        }
        Collections.sort(list);
        return list;
    }


    private ArrayList<String> imgPath = new ArrayList<String>();   //定义一个数组用于保存文件路径

    public ArrayList<String> getImgFileByUrlPath(String url) {                   //获取指定路径下的指定格式图片文件，传入路径参数
        File files = new File(url);       //新定义一个文件，路径则为传入的url
        File[] file = files.listFiles();          //遍历该文件所有的子文件夹生成文件夹数组
        if (file != null) {
            for (File f : file) {              //for循环遍历到文件数组
                if (f.isDirectory()) {            //如果为文件夹，则递归调用此方法遍历子文件夹
                    getImgFileByUrlPath(f.getAbsolutePath());  //递归调用
                } else {
                    if (isImageFile(f.getPath())) {  //如果文件是图片文件
                        imgPath.add(f.getAbsolutePath());//获取绝对路径，返回到定义好的数组中。
                    }
                }
            }
            return imgPath;

        } else {
            return null;
        }
    }

    private static String[] imageFormat = new String[]{"jpg", "bmp"};//定义图片格式

    private static boolean isImageFile(String path) { //判断是否为图片文件的方法
        for (String format : imageFormat) {
            if (path.contains(format)) {//如果文件名字包含定义的格式后缀，则返回true
                return true;
            }

        }
        return false;
    }


    public static Bitmap getBitmapFromPath(String path) {

        byte[] buf = new byte[1024 * 1024];// 1M
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            int len = fis.read(buf, 0, buf.length);
            bitmap = BitmapFactory.decodeByteArray(buf, 0, len);
            if (bitmap == null) {
                System.out.println("len= " + len);
                System.err
                        .println("path: " + path + "  could not be decode!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return bitmap;
    }


    /**
     * 把batmap 转file
     *
     * @param bitmap
     * @param filepath
     */
    public static File saveBitmapFile(Bitmap bitmap, String filepath, String fileName) {
        //创建所属文件夹
        File dir = new File(filepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            Log.d("exception", "saveBitmapFile: " + e);
            e.printStackTrace();
        }
        return file;
    }

    public static Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }


    public static void saveBitmapFile(Context context, int res, String defaultImgPath) {
        InputStream is = context.getResources().openRawResource(res);
        Bitmap bitmap = BitmapFactory.decodeStream(is);

        File file = new File(defaultImgPath);
        try {
            file.createNewFile();

            FileOutputStream fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
            is.close();
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取文件存放根路径
    public static File getAppDir(Context context) {
        String dirPath = "";
        //SD卡是否存在
        boolean isSdCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        boolean isRootDirExists = Environment.getExternalStorageDirectory().exists();
        if (isSdCardExists && isRootDirExists) {
            dirPath = String.format("%s/%s/", Environment.getExternalStorageDirectory().getAbsolutePath(), USER_CHAT_RECORD);
        } else {
            dirPath = String.format("%s/%s/", context.getApplicationContext().getFilesDir().getAbsolutePath(), USER_CHAT_RECORD);
        }

        File appDir = new File(dirPath);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir;
    }

    //获取录音存放路径
    public static File getAppRecordDir(Context context) {
        File appDir = getAppDir(context);
        File recordDir = new File(appDir.getAbsolutePath(), USER_CHAT_RECORD);

        if (!recordDir.exists()) {
            recordDir.mkdir();
        }
        return recordDir;
    }
}
