package com.endava.doctorsapi.tables.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public void postDepartment(String name) {
        departmentRepo.save(new Department(name));
    }
    public void postDepartment(Department department) {
        this.postDepartment(department.getName());
    }
}