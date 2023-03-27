package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.FacilityRequest;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.tables.doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorMapper implements DTOMapper<Doctor, DoctorResponse> {
	public DoctorResponse map(Doctor doctor) {
		return new DoctorResponse(
				doctor.getId(),
				doctor.getFirstName(),
				doctor.getLastName(),
				Optional.ofNullable(doctor.getFacilityDepartments()).orElse(Collections.emptySet())
						.stream()
						.map(res -> new DepartmentRequest(
								res.getDepartment().getName()))
						.collect(Collectors.toSet()),
				Optional.ofNullable(doctor.getFacilityDepartments()).orElse(Collections.emptySet())
						.stream()
						.map(res -> new FacilityRequest(
								res.getFacility().getName()))
						.collect(Collectors.toSet())
		);
	}
}
