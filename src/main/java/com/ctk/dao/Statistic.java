package com.ctk.dao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@SessionScoped
public class Statistic extends com.ctk.model.Statistic implements Serializable {

    @Inject
    Settings settings;

    private static final int FIELD_NAME = 0;
    private static final int FIELD_REGON = 1;
    private static final int FIELD_ADDRESS = 2;
    private static final int FIELD_ZIP = 3;
    private static final int FIELD_PLACE = 4;
    private static final int FIELD_URI = 5;

    public void loadFileESP() {
        String line = null;
        BufferedReader reader = null;

        Statistic statistic = new Statistic();

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

        int nameLength;
        int regonLength;
        int addressLength;
        int zipLength;
        int placeLength;
        int uriLength;


        setTotalRecords(0);
        setDataErrorRegonCounter(0);
        setDataEmptyRegonCounter(0);

        setNameLengthMin(0);
        setNameLengthMax(0);
        setNameCounterEmpty(0);

        setRegonLengthMin(0);
        setRegonLengthMax(0);
        setRegonCounterEmpty(0);

        setAddressLengthMin(0);
        setAddressLengthMax(0);
        setAddressCounterEmpty(0);

        setZipLengthMin(0);
        setZipLengthMax(0);
        setZipCounterEmpty(0);

        setPlaceLengthMin(0);
        setPlaceLengthMax(0);
        setPlaceCounterEmpty(0);

        setUriLengthMin(0);
        setUriLengthMax(0);
        setUriCounterEmpty(0);

        setTotalRecords(0);

        setDataErrorRegonCounter(0);
        setDataErrorZipCounter(0);

        setDataEmptyRegonCounter(0);
        setDataEmptyZipCounter(0);


        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                setTotalRecords(getTotalRecords() + 1);

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
                    setDataEmptyRegonCounter(
                            getDataEmptyRegonCounter() + 1
                    );
                }

                if ((regonLength != 9) && regonLength != 14 && regonLength != 0) {
                    setDataErrorRegonCounter(
                            getDataErrorRegonCounter() + 1
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
                    setDataEmptyZipCounter(
                            getDataErrorZipCounter()
                    );
                }

                if (zipLength != 5 && zipLength != 0) {
                    setDataErrorZipCounter(
                            getDataErrorZipCounter() + 1
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
            setNameCounterEmpty(
                    getNameCounterEmpty() + 1);
        }
        if (nameLength != 0) {
            if (getNameLengthMin() == 0) {
                setNameLengthMin(nameLength);
            }
            if (getNameLengthMin() > nameLength) {
                setNameLengthMin(nameLength);
            }
            if (getNameLengthMax() < nameLength) {
                setNameLengthMax(nameLength);
            }
        }
    }

    private void statisticRegon(Integer regonLength) {
        if (regonLength == 0) {
            setRegonCounterEmpty(
                    getRegonCounterEmpty() + 1);
        }
        if (regonLength != 0) {
            if (getRegonLengthMin() == 0) {
                setRegonLengthMin(regonLength);
            }
            if (getRegonLengthMin() > regonLength) {
                setRegonLengthMin(regonLength);
            }
            if (getRegonLengthMax() < regonLength) {
                setRegonLengthMax(regonLength);
            }
        }
    }

    private void statisticAddress(Integer addressLength) {
        if (addressLength == 0) {
            setAddressCounterEmpty(
                    getAddressCounterEmpty() + 1);
        }
        if (addressLength != 0) {
            if (getAddressLengthMin() == 0) {
                setAddressLengthMin(addressLength);
            }
            if (getAddressLengthMin() > addressLength) {
                setAddressLengthMin(addressLength);
            }
            if (getAddressLengthMax() < addressLength) {
                setAddressLengthMax(addressLength);
            }
        }
    }

    private void statisticZip(Integer zipLength) {
        if (zipLength == 0) {
            setZipCounterEmpty(
                    getZipCounterEmpty() + 1);
        }
        if (zipLength != 0) {
            if (getZipLengthMin() == 0) {
                setZipLengthMin(zipLength);
            }
            if (getZipLengthMin() > zipLength) {
                setZipLengthMin(zipLength);
            }
            if (getZipLengthMax() < zipLength) {
                setZipLengthMax(zipLength);
            }
        }
    }

    private void statisticPlace(Integer placeLength) {
        if (placeLength == 0) {
            setPlaceCounterEmpty(
                    getPlaceCounterEmpty() + 1);
        }
        if (placeLength != 0) {
            if (getPlaceLengthMin() == 0) {
                setPlaceLengthMin(placeLength);
            }
            if (getPlaceLengthMin() > placeLength) {
                setPlaceLengthMin(placeLength);
            }
            if (getPlaceLengthMax() < placeLength) {
                setPlaceLengthMax(placeLength);
            }
        }
    }

    private void statisticUri(Integer uriLength) {
        if (uriLength == 0) {
            setUriCounterEmpty(
                    getUriCounterEmpty() + 1);
        }
        if (uriLength != 0) {
            if (getUriLengthMin() == 0) {
                setUriLengthMin(uriLength);
            }
            if (getUriCounterEmpty() > uriLength) {
                setUriLengthMin(uriLength);
            }
            if (getUriLengthMax() < uriLength) {
                setUriLengthMax(uriLength);
            }
        }
    }
}
