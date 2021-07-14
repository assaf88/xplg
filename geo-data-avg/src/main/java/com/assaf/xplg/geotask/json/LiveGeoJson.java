/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assaf.xplg.geotask.json;

import com.assaf.xplg.geotask.main;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class uses the provided dependency and gets the data live
 * @author Asi
 */
public class LiveGeoJson {
    public LiveGeoJson() {
        
    }
    
    /**
     * calling by refactoring to dependency xpl-geodata.jar Class GetData 
     * @return 
     */
    public String getJson() {
        Class GetDataClass = null;
        try {
            GetDataClass = Class.forName("GetData");
        } catch (ClassNotFoundException ex) {
            System.out.println("filed to get dependency class GetData: " + ex.toString());
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Method getDataMethod = null;
        try {
            getDataMethod = GetDataClass.getMethod("getData");
        } catch (NoSuchMethodException | SecurityException ex) {
            System.out.println("filed to get dependency method GetData.getData: " + ex.toString());
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
             String result = (String)getDataMethod.invoke(GetDataClass.newInstance());
             return result;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException ex) {
            System.out.println("filed to invoke dependency method GetData.getData: " + ex.toString());
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException exc) { //could be JSONException, IOException, URISyntaxException
            Throwable cause = exc.getCause();
            System.out.println("filed to invoke dependency method GetData.getData: " + cause.toString());
        }
        
        return null;
    }
}
