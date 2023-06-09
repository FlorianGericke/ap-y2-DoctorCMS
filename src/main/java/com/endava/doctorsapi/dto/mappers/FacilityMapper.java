package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.AddressRequest;
import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.DoctorRequest;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.facility.Facility;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityMapper implements DTOMapper<Facility, FacilityResponse> {
	public FacilityResponse map(Facility facility) {
		return new FacilityResponse(
				facility.getId(),
				facility.getName(),
				Optional.ofNullable(facility.getFacilityDepartments()).orElse(Collections.emptySet())
						.stream()
						.map(res -> new DepartmentRequest(
								res.getDepartment().getName()))
						.collect(Collectors.toSet()),
				Optional.ofNullable(facility.getFacilityDepartments()).orElse(Collections.emptySet())
						.stream()
						.map(res -> new DoctorRequest(
								res.getDoctor().getFirstName(),
								res.getDoctor().getLastName()))
						.collect(Collectors.toSet()),
				Optional.ofNullable(facility.getAddresses()).orElse(Collections.emptySet())
						.stream().map(res -> new AddressRequest(
								res.getStreet(),
								res.getHouseNumber(),
								res.getLocation(),
								res.getPostCode()))
						.collect(Collectors.toSet())
		);
	}
}
