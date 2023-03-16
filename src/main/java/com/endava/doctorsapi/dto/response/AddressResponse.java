package com.endava.doctorsapi.dto.response;

import com.endava.doctorsapi.tables.facility.Facility;

import java.util.Set;

public record AddressResponse(Long id,
                              String street,
                              String houseNumber,
                              int postCode,
                              String location,
                              Set<Facility> facilities) {
}
