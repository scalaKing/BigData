package com.homework.impl;

import com.homework.bean.QualityEntity;
import com.homework.constant.DateConstant;
import com.homework.constant.FileConstant;
import com.homework.constant.IotConstant;
import com.homework.intr.DataHandler;
import com.homework.util.DateUtil;
import com.homework.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jinyibin
 * @date: 2022/5/20
 */
public class QualityHandler implements DataHandler {

    private static String logDataFilePath = System.getenv().get(FileConstant.LOG_DATA_FILE_PATH)
            + "/" + DateUtil.getCurrentDate(DateConstant.YYYYUMMUDDUFORMAT);

    @Override
    public void handleData(String data) {
        String[] elements = data.split(",");
        String dateTimeStr = elements[0];
        QualityEntity qualityEntity = IotConstant.qualityEntity;
        Map<String, String> lastMap = qualityEntity.getLastQuality();
        Map<String, String> currentMap = qualityEntity.getCurrentQuality();
        Map<String, String> curTempMap = new HashMap<String, String>();
        String lastTimeStr = qualityEntity.getLastTime();
        String lastCurrentTimeStr = qualityEntity.getCurrentTime();
        for (int i=1; i<elements.length;i++) {
            String[] elem = elements[i].split(":");
            String key = elem[0];
            double val = Double.parseDouble(elem[1]);
            if (null!=lastMap && null!=currentMap) {
                double secondLastVal = Double.parseDouble(lastMap.getOrDefault(key, "0.0"));
                double lastVal = Double.parseDouble(currentMap.getOrDefault(key, "0.0"));
                // fit the norm of alert
                if ((lastVal-secondLastVal)/secondLastVal>0.1 &&
                        (val-secondLastVal)/secondLastVal>0.1 &&
                        currentMap.size()>0 && lastMap.size()>0) {
                    String normName = IotConstant.normMap.get(key);
                    String firstLine = "Q1," + lastTimeStr + "," + normName + ":"
                            + lastVal + ",第一次" + normName + "过高";
                    String secondLine = "Q1," + lastCurrentTimeStr + "," + normName + ":"
                            + val + ",第二次" + normName + "过高";
                    System.out.println(firstLine);
                    System.out.println(secondLine);
                    FileUtil.appendContent2File(logDataFilePath, firstLine);
                    FileUtil.appendContent2File(logDataFilePath, secondLine);
                }
            }
            curTempMap.put(key, elem[1]);
        }
        qualityEntity.setSecondLastQuality(lastMap);
        qualityEntity.setLastQuality(currentMap);
        qualityEntity.setCurrentQuality(curTempMap);
        qualityEntity.setSecondLastTime(lastTimeStr);
        qualityEntity.setLastTime(lastCurrentTimeStr);
        qualityEntity.setCurrentTime(dateTimeStr);
    }
}
