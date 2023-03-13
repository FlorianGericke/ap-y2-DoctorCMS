package com.endava.doctorsapi.tables.department;


import com.endava.doctorsapi.general.base.ServiceBase;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends ServiceBase<Department, Long, DepartmentRepo> {

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

	public void put(Long id, String name) {
		Department department = repo.findById(id)
				.orElseThrow(() -> {
					throw new CmsException("id not found");
				});
		department.setName(name);
		repo.save(department);
	}

	public void put(Long id, Department department) {
		this.put(id, department.getName());
	}
}