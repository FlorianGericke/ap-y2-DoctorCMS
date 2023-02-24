package com.endava.doctorsapi.tables.address;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {
	List<Address> findAllByStateIsNot(String state);
}
