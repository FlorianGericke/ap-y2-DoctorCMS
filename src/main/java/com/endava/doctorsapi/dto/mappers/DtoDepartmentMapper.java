package com.endava.doctorsapi.dto.mappers;

import com.endava.doctorsapi.dto.response.DepartmentResponse;
import com.endava.doctorsapi.tables.department.Department;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DtoDepartmentMapper implements Function<Department, DepartmentResponse> {
	@Override
	public DepartmentResponse apply(Department department) {
		return new DepartmentResponse(department.getId(), department.getName());
	}
}
