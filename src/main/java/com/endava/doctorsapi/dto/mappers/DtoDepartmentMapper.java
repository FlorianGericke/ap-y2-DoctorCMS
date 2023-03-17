package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.tables.doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoDepartmentMapper implements Function<Doctor, DoctorResponse> {
	@Override
	public DoctorResponse apply(Doctor doctor) {
		return new DoctorResponse(doctor.getFirstName(), doctor.getLastName());
	}
}
