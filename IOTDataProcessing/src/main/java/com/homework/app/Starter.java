package com.homework.app;

import com.homework.constant.DateConstant;
import com.homework.constant.FileConstant;
import com.homework.constant.IotConstant;
import com.homework.impl.IotAlerterImpl;
import com.homework.util.DateUtil;
import com.homework.util.FileUtil;

import java.util.Scanner;

/**
 * @author: jinyibin
 * @date: 2022/5/20
 */
public class Starter {

    private static Scanner sc;

    private static String rawDataFilePath;

    private static String logDataFilePath;

    private static String middleFilePath;

    private static String reportFilePath;

    private static IotAlerterImpl alert;

    static {
        sc = new Scanner(System.in);

        alert = new IotAlerterImpl();

        rawDataFilePath = System.getenv().get(FileConstant.RAW_DATA_FILE_PATH);
        FileUtil.createFolder(rawDataFilePath);
        rawDataFilePath+="/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

        logDataFilePath = System.getenv().get(FileConstant.LOG_DATA_FILE_PATH);
        FileUtil.createFolder(logDataFilePath);
        logDataFilePath+="/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

        middleFilePath = System.getenv().get(FileConstant.MIDDLE_FILE_PATH);
        FileUtil.createFolder(middleFilePath);
        middleFilePath+="/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

        reportFilePath = System.getenv().get(FileConstant.REPORT_FILE_PATH);
        FileUtil.createFolder(reportFilePath);
        reportFilePath+="/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);
    }

    public void start() {
        try {
            while (true) {
                while (sc.hasNext()) {
                    String input = sc.nextLine();
                    // deal with input
                    alert.deal(input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Save Raw Data
            while (IotConstant.list.size()>0) {
                String elem = IotConstant.list.toString();
                FileUtil.appendContent2File(rawDataFilePath, elem);
            }
        }
    }

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.start();
    }
}
