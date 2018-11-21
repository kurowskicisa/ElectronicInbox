package com.ctk.dao;

import com.ctk.model.StatisticSourceFileESP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@SessionScoped
public class StatisticSourceFileESPReadFile implements Serializable {

    @Inject
    private Settings settings = new Settings();

    @Inject
    private StatisticSourceFileESP statisticSourceFileESP;

    private static final int FIELD_NAME = 0;
    private static final int FIELD_REGON = 1;
    private static final int FIELD_ADDRESS = 2;
    private static final int FIELD_ZIP = 3;
    private static final int FIELD_PLACE = 4;
    private static final int FIELD_URI = 5;

    private static Logger APPLOGGER = LogManager.getLogger(StatisticSourceFileESPReadFile.class.getName());

    public void loadFileESP() {
        String line = null;
        BufferedReader reader = null;

        statisticSourceFileESP.setNameLengthMin(0);
        statisticSourceFileESP.setNameLengthMax(0);
        statisticSourceFileESP.setNameCounterEmpty(0);

        statisticSourceFileESP.setRegonLengthMin(0);
        statisticSourceFileESP.setRegonLengthMax(0);
        statisticSourceFileESP.setRegonCounterEmpty(0);

        statisticSourceFileESP.setAddressLengthMin(0);
        statisticSourceFileESP.setAddressLengthMax(0);
        statisticSourceFileESP.setAddressCounterEmpty(0);

        statisticSourceFileESP.setZipLengthMin(0);
        statisticSourceFileESP.setZipLengthMax(0);
        statisticSourceFileESP.setZipCounterEmpty(0);

        statisticSourceFileESP.setPlaceLengthMin(0);
        statisticSourceFileESP.setPlaceLengthMax(0);
        statisticSourceFileESP.setPlaceCounterEmpty(0);

        statisticSourceFileESP.setUriLengthMin(0);
        statisticSourceFileESP.setUriLengthMax(0);
        statisticSourceFileESP.setUriCounterEmpty(0);

        statisticSourceFileESP.setTotalRecords(0);

        statisticSourceFileESP.setDataErrorRegonCounter(0);
        statisticSourceFileESP.setDataErrorZipCounter(0);

        try {

            reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
            line = reader.readLine();

            line = reader.readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (line != null && !line.isEmpty()) {

            try {
                readingLESPLinesFromFileAll(line, reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingLESPLinesFromFileAll(
            String line,
            BufferedReader reader)
            throws IOException {

        Integer nameLength = 0;
        Integer regonLength = 0;
        Integer addressLength = 0;
        Integer zipLength = 0;
        Integer placeLength = 0;
        Integer uriLength = 0;

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                statisticSourceFileESP.setTotalRecords(
                        statisticSourceFileESP.getTotalRecords() + 1);

                nameLength = tempList.get(FIELD_NAME)
                        .replace("\"", "")
                        .trim()
                        .length();
                statisticName(nameLength);

                regonLength = tempList.get(FIELD_REGON)
                        .replace("\"", "")
                        .trim()
                        .length();
                statisticRegon(regonLength);

                if (regonLength == 0) {
                    statisticSourceFileESP.setDataEmptyRegonCounter(
                            statisticSourceFileESP.getDataEmptyRegonCounter() + 1
                    );
                    APPLOGGER.info(
                            " |*Regon*|"
                                    + tempList.get(FIELD_REGON).replace("\"", "").trim()
                                    + "|*|EMPTY|"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );
                    System.out.println(
                            " |*Regon*|"
                                    + tempList.get(FIELD_REGON).replace("\"", "").trim()
                                    + "|*|EMPTY|"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );
                }

                if ((regonLength != 9) && regonLength != 14 && regonLength != 0) {
                    statisticSourceFileESP.setDataErrorRegonCounter(
                            statisticSourceFileESP.getDataErrorRegonCounter() + 1
                    );
                    APPLOGGER.info(
                            " |*Regon*|"
                                    + tempList.get(FIELD_REGON).replace("\"", "").trim()
                                    + "|*||"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );

                    System.out.println(
                            " |*Regon*|"
                                    + tempList.get(FIELD_REGON).replace("\"", "").trim()
                                    + "|*||"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );
                }

                addressLength = tempList.get(FIELD_ADDRESS)
                        .replace("\"", "")
                        .trim()
                        .length();
                statisticAddress(addressLength);

                zipLength = tempList.get(FIELD_ZIP)
                        .replace("\"", "")
                        .replace("-", "")
                        .trim().length();
                statisticZip(zipLength);

                if (zipLength == 0) {
                    statisticSourceFileESP.setDataEmptyZipCounter(
                            statisticSourceFileESP.getDataErrorZipCounter()
                    );
                }

                if (zipLength != 5 && zipLength != 0) {
                    statisticSourceFileESP.setDataErrorZipCounter(
                            statisticSourceFileESP.getDataErrorZipCounter() + 1
                    );

                    APPLOGGER.info(
                            " |*zip*|"
                                    + tempList.get(FIELD_ZIP).replace("\"", "").trim()
                                    + "|*||"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );

                    System.out.println(
                            " |*zip*|"
                                    + tempList.get(FIELD_ZIP).replace("\"", "").trim()
                                    + "|*||"
                                    + tempList.get(FIELD_NAME).trim()
                                    + "|"
                                    + tempList.get(FIELD_REGON).trim()
                                    + "|"
                                    + tempList.get(FIELD_ADDRESS).trim()
                                    + "|"
                                    + tempList.get(FIELD_ZIP).trim()
                                    + "|"
                                    + tempList.get(FIELD_PLACE).trim()
                                    + "|"
                                    + tempList.get(FIELD_URI).trim()
                    );
                }

                placeLength = tempList.get(FIELD_PLACE)
                        .replace("\"", "")
                        .trim()
                        .length();
                statisticPlace(placeLength);

                uriLength = tempList.get(FIELD_URI)
                        .replace("\"", "")
                        .trim()
                        .length();
                statisticUri(uriLength);
            }
            line = reader.readLine();
        }

    }

    private void statisticName(Integer nameLength) {
        if (nameLength == 0) {
            statisticSourceFileESP.setNameCounterEmpty(
                    statisticSourceFileESP.getNameCounterEmpty() + 1);
        }
        if (nameLength != 0) {
            if (statisticSourceFileESP.getNameLengthMin() == 0) {
                statisticSourceFileESP.setNameLengthMin(nameLength);
            }
            if (statisticSourceFileESP.getNameLengthMin() > nameLength) {
                statisticSourceFileESP.setNameLengthMin(nameLength);
            }
            if (statisticSourceFileESP.getNameLengthMax() < nameLength) {
                statisticSourceFileESP.setNameLengthMax(nameLength);
            }
        }
    }

    private void statisticRegon(Integer regonLength) {
        if (regonLength == 0) {
            statisticSourceFileESP.setRegonCounterEmpty(
                    statisticSourceFileESP.getRegonCounterEmpty() + 1);
        }
        if (regonLength != 0) {
            if (statisticSourceFileESP.getRegonLengthMin() == 0) {
                statisticSourceFileESP.setRegonLengthMin(regonLength);
            }
            if (statisticSourceFileESP.getRegonLengthMin() > regonLength) {
                statisticSourceFileESP.setRegonLengthMin(regonLength);
            }
            if (statisticSourceFileESP.getRegonLengthMax() < regonLength) {
                statisticSourceFileESP.setRegonLengthMax(regonLength);
            }
        }
    }

    private void statisticAddress(Integer addressLength) {
        if (addressLength == 0) {
            statisticSourceFileESP.setAddressCounterEmpty(
                    statisticSourceFileESP.getAddressCounterEmpty() + 1);
        }
        if (addressLength != 0) {
            if (statisticSourceFileESP.getAddressLengthMin() == 0) {
                statisticSourceFileESP.setAddressLengthMin(addressLength);
            }
            if (statisticSourceFileESP.getAddressLengthMin() > addressLength) {
                statisticSourceFileESP.setAddressLengthMin(addressLength);
            }
            if (statisticSourceFileESP.getAddressLengthMax() < addressLength) {
                statisticSourceFileESP.setAddressLengthMax(addressLength);
            }
        }
    }

    private void statisticZip(Integer zipLength) {
        if (zipLength == 0) {
            statisticSourceFileESP.setZipCounterEmpty(
                    statisticSourceFileESP.getZipCounterEmpty() + 1);
        }
        if (zipLength != 0) {
            if (statisticSourceFileESP.getZipLengthMin() == 0) {
                statisticSourceFileESP.setZipLengthMin(zipLength);
            }
            if (statisticSourceFileESP.getZipLengthMin() > zipLength) {
                statisticSourceFileESP.setZipLengthMin(zipLength);
            }
            if (statisticSourceFileESP.getZipLengthMax() < zipLength) {
                statisticSourceFileESP.setZipLengthMax(zipLength);
            }
        }
    }

    private void statisticPlace(Integer placeLength) {
        if (placeLength == 0) {
            statisticSourceFileESP.setPlaceCounterEmpty(
                    statisticSourceFileESP.getPlaceCounterEmpty() + 1);
        }
        if (placeLength != 0) {
            if (statisticSourceFileESP.getPlaceLengthMin() == 0) {
                statisticSourceFileESP.setPlaceLengthMin(placeLength);
            }
            if (statisticSourceFileESP.getPlaceLengthMin() > placeLength) {
                statisticSourceFileESP.setPlaceLengthMin(placeLength);
            }
            if (statisticSourceFileESP.getPlaceLengthMax() < placeLength) {
                statisticSourceFileESP.setPlaceLengthMax(placeLength);
            }
        }
    }

    private void statisticUri(Integer uriLength) {
        if (uriLength == 0) {
            statisticSourceFileESP.setUriCounterEmpty(
                    statisticSourceFileESP.getUriCounterEmpty() + 1);
        }
        if (uriLength != 0) {
            if (statisticSourceFileESP.getUriLengthMin() == 0) {
                statisticSourceFileESP.setUriLengthMin(uriLength);
            }
            if (statisticSourceFileESP.getUriCounterEmpty() > uriLength) {
                statisticSourceFileESP.setUriLengthMin(uriLength);
            }
            if (statisticSourceFileESP.getUriLengthMax() < uriLength) {
                statisticSourceFileESP.setUriLengthMax(uriLength);
            }
        }
    }
}
