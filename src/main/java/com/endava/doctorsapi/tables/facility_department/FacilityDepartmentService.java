package com.endava.doctorsapi.tables.facility_department;

import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.department.DepartmentRepo;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.doctor.DoctorRepo;
import com.endava.doctorsapi.tables.facility.Facility;
import com.endava.doctorsapi.tables.facility.FacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FacilityDepartmentService {

	private final FacilityDepartmentRepo facilityDepartmentRepo;

	private final FacilityRepo facilityRepo;
	private final DepartmentRepo departmentRepo;
	private final DoctorRepo doctorRepo;

	@Autowired
	public FacilityDepartmentService(FacilityDepartmentRepo facilityDepartmentRepo, FacilityRepo facilityRepo, DepartmentRepo departmentRepo, DoctorRepo doctorServiceRepo) {
		this.facilityDepartmentRepo = facilityDepartmentRepo;
		this.facilityRepo = facilityRepo;
		this.departmentRepo = departmentRepo;
		this.doctorRepo = doctorServiceRepo;
	}

	public FacilityDepartment onPost(Facility facility, Department department, Doctor doctor) {
		return facilityDepartmentRepo.save(new FacilityDepartment(facility, department, doctor));
	}


	public FacilityDepartment onPost(long facilityId, long departmentId, long doctorId) {
		Facility fac = facilityRepo.findById(facilityId).orElseThrow(() -> new CmsException(HttpStatus.NOT_FOUND,"Facility with id " + facilityId + " not found."));
		Department dep = departmentRepo.findById(departmentId).orElseThrow(() -> new CmsException(HttpStatus.NOT_FOUND,"Facility with id " + facilityId + " not found."));
		Doctor doc = doctorRepo.findById(doctorId).orElseThrow(() -> new CmsException(HttpStatus.NOT_FOUND,"Facility with id " + facilityId + " not found."));

		return onPost(fac,dep,doc);
	}
}
