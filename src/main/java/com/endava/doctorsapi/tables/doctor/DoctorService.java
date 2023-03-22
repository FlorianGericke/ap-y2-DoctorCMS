package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.department.DepartmentService;
import com.endava.doctorsapi.tables.facility.Facility;
import com.endava.doctorsapi.tables.facility.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DoctorService extends BaseService<Doctor, DoctorRepo> {

	private final FacilityService facilityService;

	private final DepartmentService departmentService;

	@Autowired
	public DoctorService(DoctorRepo doctorRepo, FacilityService facilityService, DepartmentService departmentService) {
		super(doctorRepo);
		this.facilityService = facilityService;
		this.departmentService = departmentService;
	}

	public Doctor postDoctor(Doctor doctor) {
		return this.postDoctor(doctor.getFirstName(), doctor.getLastName());
	}

	public Doctor postDoctor(String firstName, String lastName) {
		return repo.save(new Doctor(firstName, lastName));
	}

	public Doctor putDoctor(Long id, Doctor doc) {
		return this.putDoctor(id, doc.getFirstName(), doc.getLastName());
	}

//	@Transactional
//	public Doctor patchWorkSpace(long docId, long facId,long depId){
//		Doctor doc = get(docId);
//		Facility fac = facilityService.get(facId);
//		Department dep = departmentService.get(depId);
//
//
//
//		doc.assignWorkspace(fac,dep,doc);
//
//		return repo.save(doc);
//	}

	public Doctor putDoctor(Long id, String firstName, String lastName) {
		Doctor doc = repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id not found");
				});
		doc.setFirstName(firstName);
		doc.setLastName(lastName);
		return repo.save(doc);
	}
}
