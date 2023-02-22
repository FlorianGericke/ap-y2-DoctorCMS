package com.endava.doctorsapi.tabels.address;

import com.endava.doctorsapi.tabels.general.RepoBase;

import java.util.List;

public interface AddressRepo extends RepoBase<Address,Long> {
	List<Address> findAllByStateIsNot(String state);
}
