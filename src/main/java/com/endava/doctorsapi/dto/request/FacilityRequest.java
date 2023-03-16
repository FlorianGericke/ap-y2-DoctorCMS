package com.endava.doctorsapi.dto.request;

import com.endava.doctorsapi.tables.address.Address;


import java.util.Set;

public record FacilityRequest(String name,
                              Set<Address> addresses) {
}
