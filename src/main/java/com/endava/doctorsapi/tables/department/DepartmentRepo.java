package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.tables.general.RepoBase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends RepoBase<Department, Long> {
    List<Department> findAllByStateIsNot(String state);
}
