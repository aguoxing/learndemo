package top.cloudos.upload.utils;

import java.io.File;

/**
 * @author gx
 * @date 2021/6/29 13:46
 **/
public class F {
    public static void main(String[] args) throws Exception {
        /** 测试压缩方法1  */
//        FileOutputStream fos1 = new FileOutputStream(new File("D:/DA/uploadPath/temp/H010.zip"));
//        FileCompress.toZip("D:/DA/uploadPath/temp/H010", fos1, true);

        /** 测试压缩方法2  */
//        List<File> fileList = new ArrayList<>();
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
//        FileOutputStream fos2 = new FileOutputStream(new File("c:/mytest02.zip"));
//        FileCompress.toZip(fileList, fos2);

        String path = "D:/DA/uploadPath/temp/H010";
        String format = "zip";
        ZipUtils.zipFileTree(new File(path), format);
    }
}
