package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.general.EntityStates;
import com.endava.doctorsapi.general.base.ControllerBase;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController extends ControllerBase<Department, DepartmentRepo, DepartmentService> {

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super(departmentService);
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Department> department,
	                   @RequestParam(name = "name", required = false) Optional<String> name) {
		if (department.isPresent() && name.isEmpty()) {
			if (department.get().getName() == null || department.get().getName().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			service.postDepartment(department.get());
			return;
		}

		if (department.isEmpty() && name.isPresent()) {
			if (name.get().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			service.postDepartment(name.get());
			return;
		}

		throw new CmsException("Please provide a RequestBody name or Query Params with it");
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id,
	                  @RequestBody(required = false) Optional<Department> department,
	                  @RequestParam(name = "name", required = false) Optional<String> name) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		if (department.isEmpty() && name.isPresent()) {
			if (service.get(id).getState().equals(EntityStates.DELETED.toString())) {
				throw new CmsException("Cannot change a deleted object");
			}
			if (name.get().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			service.put(id, name.get());
			return;
		}

		if (department.isPresent() && name.isEmpty()) {
			if (department.get().getName() == null || department.get().getName().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			service.put(id, department.get().getName());
			return;
		}

		throw new CmsException("Please provide a RequestBody name or Query Params with it");
	}
}
