package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.AddressResponse;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.address.Address;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.facility.Facility;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DtoAddressMapper implements Function<Address, AddressResponse> {
	@Override
	public AddressResponse apply(Address address) {
		return new AddressResponse(address.getStreet(),
				address.getHouseNumber(),
				address.getLocation(),
				address.getPostCode(),
				address.getFacilities()
						.stream()
						.map(facility -> new FacilityResponse(facility.getName(),null))
						.collect(Collectors.toSet()));
	}
}
