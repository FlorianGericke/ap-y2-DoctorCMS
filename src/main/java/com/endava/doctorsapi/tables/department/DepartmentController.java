package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/department")
public class DepartmentController extends BaseController<Department, DepartmentService> {

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super(departmentService);
	}

	@PostMapping()
	public void onPost(@RequestBody() Optional<Department> department) {
		validate(department);
		service.postDepartment(department.get());
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Department> department) {
		if (id == null) {
			throw new ControllerException(this, "Invalid param id is null");
		}

		validate(department);
		service.putDepartment(id, department.get());
	}

	private void validate(Optional<Department> department) {
		if (department.isEmpty()) {
			throw new ControllerException(this, "Please provide a RequestBody withe attribute name");
		}

		if ((department.get().getName() == null || department.get().getName().length() < 3)) {
			throw new ControllerException(this, "Please provide a name in RequestBody (min 2 chars)");
		}
	}
}
