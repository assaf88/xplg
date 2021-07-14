/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Asi
 */
public class Consts {
    //time
    public static  DateFormat FORMATTERUNSORTED = new SimpleDateFormat("yyyy/MM/dd");
    public static DateFormat FORMATTERSORTED = new SimpleDateFormat("dd/MM/yyyy");
    
    //io
    public static String OUTPUT_FILE_FULLPATH = "output.txt";
    
    //json
    public static String GEOJSON_MEMBER_TIME = "time";
    public static String GEOJSON_MEMBER_MAGNITUDE = "mag";
    public static String GEOJSON_MEMBER_PLACE = "place";
    
}
