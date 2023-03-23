package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.request.DoctorRequest;
import com.endava.doctorsapi.dto.request.FacilityRequest;
import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.tables.department.Department;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DepartmentMapper implements DTOMapper<Department, DepartmentResponse> {
	public DepartmentResponse map(Department department) {
		return new DepartmentResponse(
				department.getId(),
				department.getName(),
				department.getFacilityDepartments()
						.stream()
						.map(res -> new FacilityRequest(
								res.getFacility().getId(),
								res.getFacility().getName()))
						.collect(Collectors.toSet()),
				department.getFacilityDepartments()
						.stream()
						.map(res -> new DoctorRequest(
								res.getDoctor().getId(),
								res.getDoctor().getFirstName(),
								res.getDoctor().getLastName()))
						.collect(Collectors.toSet())
		);
	}
}
