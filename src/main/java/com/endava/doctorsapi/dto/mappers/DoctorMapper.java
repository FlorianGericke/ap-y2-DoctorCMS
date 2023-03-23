package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.request.FacilityRequest;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.tables.doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DoctorMapper implements DTOMapper<Doctor, DoctorResponse> {
	public DoctorResponse map(Doctor doctor) {
		return new DoctorResponse(
				doctor.getId(),
				doctor.getFirstName(),
				doctor.getLastName(),
				doctor.getFacilityDepartments()
						.stream()
						.map(res -> new DepartmentRequest(
								res.getDepartment().getId(),
								res.getDepartment().getName()))
						.collect(Collectors.toSet()),
				doctor.getFacilityDepartments()
						.stream()
						.map(res -> new FacilityRequest(
								res.getFacility().getId(),
								res.getFacility().getName()))
						.collect(Collectors.toSet())
		);
	}
}
