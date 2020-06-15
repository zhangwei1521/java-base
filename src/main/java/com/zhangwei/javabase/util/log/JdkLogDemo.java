package com.zhangwei.javabase.util.log;

import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class JdkLogDemo {
    private static Logger logger = Logger.getLogger("com.zhangwei.javabase.util.log.JdkLogDemo");

    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        logger.setLevel(Level.INFO);
        logger.setFilter(logRecord->
                logRecord.getLevel().intValue() >= Level.WARNING.intValue());

        logger.setUseParentHandlers(false);
        StreamHandler myHandler = new StreamHandler(System.out,new java.util.logging.Formatter() {
            @Override
            public String format(LogRecord record) {
                String format = "%1$tY %1$tb %1$td %1$tl:%1$tM:%1$tS %1$Tp [%2$s] %4$s: %5$s%6$s%n";
                Date now = new Date();
                Formatter formatter = new Formatter(Locale.US);
                return formatter.format(format,
                        now,
                        record.getSourceClassName()+" "+record.getSourceMethodName(),
                        record.getLoggerName(),
                        record.getLevel().getName(),
                        record.getMessage(),
                        null).toString();
            }
        });
        logger.addHandler(myHandler);

        logger.log(Level.SEVERE,"logging severe");
        logger.log(Level.WARNING,"logging warning");
        logger.log(Level.INFO,"logging info");
        logger.log(Level.CONFIG,"logging config");
        logger.fine("logging fine");

    }
}
