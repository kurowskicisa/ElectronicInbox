package com.ctk.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class Settings {

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.dao.Settings.class.getName());

    private final Path pathLESPcsv = Paths.get(System.getProperty("jboss.server.data.dir"), "LESP.csv");
    private final Path pathAdmin = Paths.get(System.getProperty("jboss.server.data.dir"), "admin.csv");
    private final Path pathDatabaseInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "databaseinfo.csv");
    private final Path pathGrayScaleInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "grayscale.csv");

    public Path getPathLESPcsv() {
        APPLOGGER.info("Settings | getPathLESPcsv | *");
        return pathLESPcsv;
    }

    public Path getPathAdmin() {
        APPLOGGER.info("Settings | getPathAdmin | *");
        return pathAdmin;
    }

    public Path getPathDatabaseInfo() {
        APPLOGGER.info("Settings | getPathDatabaseInfo | *");
        return pathDatabaseInfo;
    }

    public Path getPathGrayScaleInfo() {
        APPLOGGER.info("Settings | getPathGrayScaleInfo | *");
        return pathGrayScaleInfo;
    }

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateTimeFormatter getDateFormat() {
        return dateFormat;
    }
}
