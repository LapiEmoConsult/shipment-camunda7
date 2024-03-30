package com.lapiemo.camundatraining.service;

import com.lapiemo.camundatraining.model.Carrier;
import com.lapiemo.camundatraining.model.CarrierEstimation;
import com.lapiemo.camundatraining.model.Parcel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
public class CarrierSendResponseDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Parcel parcel = (Parcel) delegateExecution.getVariable("parcel");
        Carrier carrier = (Carrier) delegateExecution.getVariable("carrier");
        String estimatedTime = (String) delegateExecution.getVariable("estimatedDeliveryTime");
        Double price = Double.valueOf((String) delegateExecution.getVariable("price"));

        VariableMap vars = Variables.putValue("carrierEstimation", new CarrierEstimation(carrier, estimatedTime, price));

        delegateExecution
                .getProcessEngine()
                .getRuntimeService()
                .correlateMessage("carrier_responded", parcel.id(), vars);
    }
}
