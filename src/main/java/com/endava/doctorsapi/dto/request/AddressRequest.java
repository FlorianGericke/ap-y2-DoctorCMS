package com.endava.doctorsapi.dto.request;

import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.tables.address.Address;
import com.endava.doctorsapi.tables.facility.Facility;

import java.util.Set;

public record AddressRequest(String street,
                             String houseNumber,
                             int postCode,
                             String location,
                             Set<Facility> facilities) {
}
