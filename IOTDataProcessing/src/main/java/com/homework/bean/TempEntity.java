package com.homework.bean;

import java.util.Date;

/**
 * @author: jinyibin
 * @date: 2022/5/20
 */
public class TempEntity {

    private Double totalTemp;

    private int recordCount;

    private Double currentTemp;

    private String currentDateTime;

    public Double getTotalTemp() {
        return totalTemp;
    }

    public void setTotalTemp(Double totalTemp) {
        this.totalTemp = totalTemp;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    @Override
    public String toString() {
        return currentDateTime + "," + currentTemp + "," + totalTemp + "," + recordCount;
    }
}
