package com.ctk.dao;

import com.ctk.model.Statistic;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@SessionScoped
public class StatisticSourceFileESPReadFile implements Serializable {

    @Inject
    private Settings settings = new Settings();

    @Inject
    private Statistic statistic;

    private static final int FIELD_NAME = 0;
    private static final int FIELD_REGON = 1;
    private static final int FIELD_ADDRESS = 2;
    private static final int FIELD_ZIP = 3;
    private static final int FIELD_PLACE = 4;
    private static final int FIELD_URI = 5;

    public void loadFileESP() {
        String line = null;
        BufferedReader reader = null;

        statistic.setNameLengthMin(0);
        statistic.setNameLengthMax(0);
        statistic.setNameCounterEmpty(0);

        statistic.setRegonLengthMin(0);
        statistic.setRegonLengthMax(0);
        statistic.setRegonCounterEmpty(0);

        statistic.setAddressLengthMin(0);
        statistic.setAddressLengthMax(0);
        statistic.setAddressCounterEmpty(0);

        statistic.setZipLengthMin(0);
        statistic.setZipLengthMax(0);
        statistic.setZipCounterEmpty(0);

        statistic.setPlaceLengthMin(0);
        statistic.setPlaceLengthMax(0);
        statistic.setPlaceCounterEmpty(0);

        statistic.setUriLengthMin(0);
        statistic.setUriLengthMax(0);
        statistic.setUriCounterEmpty(0);

        statistic.setTotalRecords(0);

        statistic.setDataErrorRegonCounter(0);
        statistic.setDataErrorZipCounter(0);

        statistic.setDataEmptyRegonCounter(0);
        statistic.setDataEmptyZipCounter(0);

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

        Integer nameLength;
        Integer regonLength;
        Integer addressLength;
        Integer zipLength;
        Integer placeLength;
        Integer uriLength;

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                statistic.setTotalRecords(
                        statistic.getTotalRecords() + 1);

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
                    statistic.setDataEmptyRegonCounter(
                            statistic.getDataEmptyRegonCounter() + 1
                    );
                }

                if ((regonLength != 9) && regonLength != 14 && regonLength != 0) {
                    statistic.setDataErrorRegonCounter(
                            statistic.getDataErrorRegonCounter() + 1
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
                    statistic.setDataEmptyZipCounter(
                            statistic.getDataErrorZipCounter()
                    );
                }

                if (zipLength != 5 && zipLength != 0) {
                    statistic.setDataErrorZipCounter(
                            statistic.getDataErrorZipCounter() + 1
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
            statistic.setNameCounterEmpty(
                    statistic.getNameCounterEmpty() + 1);
        }
        if (nameLength != 0) {
            if (statistic.getNameLengthMin() == 0) {
                statistic.setNameLengthMin(nameLength);
            }
            if (statistic.getNameLengthMin() > nameLength) {
                statistic.setNameLengthMin(nameLength);
            }
            if (statistic.getNameLengthMax() < nameLength) {
                statistic.setNameLengthMax(nameLength);
            }
        }
    }

    private void statisticRegon(Integer regonLength) {
        if (regonLength == 0) {
            statistic.setRegonCounterEmpty(
                    statistic.getRegonCounterEmpty() + 1);
        }
        if (regonLength != 0) {
            if (statistic.getRegonLengthMin() == 0) {
                statistic.setRegonLengthMin(regonLength);
            }
            if (statistic.getRegonLengthMin() > regonLength) {
                statistic.setRegonLengthMin(regonLength);
            }
            if (statistic.getRegonLengthMax() < regonLength) {
                statistic.setRegonLengthMax(regonLength);
            }
        }
    }

    private void statisticAddress(Integer addressLength) {
        if (addressLength == 0) {
            statistic.setAddressCounterEmpty(
                    statistic.getAddressCounterEmpty() + 1);
        }
        if (addressLength != 0) {
            if (statistic.getAddressLengthMin() == 0) {
                statistic.setAddressLengthMin(addressLength);
            }
            if (statistic.getAddressLengthMin() > addressLength) {
                statistic.setAddressLengthMin(addressLength);
            }
            if (statistic.getAddressLengthMax() < addressLength) {
                statistic.setAddressLengthMax(addressLength);
            }
        }
    }

    private void statisticZip(Integer zipLength) {
        if (zipLength == 0) {
            statistic.setZipCounterEmpty(
                    statistic.getZipCounterEmpty() + 1);
        }
        if (zipLength != 0) {
            if (statistic.getZipLengthMin() == 0) {
                statistic.setZipLengthMin(zipLength);
            }
            if (statistic.getZipLengthMin() > zipLength) {
                statistic.setZipLengthMin(zipLength);
            }
            if (statistic.getZipLengthMax() < zipLength) {
                statistic.setZipLengthMax(zipLength);
            }
        }
    }

    private void statisticPlace(Integer placeLength) {
        if (placeLength == 0) {
            statistic.setPlaceCounterEmpty(
                    statistic.getPlaceCounterEmpty() + 1);
        }
        if (placeLength != 0) {
            if (statistic.getPlaceLengthMin() == 0) {
                statistic.setPlaceLengthMin(placeLength);
            }
            if (statistic.getPlaceLengthMin() > placeLength) {
                statistic.setPlaceLengthMin(placeLength);
            }
            if (statistic.getPlaceLengthMax() < placeLength) {
                statistic.setPlaceLengthMax(placeLength);
            }
        }
    }

    private void statisticUri(Integer uriLength) {
        if (uriLength == 0) {
            statistic.setUriCounterEmpty(
                    statistic.getUriCounterEmpty() + 1);
        }
        if (uriLength != 0) {
            if (statistic.getUriLengthMin() == 0) {
                statistic.setUriLengthMin(uriLength);
            }
            if (statistic.getUriCounterEmpty() > uriLength) {
                statistic.setUriLengthMin(uriLength);
            }
            if (statistic.getUriLengthMax() < uriLength) {
                statistic.setUriLengthMax(uriLength);
            }
        }
    }
}
