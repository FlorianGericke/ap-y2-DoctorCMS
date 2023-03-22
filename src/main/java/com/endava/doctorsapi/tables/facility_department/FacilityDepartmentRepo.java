package com.endava.doctorsapi.tables.facility_department;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityDepartmentRepo extends JpaRepository<FacilityDepartment,Long> {
}
