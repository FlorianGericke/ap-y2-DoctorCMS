package com.endava.doctorsapi.dto.mappers;


import com.endava.doctorsapi.dto.response.PatientResponse;
import com.endava.doctorsapi.tables.patient.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper implements DTOMapper<Patient, PatientResponse> {
	@Override
	public PatientResponse map(Patient patient) {
		return new PatientResponse(
				patient.getId(),
				patient.getInsuranceNumber(),
				patient.getFirstName(),
				patient.getLastName(),
				patient.getAge(),
				patient.getPhone()
		);
	}
}
