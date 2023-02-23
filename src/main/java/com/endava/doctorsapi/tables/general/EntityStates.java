package com.endava.doctorsapi.tables.general;

public enum EntityStates {
	CREATED("created"),
	UPDATED("updated"),
	DELETED("deleted"),;

	final String value;
	EntityStates(String string) {
		this.value = string;
	}

	@Override
	public String toString() {
		return value;
	}
}
