/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */

package com.zss.trade.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;

/**
 * 文件工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/22
 */
public class FileUtils {

    /**
     * 系统相册目录
     */
    public static final File DCIM_DIR = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera");

    private FileUtils() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable e) {
            }
        }
    }

    public static byte[] readBytes(InputStream in, long skip, long size) throws IOException {
        ByteArrayOutputStream out = null;
        try {
            if (skip > 0) {
                long skipSize = 0;
                while (skip > 0 && (skipSize = in.skip(skip)) > 0) {
                    skip -= skipSize;
                }
            }
            out = new ByteArrayOutputStream();
            for (int i = 0; i < size; i++) {
                out.write(in.read());
            }
            return out.toByteArray();
        } finally {
            closeQuietly(out);
        }
    }

    public static String readStr(File file) throws IOException {
        return readStr(new FileInputStream(file), "UTF-8");
    }

    public static String readStr(InputStream in) throws IOException {
        return readStr(in, "UTF-8");
    }

    public static String readStr(InputStream in, String charset) throws IOException {
        if (TextUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }

        if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream(in);
        }
        Reader reader = new InputStreamReader(in, charset);
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) >= 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    public static void writeStr(OutputStream out, String str) throws IOException {
        writeStr(out, str, "UTF-8");
    }

    public static void writeStr(OutputStream out, String str, String charset) throws IOException {
        if (TextUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }

        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(str);
        writer.flush();
    }

    public static void writeTo(String msg, File destdir) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destdir);
            fos.write((msg).getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream(in);
        }
        if (!(out instanceof BufferedOutputStream)) {
            out = new BufferedOutputStream(out);
        }
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
    }

    /**
     * 将一个文件拷贝到另外一个地方
     *
     * @param sourceFile    源文件地址
     * @param destFile      目的地址
     * @param shouldOverlay 是否覆盖
     *
     * @return
     */
    public static boolean copyFiles(String sourceFile, String destFile, boolean shouldOverlay) {
        try {
            if (shouldOverlay) {
                deleteFile(destFile);
            }
            FileInputStream fi = new FileInputStream(sourceFile);
            return writeFile(destFile, fi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除 directoryPath目录下的所有文件，包括删除删除文件夹
     * modify by liuyu 这个方法 没有删除文件夹的功能 更新了方法名
     *
     * @param
     */
    public static void clearFilesOnlyInDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    clearFilesOnlyInDir(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
    }

    /**
     * 删除一个文件
     *
     * @param filePath 要删除的文件路径名
     *
     * @return true if this file was deleted, false otherwise
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return deleteFileOrDir(file);
    }

    /**
     * 清除指定文件(文件夹下的全部，含当前)
     *
     * @param path
     */
    public static boolean deleteFileOrDir(File path) {
        if (path == null || !path.exists()) {
            return true;
        }
        if (path.isFile()) {
            return path.delete();
        }
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteFileOrDir(file);
            }
        }
        return path.delete();
    }

    /**
     * 在SD卡上创建文件
     *
     * @param fileName
     *
     * @return File
     *
     * @throws IOException
     */
    public static File createSDFile(String fileName) throws IOException {
        File file = new File(getSdPath() + fileName);
        if (file.getParentFile() != null && file.getParentFile().exists() == false) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        return file;
    }

    public static String getSdPath() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    public static boolean isSDCardExists() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static String getAppPath() {
        File dateDir = Environment.getDataDirectory();
        return dateDir.getAbsolutePath() + "/";
    }

    public static File getDcimDir() {
        if (!DCIM_DIR.exists()) {
            DCIM_DIR.mkdirs();
        }
        return DCIM_DIR;
    }

    public static String getDcimPath() {
        File dir = getDcimDir();
        return dir.getAbsolutePath() + "/";
    }

    public static boolean exists(File dir, String fileName) {
        File file = new File(dir, fileName);
        if (file.exists()) {
            return file.length() > 0;
        }
        return false;
    }

    //获得应用的存储目录
    public static String getRootPath() {
        String rootPath;
        //判断sd卡是否存在
        if (isSDCardExists()) {
            rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();//获取跟目录
        } else {
            //            rootPath = context.getFilesDir().getAbsolutePath();
            rootPath = "";
        }
        return rootPath;
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 路径名
     *
     * @return
     */
    public static boolean isFileExist(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * 创建一个文件，创建成功返回true
     *
     * @param filePath
     *
     * @return
     */
    public static boolean createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 字符串转流
     *
     * @param str
     *
     * @return
     */
    public static InputStream String2InputStream(String str) {
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * 流转字符串
     *
     * @param is
     *
     * @return
     */
    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 清理sdcard上面时间早于time的缓存图片
     *
     * @param time
     */
    public static void clear(long time, String path) {
        if (isSDCardExists()) {
            return;
        }
        clearFile(time, System.currentTimeMillis(), path);
    }

    /**
     * @param time
     * @param currentTime
     * @param path
     */
    private static void clearFile(long time, long currentTime, String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            // 删除早于time的文件
            if (file.lastModified() < time) {
                file.delete();
            }
            // 清理由于系统时间不准确可能引入的脏文件
            else if (file.lastModified() > currentTime + 1000 * 60 * 60 * 24) {
                file.delete();
            } else {
                //todo
            }
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file2 : files) {
                    clearFile(time, currentTime, file2.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 将数据写入一个文件
     *
     * @param destFilePath 要创建的文件的路径
     * @param data         待写入的文件数据
     * @param startPos     起始偏移量
     * @param length       要写入的数据长度
     *
     * @return 成功写入文件返回true, 失败返回false
     */
    public static boolean writeFile(String destFilePath, byte[] data, int startPos, int length) {
        try {
            if (!createFile(destFilePath)) {
                return false;
            }
            FileOutputStream fos = new FileOutputStream(destFilePath);
            fos.write(data, startPos, length);
            fos.flush();
            if (null != fos) {
                fos.close();
                fos = null;
            }
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从一个输入流里写文件
     *
     * @param destFilePath 要创建的文件的路径
     * @param in           要读取的输入流
     *
     * @return 写入成功返回true, 写入失败返回false
     */
    public static boolean writeFile(String destFilePath, InputStream in) {
        try {
            if (!createFile(destFilePath)) {
                return false;
            }
            FileOutputStream fos = new FileOutputStream(destFilePath);
            int readCount = 0;
            int len = 1024;
            byte[] buffer = new byte[len];
            while ((readCount = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
            fos.flush();
            if (null != fos) {
                fos.close();
                fos = null;
            }
            if (null != in) {
                in.close();
                in = null;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从一个数量流里读取数据,返回以byte数组形式的数据。
     * </br></br>
     * 需要注意的是，如果这个方法用在从本地文件读取数据时，一般不会遇到问题，但如果是用于网络操作，就经常会遇到一些麻烦(available()方法的问题)。所以如果是网络流不应该使用这个方法。
     *
     * @param in 要读取的输入流
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream in) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] b = new byte[in.available()];
            int length = 0;
            while ((length = in.read(b)) != -1) {
                os.write(b, 0, length);
            }

            b = os.toByteArray();

            in.close();
            in = null;

            os.close();
            os = null;

            return b;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean appendFile(String filename, byte[] data, int datapos, int datalength) {
        try {
            createFile(filename);
            RandomAccessFile rf = new RandomAccessFile(filename, "rw");
            rf.seek(rf.length());
            rf.write(data, datapos, datalength);
            if (rf != null) {
                rf.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 读取文件，返回以byte数组形式的数据
     *
     * @param filePath 要读取的文件路径名
     *
     * @return
     */
    public static byte[] readFile(String filePath) {
        try {
            if (isFileExist(filePath)) {
                FileInputStream fi = new FileInputStream(filePath);
                return readInputStream(fi);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取网络流
     *
     * @param in
     *
     * @return
     */
    public static byte[] readNetWorkInputStream(InputStream in) {
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();

            int readCount = 0;
            int len = 1024;
            byte[] buffer = new byte[len];
            while ((readCount = in.read(buffer)) != -1) {
                os.write(buffer, 0, readCount);
            }

            in.close();
            in = null;

            return os.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os = null;
            }
        }
        return null;
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     *
     * @return long 单位为M
     *
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFolderSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size / 1048576;
    }

    //批量更改文件后缀
    public static void reNameSuffix(File dir, String oldSuffix, String newSuffix) {
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                reNameSuffix(listFiles[i], oldSuffix, newSuffix);
            }
        } else {
            dir.renameTo(new File(dir.getPath().replace(oldSuffix, newSuffix)));
        }
    }

    public static void writeImage(Bitmap bitmap, String destPath, int quality) {
        try {
            deleteFile(destPath);
            if (createFile(destPath)) {
                FileOutputStream out = new FileOutputStream(destPath);
                if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                    out.flush();
                    out.close();
                    out = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件扩展名,比如 .jpg
     * add by wubb
     *
     * @param filePath 例如：abc/bcd/123.jpg
     *
     * @return
     */
    public static String getFileExt(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        int pos = filePath.indexOf(".");
        if (pos < 0) {
            return null;
        }
        String fileExt = filePath.substring(pos, filePath.length());
        return fileExt;
    }

    /**
     * 获取文件名不带扩展名
     * add by wubb
     *
     * @param filePath 例如：abc/bcd/123.jpg
     *
     * @return
     */
    public static String getFileTitle(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        filePath = filePath.replace("\\", "/");
        int pos = filePath.lastIndexOf("/");
        int pos2 = filePath.lastIndexOf(".");

        if (pos < 0 && pos2 < 0) {
            return filePath;
        }

        String fileTitle = null;
        if (pos < 0 && pos2 > 0) {
            fileTitle = filePath.substring(0, pos2);
            return fileTitle;
        }
        if (pos == 0 && pos2 < 0) {
            fileTitle = filePath.substring(1);
            return fileTitle;
        }
        if (pos >= 0 && pos2 > pos) {
            fileTitle = filePath.substring(pos + 1, pos2);
            return fileTitle;
        }
        return fileTitle;
    }

    /**
     * 获取文件名    例如：abc.jpg
     * add by wubb
     *
     * @param filePath 文件路径
     *
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        filePath = filePath.replace('\\', '/');
        int start = filePath.lastIndexOf("/");
        if (start < 0) {
            return filePath;
        }
        String fileName = filePath.substring(start + 1);
        return fileName;
    }

    /**
     * 复制文件
     **/
    public static boolean copyFile(File src, File tar) throws Exception {
        if (src.isFile()) {
            InputStream is = new FileInputStream(src);
            OutputStream op = new FileOutputStream(tar);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(op);
            try {
                byte[] bt = new byte[1024 * 8];
                int len = bis.read(bt);
                while (len != -1) {
                    bos.write(bt, 0, len);
                    len = bis.read(bt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw (new Exception(e));
            } finally {
                bis.close();
                bos.close();
            }
        }
        if (src.isDirectory()) {
            File[] f = src.listFiles();
            tar.mkdir();
            for (int i = 0; i < f.length; i++) {
                copyFile(f[i].getAbsoluteFile(), new File(tar.getAbsoluteFile() + File.separator
                        + f[i].getName()));
            }
        }
        return true;
    }

    /**
     * 移动文件
     **/
    public static boolean moveFile(File src, File tar) throws Exception {
        if (copyFile(src, tar)) {
            deleteFile(src);
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     **/
    public static void deleteFile(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; ++i) {
                    deleteFile(files[i]);
                }
            }
        }
        f.delete();
    }

    public static boolean isFileSize(String path) {
        File file = new File(path);
        if (file != null && file.length() > 0) {
            return true;
        }

        return false;
    }

    /**
     * @param context    上下文
     * @param mountPoint 挂载点
     *
     * @Description:判断SDCard是否挂载上,返回值为true证明挂载上了，否则未挂载
     * @Date 2013-11-12
     */
    public static boolean checkSDCardMount14(Context context, String mountPoint) {
        if (mountPoint == null) {
            return false;
        }
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getVolumeState = storageManager.getClass().getMethod("getVolumeState", String.class);
            String state = (String) getVolumeState.invoke(storageManager, mountPoint);
            return Environment.MEDIA_MOUNTED.equals(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //获取外置sd
    public static String getExterPath() {
        //得到路径
        String sdcard_path = "";
        BufferedReader br = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            String line;
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = br.readLine()) != null) {
                if (line.contains("secure")) {
                    continue;
                }
                if (line.contains("asec")) {
                    continue;
                }

                if (line.contains("fat")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        sdcard_path = sdcard_path.concat("*" + columns[1]);
                    }
                } else if (line.contains("fuse")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        sdcard_path = sdcard_path.concat(columns[1]);
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sdcard_path;
    }

    /**
     * 获取缓存路径  /sdcard/Android/data/包名/Cache目录
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public static boolean isSdCardCanWrite() {

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED) && Environment
                .getExternalStorageDirectory().canWrite())//sd存在并可写
        {
            return true;

        }
        return false;
    }

    public static String getFileMD5(InputStream in) {
        byte buffer[] = new byte[1024];
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            int len;
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            //      BigInteger bigInt = new BigInteger(1, digest.digest());
            //      return bigInt.toString(16);
            return asHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param buf
     *
     * @return
     */
    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)//小于十前面补零
            {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

}
