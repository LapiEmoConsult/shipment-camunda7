package com.lapiemo.camundatraining.model;

import java.io.Serializable;

public record Parcel(String id,
                     String senderName,
                     String senderEmail,
                     String parcelDescription,
                     boolean isFragile,
                     boolean isSpecialProduct) implements Serializable { }

