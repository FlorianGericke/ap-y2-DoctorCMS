package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.dto.mappers.DepartmentMapper;
import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/department")
public class DepartmentController extends BaseController<Department, DepartmentService, DepartmentResponse, DepartmentMapper> {

	@Autowired
	public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
		super(departmentService, departmentMapper);
	}

	@PostMapping()
	public ResponseEntity<DepartmentResponse> onPost(@RequestBody() Optional<Department> department) {
		validate(department);
		return new ResponseEntity<>(mapper.map(service.postDepartment(department.get())), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Department> department) {
		if (id == null) {
			throw new ControllerException(this, "Invalid param id is null");
		}

		validate(department);
		return new ResponseEntity<>(mapper.map(service.putDepartment(id, department.get())), HttpStatus.OK);
	}

	@PatchMapping("/{depId}/facility/{facId}/doctor/{docId}")
	public ResponseEntity<DepartmentResponse> onPatch(@PathVariable("depId") Optional<Long> depId,
	                                              @PathVariable("facId") Optional<Long> facId,
	                                              @PathVariable("docId") Optional<Long> docId) {

		return new ResponseEntity<>(mapper.map(service.onPatch(docId.get(), facId.get(), depId.get())), HttpStatus.OK);
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
