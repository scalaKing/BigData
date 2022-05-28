package com.homework.impl;

import com.homework.bean.TempEntity;
import com.homework.constant.DateConstant;
import com.homework.constant.FileConstant;
import com.homework.constant.IotConstant;
import com.homework.intr.DataHandler;
import com.homework.util.DateUtil;
import com.homework.util.FileUtil;

/**
 * @author: jinyibin
 * @date: 2022/5/20
 */
public class TempHandler implements DataHandler {

    private static String logDataFilePath = System.getenv().get(FileConstant.LOG_DATA_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    private static String reportFilePath = System.getenv().get(FileConstant.REPORT_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    @Override
    public void handleData(String data) {
        String[] elements = data.split(",");
        String dateTimeStr = elements[0];
        Double temperature = Double.parseDouble(elements[1]);
        TempEntity tempEntity = IotConstant.tempEntity;
        Double totalTemp = (null==tempEntity.getTotalTemp())?0.0:tempEntity.getTotalTemp();
        int totalCount = tempEntity.getRecordCount();
        double avgTemp = totalTemp /(1.0*totalCount);

        // 5 degrees higher than average temperature
        String alertMsg = "";
        if (totalCount>0) {
            if (temperature-avgTemp>5) {
                alertMsg = "T1," + dateTimeStr + ",温度过高";
                System.out.println(alertMsg);
                FileUtil.appendContent2File(logDataFilePath, alertMsg);
            } else if (avgTemp-temperature>5) {
                alertMsg = "T1," + dateTimeStr + ",温度过低";
                System.out.println(alertMsg);
                FileUtil.appendContent2File(logDataFilePath, alertMsg);
            }
        }

        tempEntity.setTotalTemp(totalTemp+temperature);
        tempEntity.setCurrentTemp(temperature);
        tempEntity.setRecordCount(++totalCount);
        tempEntity.setCurrentDateTime(dateTimeStr);
        String today = DateUtil.getCurrentDate();
        double avgTempNow = tempEntity.getTotalTemp()/(1.0*tempEntity.getRecordCount());
        String report = "温度：" + today + " " + avgTempNow;
        FileUtil.appendContent2File(reportFilePath, report, false);
    }
}
