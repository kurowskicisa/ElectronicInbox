package com.ctk.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Settings implements com.ctk.services.Settings, Serializable {

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
}
