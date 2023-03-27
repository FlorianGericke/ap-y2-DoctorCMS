package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.dto.mappers.DepartmentMapper;
import com.endava.doctorsapi.dto.request.DepartmentRequest;
import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.general.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController extends BaseController<Department, DepartmentService, DepartmentResponse, DepartmentMapper> {

	@Autowired
	public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
		super(departmentService, departmentMapper);
	}

	@PostMapping()
	public ResponseEntity<DepartmentResponse> onPost(@RequestBody() DepartmentRequest departmentRequest) {
		return new ResponseEntity<>(mapper.map(service.postDepartment(departmentRequest)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> onPut(@PathVariable(value = "id") Long id,
	                                                @RequestBody() DepartmentRequest departmentRequest) {
		return new ResponseEntity<>(mapper.map(service.putDepartment(id, departmentRequest)), HttpStatus.OK);
	}

	@PatchMapping("/{depId}/facility/{facId}/doctor/{docId}")
	public ResponseEntity<DepartmentResponse> onPatch(@PathVariable("depId") long depId,
	                                                  @PathVariable("facId") long facId,
	                                                  @PathVariable("docId") long docId) {

		return new ResponseEntity<>(mapper.map(service.onPatchDoctorFacilityDepartment(docId, facId, depId)), HttpStatus.OK);
	}
}
