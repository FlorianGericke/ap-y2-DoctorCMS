package com.endava.doctorsapi.tabels.doctor;


import com.endava.doctorsapi.tabels.general.RepoBase;

import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DoctorRepo extends RepoBase<Doctor, Long> {

	List<Doctor> findAllByStateIsNot(String state);
}
