package com.ctk.services;

public interface Statistic {

    Integer getNameLengthMin();

    void setNameLengthMin(Integer nameLengthMin);

    Integer getNameLengthMax();

    void setNameLengthMax(Integer nameLengthMax);

    Integer getNameCounterEmpty();

    void setNameCounterEmpty(Integer getNameCounterEmpty);

    Integer getRegonLengthMin();

    void setRegonLengthMin(Integer regonLengthMin);

    Integer getRegonLengthMax();

    void setRegonLengthMax(Integer regonLengthMax);

    Integer getRegonCounterEmpty();

    void setRegonCounterEmpty(Integer regonCounterEmpty);

    Integer getAddressLengthMin();

    void setAddressLengthMin(Integer addressLengthMin);

    Integer getAddressLengthMax();

    void setAddressLengthMax(Integer addressLengthMax);

    Integer getAddressCounterEmpty();

    void setAddressCounterEmpty(Integer addressCounterEmpty);

    Integer getZipLengthMin();

    void setZipLengthMin(Integer zipLengthMin);

    Integer getZipLengthMax();

    void setZipLengthMax(Integer zipLengthMax);

    Integer getZipCounterEmpty();

    void setZipCounterEmpty(Integer zipCounterEmpty);

    Integer getPlaceLengthMin();

    void setPlaceLengthMin(Integer placeLengthMin);

    Integer getPlaceLengthMax();

    void setPlaceLengthMax(Integer placeLengthMax);

    Integer getPlaceCounterEmpty();

    void setPlaceCounterEmpty(Integer placeCounterEmpty);

    Integer getUriLengthMin();

    void setUriLengthMin(Integer uriLengthMin);

    Integer getUriLengthMax();

    void setUriLengthMax(Integer uriLengthMax);

    Integer getUriCounterEmpty();

    void setUriCounterEmpty(Integer uriCounterEmpty);

    Integer getTotalRecords();

    void setTotalRecords(Integer totalRecords);

    Integer getDataErrorRegonCounter();

    void setDataErrorRegonCounter(Integer dataErrorRegonCounter);

    Integer getDataErrorZipCounter();

    void setDataErrorZipCounter(Integer dataErrorZipCounter);

    Integer getDataEmptyRegonCounter();

    void setDataEmptyRegonCounter(Integer dataEmptyRegonCounter);

    Integer getDataEmptyZipCounter();

    void setDataEmptyZipCounter(Integer dataEmptyZipCounter);

}
