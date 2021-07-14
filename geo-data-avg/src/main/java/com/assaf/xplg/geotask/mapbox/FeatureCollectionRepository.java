/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.mapbox;

import com.assaf.xplg.geotask.application.DayMagnitudeStatistics;
import com.assaf.xplg.geotask.config.Consts;
import com.google.gson.JsonObject;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Using the library mapbox to manipulate the geojson and return a map with all the results
 * @author Asi
 */
public class FeatureCollectionRepository {
    public FeatureCollectionRepository() {
        
    }
    
    public Map<String, DayMagnitudeStatistics> getDailyMagnitudeStatisticsMap(String geoJson) {
        FeatureCollection featureCollectionFromJson = FeatureCollection.fromJson(geoJson);
        Map<String, DayMagnitudeStatistics> mapDailyMagnitudeStatistics = new HashMap<>();
        
        for (Feature feature : featureCollectionFromJson.getFeatures()) {
            JsonObject properties = feature.getProperties();
            String timeS = properties.get(Consts.GEOJSON_MEMBER_TIME).toString();
            Date date = new Date(Long.valueOf(timeS));
            String dateFormatted = Consts.FORMATTERUNSORTED.format(date);
            
            BigDecimal mag = new BigDecimal(properties.get(Consts.GEOJSON_MEMBER_MAGNITUDE).toString());
            String place = properties.get(Consts.GEOJSON_MEMBER_PLACE).toString();
                
            if (mapDailyMagnitudeStatistics.containsKey(dateFormatted)) {
                DayMagnitudeStatistics dayMagnitudeStatistics = mapDailyMagnitudeStatistics.get(dateFormatted);
                
                dayMagnitudeStatistics.addMagnitude(mag, place);
                mapDailyMagnitudeStatistics.put(dateFormatted, dayMagnitudeStatistics);
            } else {
                DayMagnitudeStatistics dayMagnitudeStatistics = new DayMagnitudeStatistics();
                
                dayMagnitudeStatistics.addMagnitude(mag, place);
                mapDailyMagnitudeStatistics.put(dateFormatted, dayMagnitudeStatistics);
            }
        }
        
        return mapDailyMagnitudeStatistics;
    }
}
