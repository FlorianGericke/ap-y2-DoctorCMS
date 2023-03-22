package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.DoctorRequest;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.facility.Facility;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FacilityMapper implements DTOMapper<Facility, FacilityResponse> {
	public FacilityResponse map(Facility facility) {
		return new FacilityResponse(
				facility.getId(),
				facility.getName(),
				facility.getFacilityDepartments()
						.stream()
						.map(res -> new DepartmentRequest(
								res.getDepartment().getId(),
								res.getDepartment().getName()))
						.collect(Collectors.toSet()),
				facility.getFacilityDepartments()
						.stream()
						.map(res -> new DoctorRequest(
								res.getDoctor().getId(),
								res.getDoctor().getFirstName(),
								res.getDoctor().getLastName()))
						.collect(Collectors.toSet())
		);
	}
}
