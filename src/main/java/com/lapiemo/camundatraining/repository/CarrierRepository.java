package com.lapiemo.camundatraining.repository;

import com.lapiemo.camundatraining.model.Carrier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CarrierRepository {
    private final Map<String, Carrier> carries = new HashMap<>();
    private static CarrierRepository instance = null;

    private CarrierRepository(){
        String id1 = UUID.randomUUID().toString();
        carries.put(id1, new Carrier(id1, "Spiral Express"));

        String id2 = UUID.randomUUID().toString();
        carries.put(id2, new Carrier(id2, "Delco Services"));
    }

    public List<Carrier> getAll() {
        return carries.values().stream().toList();
    }

    public Carrier getById(String id) {
        return carries.get(id);
    }

    public static CarrierRepository getInstance() {
        if (instance == null) {
            instance = new CarrierRepository();
        }
        return instance;
    }
}
