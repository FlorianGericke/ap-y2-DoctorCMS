package com.endava.doctorsapi.tables.department;


import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.CmsException;
import com.endava.doctorsapi.tables.facility_department.FacilityDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService extends BaseService<Department, DepartmentRepo> {

	private final FacilityDepartmentService facilityDepartmentService;

	@Autowired
	public DepartmentService(DepartmentRepo departmentRepo, FacilityDepartmentService facilityDepartmentService) {
		super(departmentRepo);
		this.facilityDepartmentService = facilityDepartmentService;
	}

	public Department postDepartment(String name) {
		return repo.save(new Department(name));
	}

	public Department postDepartment(DepartmentRequest departmentRequest) {
		return this.postDepartment(departmentRequest.departmentName());
	}

	@Transactional
	public Department onPatchDoctorFacilityDepartment(long docId, long facId, long depId) {
		Department dep = get(docId);

		facilityDepartmentService.onPost(facId, depId, docId);

		return dep;
	}

	public Department putDepartment(Long id, String name) {
		Department department = repo.findById(id)
				.orElseThrow(() -> {
					throw new CmsException(HttpStatus.valueOf(404), "id not found");
				});
		department.setName(name);
		repo.save(department);
		return department;
	}

	public Department putDepartment(Long id, DepartmentRequest departmentRequest) {
		return this.putDepartment(id, departmentRequest.departmentName());
	}
}