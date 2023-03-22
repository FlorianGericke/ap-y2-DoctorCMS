package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.dto.response.FacilitiesDepartmentResponse;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.facility_department.FacilityDepartment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoFacilityDepartmentMapper implements Function<FacilityDepartment, FacilitiesDepartmentResponse> {
	@Override
	public FacilitiesDepartmentResponse apply(FacilityDepartment facilityDepartment) {
		return new FacilitiesDepartmentResponse(new DtoFacilityMapper().apply(facilityDepartment.getFacility()),
				new DtoDepartmentMapper().apply(facilityDepartment.getDepartment()),
				new DtoDoctorMapper().apply(facilityDepartment.getDoctor()));
	}
}
