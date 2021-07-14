/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.io;

import com.assaf.xplg.geotask.application.DayMagnitudeStatistics;
import com.assaf.xplg.geotask.config.Consts;
import com.assaf.xplg.geotask.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class print result to screen and file, could do polymorphism here (screen&file) but didn't due to lack of time
 * @author Asi
 */
public class OutputUtil {
    public OutputUtil() {
        createFile();
    }
    
    public void printMapStatistics(Map<String, DayMagnitudeStatistics> mapDailyMagnitudeStatistics) {
        for (Map.Entry<String, DayMagnitudeStatistics> entry : mapDailyMagnitudeStatistics.entrySet()) {
            String dayKey = entry.getKey();
            try {
                Date dateValue = Consts.FORMATTERUNSORTED.parse(dayKey);
                dayKey = Consts.FORMATTERSORTED.format(dateValue);
            } catch (ParseException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DayMagnitudeStatistics dayMagnitudeStatistics = entry.getValue();
            
            BigDecimal avgMag = dayMagnitudeStatistics.getAvgMagnitude();
            String maxMagPlace = dayMagnitudeStatistics.getMaxMagnitudePlace();
            
            String data = dayKey + " " + avgMag + ", location: " + maxMagPlace;
            printToScreen(data);
            writeToFile(data);
        }
    }
    
    private void createFile() {
        try {
            File myObj = new File(Consts.OUTPUT_FILE_FULLPATH);
            if (myObj.createNewFile()) {
              System.out.println("Output file created: " + myObj.getName());
            } else {
              System.out.println("Output file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
            e.toString();
        }
    }
    private void writeToFile(String data) {
        try {
            FileWriter myWriter = new FileWriter(Consts.OUTPUT_FILE_FULLPATH, true);

            myWriter.write(data + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.toString();
        }
    }
    
    private void printToScreen(String data) {
        System.out.println(data);
    }
}
