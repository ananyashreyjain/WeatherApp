package com.Sapient.WeatherApp.utilites;

import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;


/**
 * Class reponsible for providing logging utility
 */
public class Logger {

    private final FileWriter logWriter;
    private static Logger instance;
    private static final String FILE = "src/main/java/com/Sapient/WeatherApp/logs/log.txt";
    private Logger(){
        try{
            logWriter = new FileWriter(FILE);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Function responsible for getting 
     * the instance for the singleton class
     * @return Logger
     */
    public static Logger getLogger(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Function response for logging informational data
     */
    public void logInfo(final String message){
        synchronized(this){
            try{
                logWriter.write("[INFO] " + LocalDateTime.now().toString() + ": " + message + "\n");
                logWriter.flush();
            } catch(Exception exception){
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Function responsible for logging error pathways
     */
    public void logError(final String message){
        synchronized(this){
            try{
                logWriter.write("[ERROR] " + LocalDateTime.now().toString() + ": " + message + "\n");
                logWriter.flush();
            } catch(Exception exception){
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Function responsible for logging the warnings
     */
    public void logWarn(final String message){
        synchronized(this){
            try{
                logWriter.write("[WARN] " + LocalDateTime.now().toString() + ": " + message + "\n");
                logWriter.flush();
            } catch(Exception exception){
                throw new RuntimeException(exception);
            }
        }
    }
    
}
