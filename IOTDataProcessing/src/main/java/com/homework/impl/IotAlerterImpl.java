package com.homework.impl;

import com.homework.constant.DateConstant;
import com.homework.constant.FileConstant;
import com.homework.constant.IotConstant;
import com.homework.intr.DataHandler;
import com.homework.intr.IotAlterter;
import com.homework.util.DateUtil;
import com.homework.util.FileUtil;

/**
 * @author: jinyibin
 * @date: 2022/5/19
 */
public class IotAlerterImpl implements IotAlterter {

    DataHandler dataHandler;

    private static String rawDataFilePath = System.getenv().get(FileConstant.RAW_DATA_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    private static String logDataFilePath = System.getenv().get(FileConstant.LOG_DATA_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    private static String middleFilePath = System.getenv().get(FileConstant.MIDDLE_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    private static String reportFilePath = System.getenv().get(FileConstant.REPORT_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    @Override
    public void deal(String input) {
        try {
            String[] records = input.split(";", -1);
            /**
             * Add Elements to the End Of the Queue
             */
            for (String rec: records) {
                if (null!=rec && !rec.isEmpty()) {
                    IotConstant.list.add(rec);
                }
            }
            if (IotConstant.list.size()>0) {
                String record = IotConstant.list.poll();
                String[] elements = record.split(",");
                if ("T1".equals(elements[0].toString().toUpperCase())) {
                    dataHandler = new TempHandler();
                    dataHandler.handleData(record.substring(record.indexOf(",")+1));
                } else if ("Q1".equals(elements[0].toString().toUpperCase())) {
                    dataHandler = new QualityHandler();
                    dataHandler.handleData(record.substring(record.indexOf(",")+1));
                } else {
                    String errorMsg = "Error Input Data Format:" + record;
                    FileUtil.appendContent2File(logDataFilePath, errorMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileUtil.appendContent2File(logDataFilePath, e.getMessage());
            while (IotConstant.list.size()>0) {
                String elem = IotConstant.list.toString();
                FileUtil.appendContent2File(rawDataFilePath, elem);
            }
        }
    }

    @Override
    public int increment() {
        return 0;
    }

    @Override
    public double updateTempAvg() {
        return 0;
    }

    @Override
    public double updateTempSum() {
        return 0;
    }

    @Override
    public void updateAlertList() {

    }

    @Override
    public void updateReadyAlertList() {

    }

    @Override
    public void saveRawData2File() {

    }

    @Override
    public void saveCheckPoint() {

    }
}
