package com.Sapient.WeatherApp.utilites;

import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;

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

    public static Logger getLogger(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

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
