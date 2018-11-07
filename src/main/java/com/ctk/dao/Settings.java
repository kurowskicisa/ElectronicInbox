package com.ctk.dao;

import javax.enterprise.context.ApplicationScoped;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;


@ApplicationScoped
public class Settings {

    private final Path pathLESPcsv = Paths.get(System.getProperty("jboss.server.data.dir"), "LESP.csv");

    public Path getPathLESPcsv() {
        return pathLESPcsv;
    }

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateTimeFormatter getDateFormat() {
        return dateFormat;
    }
}