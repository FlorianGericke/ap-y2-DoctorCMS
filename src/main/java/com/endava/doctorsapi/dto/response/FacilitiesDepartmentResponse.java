package com.endava.doctorsapi.dto.response;


public record FacilitiesDepartmentResponse(
		FacilityResponse facility,
		DepartmentResponse department,
		DoctorResponse doctor
){

}
