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

	public void postDepartment(String name) {
		repo.save(new Department(name));
	}

	public void postDepartment(Department department) {
		this.postDepartment(department.getName());
	}

	public void putDepartment(Long id, String name) {
		Department department = repo.findById(id)
				.orElseThrow(() -> {
					throw new CmsException(HttpStatus.valueOf(404), "id not found");
				});
		department.setName(name);
		repo.save(department);
	}

	public void putDepartment(Long id, Department department) {
		this.putDepartment(id, department.getName());
	}
}