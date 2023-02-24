package com.endava.doctorsapi.tables.department;


import com.endava.doctorsapi.tables.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void put(Long id, String name) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> {
                    throw new CmsException("id not found");
                });
        department.setName(name);
        departmentRepo.save(department);
    }

    public void put(Long id, Department department) {
        this.put(id, department.getName());
    }

    public Department get(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> {
                    throw new CmsException("id not found");
                });
    }

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public void delete(Long id) {
        departmentRepo.deleteById(id);
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        departmentRepo.deleteAllById(longs);
    }

    public void deleteAll() {
        departmentRepo.deleteAll();
    }
}