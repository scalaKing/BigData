package com.homework.bean;

import java.util.Date;
import java.util.Map;

/**
 * @author: jinyibin
 * @date: 2022/5/20
 */
public class QualityEntity {

    private String secondLastTime;

    private Map<String, String> secondLastQuality;

    private String lastTime;

    private Map<String, String> lastQuality;

    private String currentTime;

    private Map<String, String> currentQuality;

    private Map<String, Integer> abnormalTimes;

    public String getSecondLastTime() {
        return secondLastTime;
    }

    public void setSecondLastTime(String secondLastTime) {
        this.secondLastTime = secondLastTime;
    }

    public Map<String, String> getSecondLastQuality() {
        return secondLastQuality;
    }

    public void setSecondLastQuality(Map<String, String> secondLastQuality) {
        this.secondLastQuality = secondLastQuality;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public Map<String, String> getLastQuality() {
        return lastQuality;
    }

    public void setLastQuality(Map<String, String> lastQuality) {
        this.lastQuality = lastQuality;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Map<String, String> getCurrentQuality() {
        return currentQuality;
    }

    public void setCurrentQuality(Map<String, String> currentQuality) {
        this.currentQuality = currentQuality;
    }

    public Map<String, Integer> getAbnormalTimes() {
        return abnormalTimes;
    }

    public void setAbnormalTimes(Map<String, Integer> abnormalTimes) {
        this.abnormalTimes = abnormalTimes;
    }
}
