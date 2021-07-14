/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.util;

import com.assaf.xplg.geotask.application.DayMagnitudeStatistics;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  
 * @author Asi
 */
public class CollectionUtil {
    public static Map<String, DayMagnitudeStatistics> sortMap(Map<String, DayMagnitudeStatistics> unsortedMap) {
        LinkedHashMap<String, DayMagnitudeStatistics> sortedMap = new LinkedHashMap<>();
 
        unsortedMap.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEachOrdered(xx -> sortedMap.put(xx.getKey(), xx.getValue()));
        
        return sortedMap;
    }
}
