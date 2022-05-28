package com.homework.intr;

/**
 * @author: jinyibin
 * @date: 2022/5/19
 */
public interface IotAlterter {

    public abstract void deal(String input);

    public abstract int increment();

    public abstract double updateTempAvg();

    public abstract double updateTempSum();

    public abstract void updateAlertList();

    public abstract void updateReadyAlertList();

    public abstract void saveRawData2File();

    public abstract void saveCheckPoint();
}
