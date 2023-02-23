package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.general.RepoBase;

import java.util.List;

public interface AddressRepo extends RepoBase<Address,Long> {
	List<Address> findAllByStateIsNot(String state);
}
