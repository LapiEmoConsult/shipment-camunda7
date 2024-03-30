package com.lapiemo.camundatraining.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrier implements Serializable {
    public final String id;
    public String name;
    public final List<CarrierParcel> carrierParcels = new ArrayList<>();

    public Carrier(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarrierParcel> getCarrierParcels() {
        return carrierParcels;
    }

    public void addParcelId(String parcelId) {
        carrierParcels.add(new CarrierParcel(parcelId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return Objects.equals(id, carrier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
