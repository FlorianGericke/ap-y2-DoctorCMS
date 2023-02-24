package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.tables.general.EntityStates;
import com.endava.doctorsapi.tables.general.base.DeleteAllById;
import com.endava.doctorsapi.tables.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/{id}")
	public Department onGet(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}
		return departmentService.get(id);
	}

	@GetMapping()
	public List<Department> onGetAll() {
		return departmentService.getAll();
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Department> department,
	                   @RequestParam(name = "name", required = false) Optional<String> name) {
		if (department.isPresent() && name.isEmpty()) {
			if (department.get().getName() == null || department.get().getName().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			departmentService.postDepartment(department.get());
			return;
		}

		if (department.isEmpty() && name.isPresent()) {
			if (name.get().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			departmentService.postDepartment(name.get());
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
			if (departmentService.get(id).getState().equals(EntityStates.DELETED.toString())) {
				throw new CmsException("Cannot change a deleted object");
			}
			if (name.get().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			departmentService.put(id, name.get());
			return;
		}

		if (department.isPresent() && name.isEmpty()) {
			if (department.get().getName() == null || department.get().getName().length() < 2) {
				throw new CmsException("Please provide a name (minimum 2 chars)");
			}
			departmentService.put(id, department.get().getName());
			return;
		}

		throw new CmsException("Please provide a RequestBody name or Query Params with it");
	}

	@DeleteMapping("/{id}")
	public void onDelete(@PathVariable(value = "id") Long id) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		if (departmentService.get(id).getState().equals(EntityStates.DELETED.toString())) {
			throw new CmsException("Object is already deleted");
		}
		departmentService.delete(id);
	}

	@DeleteMapping()
	public void onDeleteAll(@RequestBody(required = false) Optional<DeleteAllById> params) {
		if (params.isPresent()) {
			Iterator<Long> ids = Arrays.stream(params.get().ids()).iterator();
			departmentService.deleteAllById(new Iterable<Long>() {
				@Override
				public Iterator<Long> iterator() {
					return ids;
				}
			});
			return;
		}
		departmentService.deleteAll();
	}
}
