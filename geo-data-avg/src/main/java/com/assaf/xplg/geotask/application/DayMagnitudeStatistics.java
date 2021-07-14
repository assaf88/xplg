/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class used to handle all output at once, avg mag, max mag's location and returning the math results
 * @author Asi
 */
public class DayMagnitudeStatistics {
    //stats
    private int countMagnitudes;
    private BigDecimal sumMagnitudes = BigDecimal.ZERO;
    private BigDecimal maxMagnitude = BigDecimal.ZERO;
    private String maxMagnitudePlace = "";
    //math
    private RoundingMode roundingMode = RoundingMode.HALF_UP; 
    private int scale = 2;

    /**
     * Call this method on every json node we get from a "Feature", to sum up the magnitudes and keep the maxes.
     * @param mag
     * @param place 
     */
    public void addMagnitude(BigDecimal mag, String place) {
        countMagnitudes++;
        sumMagnitudes = sumMagnitudes.add(mag);
        
        if (mag.compareTo(maxMagnitude) == 1) {
            maxMagnitude = mag;
            maxMagnitudePlace = place;
        }
    }
    
    /**
     * Call this at the end of the cycle to get final stats
     * @return 
     */
    public BigDecimal getAvgMagnitude() {
        if (countMagnitudes == 0) {
            return BigDecimal.ZERO;
        } 
        
        BigDecimal result = sumMagnitudes.divide(new BigDecimal(countMagnitudes), scale, roundingMode);
        return result;
    }
    
    public String getMaxMagnitudePlace() {
        return maxMagnitudePlace;
    }
}
