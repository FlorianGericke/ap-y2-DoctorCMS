package com.endava.doctorsapi.tables.department;


import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService<Department, DepartmentRepo> {

	@Autowired
	public DepartmentService(DepartmentRepo departmentRepo) {
		super(departmentRepo);
	}

	public Department postDepartment(String name) {
		return repo.save(new Department(name));
	}

	public Department postDepartment(Department department) {
		return this.postDepartment(department.getName());
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

	public Department putDepartment(Long id, Department department) {
		return this.putDepartment(id, department.getName());
	}
}