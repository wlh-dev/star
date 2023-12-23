package com.star.wlh.designpattern.responsibility;

public class Client {
    public static void main(String[] args) {
        LoggerHandler loggers = getLoggers();
        loggers.logMessage(LoggerHandler.ERROR,"This is an error information.");
    }
    protected static  LoggerHandler getLoggers(){
        InfoLog infoLog = new InfoLog(LoggerHandler.INFO);
        ErrorLog errorLog = new ErrorLog(LoggerHandler.ERROR);
        DebugLog debugLog = new DebugLog(LoggerHandler.DEBUG);
        errorLog.setNextLoggerHandler(debugLog);
        debugLog.setNextLoggerHandler(infoLog);
        return errorLog;
    }
}
