package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.dto.response.FacilitiesDepartmentResponse;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.tables.doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DtoDoctorMapper implements Function<Doctor, DoctorResponse> {
	@Override
	public DoctorResponse apply(Doctor doctor) {
		return new DoctorResponse(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getFacilityDepartments().stream()
				.map(res -> new FacilitiesDepartmentResponse(
						new FacilityResponse(
								res.getFacility().getId(),
								res.getFacility().getName(),
								res.getFacility().getAddresses()
										.stream()
										.map(e -> new DtoAddressMapper().apply(e))
										.collect(Collectors.toSet())
						),
						new DepartmentResponse(
								res.getDepartment().getId(),
								res.getDepartment().getName()
						),
						null
				))
				.collect(Collectors.toSet()));
	}
}
