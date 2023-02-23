package com.endava.doctorsapi.tables.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping()
    public void onPost(@RequestBody(required = false)Optional<Department> department,
                       @RequestParam(name = "name", required = false) Optional<String> name) {
        if (department.isPresent() && name.isEmpty()) {
            if (department.get().getName() == null || department.get().getName().length() < 3) {
                throw new DepartmentManagementException("Please provide a name (minimum 3 chars)");
            }
            departmentService.postDepartment(department.get());
            return;
        }

        if (department.isEmpty() && name.isPresent()) {
            if (name.get().length() < 3) {
                throw new DepartmentManagementException("Please provide a name (minimum 3 chars)");
            }
            departmentService.postDepartment(name.get());
            return;
        }

        throw new DepartmentManagementException("Please provide a RequestBody name or Query Params with it");
    }

}
