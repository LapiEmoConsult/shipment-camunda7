package com.lapiemo.camundatraining.model;

import java.io.Serializable;

public record CarrierEstimation(Carrier carrier, String estimatedTime, Double price) implements Serializable { }
