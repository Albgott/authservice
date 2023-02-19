package com.albgott.authservice.business.domain.model;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter(autoApply = true)
public class BusinessNameConverter implements AttributeConverter<BusinessName, String> {

    @Override
    public String convertToDatabaseColumn(BusinessName businessName) {
        return businessName != null ? businessName.value() : null;
    }

    @Override
    public BusinessName convertToEntityAttribute(String name) {
        return name != null ? new BusinessName(name) : null;
    }
}
