/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.application;

import com.assaf.xplg.geotask.io.OutputUtil;
import com.assaf.xplg.geotask.json.LiveGeoJson;
import com.assaf.xplg.geotask.mapbox.FeatureCollectionRepository;
import com.assaf.xplg.geotask.util.CollectionUtil;
import java.util.Map;

/**
 *
 * @author Asi
 */
public class GeoTaskApplication {
    FeatureCollectionRepository featureCollectionRepository = new FeatureCollectionRepository();
    OutputUtil outputUtil = new OutputUtil();
        
    public void run() {
        String geoJson = new LiveGeoJson().getJson();
        
        Map<String, DayMagnitudeStatistics> mapDailyMagnitudeStatistics = featureCollectionRepository.getDailyMagnitudeStatisticsMap(geoJson);
        
        mapDailyMagnitudeStatistics = CollectionUtil.sortMap(mapDailyMagnitudeStatistics);
        
        outputUtil.printMapStatistics(mapDailyMagnitudeStatistics);
    }
}
