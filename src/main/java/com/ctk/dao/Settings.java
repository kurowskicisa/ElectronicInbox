package com.ctk.dao;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class Settings {

    private final Path pathLESPcsv = Paths.get(System.getProperty("jboss.server.data.dir"), "LESP.csv");
    private final Path pathAdmin = Paths.get(System.getProperty("jboss.server.data.dir"), "admin.csv");
    private final Path pathDatabaseInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "databaseinfo.csv");
    private final Path pathGrayScaleInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "grayscale.csv");

    public Path getPathLESPcsv() {
        return pathLESPcsv;
    }

    public Path getPathAdmin() {
        return pathAdmin;
    }

    public Path getPathDatabaseInfo() {
        return pathDatabaseInfo;
    }

    public Path getPathGrayScaleInfo() {
        return pathGrayScaleInfo;
    }

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateTimeFormatter getDateFormat() {
        return dateFormat;
    }
}
