package com.homework.constant;

import com.homework.bean.QualityEntity;
import com.homework.bean.TempEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: jinyibin
 * @date: 2022/5/19
 */
public class IotConstant {

    public static Map<String, String> normMap = new HashMap<String, String>();

    public static LinkedList<String> list;

    public static TempEntity tempEntity;

    public static QualityEntity qualityEntity;

    static {
        normMap.put("AB", "酸度");
        normMap.put("AE", "黏稠度");
        normMap.put("CE", "含水量");
        list = new LinkedList<String>();
        tempEntity = new TempEntity();
        qualityEntity = new QualityEntity();
    }

}
