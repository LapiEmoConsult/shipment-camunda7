package com.lapiemo.camundatraining.service;

import com.lapiemo.camundatraining.repository.CarrierRepository;
import com.lapiemo.camundatraining.model.Carrier;
import com.lapiemo.camundatraining.model.CarrierEstimation;
import com.lapiemo.camundatraining.model.CarrierParcel;
import com.lapiemo.camundatraining.model.Parcel;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("shipment")
public class ShipmentService {
    private final RuntimeService runtimeService;
    private final CarrierRepository carrierRepository;

    public ShipmentService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
        this.carrierRepository = CarrierRepository.getInstance();
    }

    public String getDeliveryMode(Parcel parcel) {
        if (parcel.isSpecialProduct())  {
            return "special_carrier";
        }
        return "normal";
    }

    public boolean isExtraAssuranceNecessary(Parcel parcel) {
        return parcel.isFragile();
    }

    public void sendRequestToEachCarriers(Parcel parcel) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("parcel", parcel);

        carrierRepository.getAll().forEach(carrier -> {
            carrier.addParcelId(parcel.id());
            vars.put("carrier", carrier);
            runtimeService.startProcessInstanceByMessage("new_request_sent", vars);
        });
        System.out.println("Request has been send to all carriers...");
    }

    public void responseSentBy(String carrierId, String parcelId) {
        carrierRepository.getById(carrierId).getCarrierParcels().stream()
                .filter(carrierParcel -> carrierParcel.getParcelId().equals(parcelId))
                .findAny()
                .orElseThrow()
                .setResponseSent(true);
        System.out.printf("responseSentBy. ParcelId: %s - CarrierId: %s.%n", parcelId, carrierId);
    }

    public boolean allCarriersResponded(String parcelId) {
        System.out.printf("allCarriersResponded. ParcelId: %s.%n", parcelId);
        return carrierRepository.getAll().stream()
                .map(Carrier::getCarrierParcels)
                .flatMap(Collection::stream)
                .filter(carrierParcel -> carrierParcel.getParcelId().equals(parcelId))
                .allMatch(CarrierParcel::isResponseSent);
    }

    public List<Map<String, Object>> getCarrierPropositions(List<CarrierEstimation> allEstimations) {
        List<Map<String, Object>> propositions = new ArrayList<>();

        allEstimations.forEach(item -> {
            HashMap<String, Object> proposition = new HashMap<>();
            proposition.put("name", item.carrier().getName());
            proposition.put("estimatedDeliveryTime", item.estimatedTime());
            proposition.put("price", item.price());

            propositions.add(proposition);
        });

        return propositions;
    }
}
