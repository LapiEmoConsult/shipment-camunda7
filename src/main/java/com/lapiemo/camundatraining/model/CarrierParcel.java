package com.lapiemo.camundatraining.model;

import java.io.Serializable;
import java.util.Objects;

public class CarrierParcel implements Serializable {
    public final String parcelId;
    public boolean responseSent;

    public CarrierParcel(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelId() {
        return parcelId;
    }

    public boolean isResponseSent() {
        return responseSent;
    }

    public void setResponseSent(boolean responseSent) {
        this.responseSent = responseSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrierParcel that = (CarrierParcel) o;
        return responseSent == that.responseSent && Objects.equals(parcelId, that.parcelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parcelId, responseSent);
    }
}
