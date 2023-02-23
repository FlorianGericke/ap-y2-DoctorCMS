package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.tables.general.base.RepoBase;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepo extends RepoBase<Doctor,Long> {
	List<Doctor> findAllByStateIsNot(String state);
}
