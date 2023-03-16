package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.tables.address.Address;

import java.util.Set;

public record FacilityResponse(Long id,
                               String name,
                               Set<Address> addresses) {
}
