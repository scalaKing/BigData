package com.homework.util;

import com.homework.constant.IotConstant;

import java.io.*;

/**
 * @author: jinyibin
 * @date: 2022/5/19
 */
public class FileUtil {

    /**
     * Write content into a file
     * @param path
     * @param content
     * @param append
     */
    public static void appendContent2File(String path, String content, boolean append) {
        FileWriter fw;
        BufferedWriter bw = null;
        String line;
        try {
            if (!(new File(path)).exists()) {
                (new File(path)).createNewFile();
            }
            fw = new FileWriter(path, append);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null!=bw) {
                try {
                    bw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void appendContent2File(String path, String content) {
        appendContent2File(path, content, true);
    }

    public static void createFolder(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static void readRawDataFromFile(String path) {
        FileReader fr;
        BufferedReader br;
        String line;
        try {
            if (!(new File(path)).exists()) {
                throw new FileNotFoundException();
            }
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            while ((line=br.readLine())!=null) {
                IotConstant.list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
