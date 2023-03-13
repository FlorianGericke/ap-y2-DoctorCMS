package com.endava.doctorsapi.tables.facility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacilityConfig {
//	@Bean
	CommandLineRunner facilityConfigRunner(FacilityService facilityService) {
		return args -> {
			facilityService.postFacility("Ambulatory surgical center");
			facilityService.postFacility("Birth center");
			facilityService.postFacility("Blood bank");
			facilityService.postFacility("Clinics and medical office");
			facilityService.postFacility("Diabetes education center");
			facilityService.postFacility("Dialysis Center");
			facilityService.postFacility("Hospice home");
			facilityService.postFacility("Hospital");
			facilityService.postFacility("Imaging and radiology");
			facilityService.postFacility("Mental health and addiction treatment");
			facilityService.postFacility("Nursing home");
			facilityService.postFacility("Orthopedic and rehabilitation center");
			facilityService.postFacility("Urgent care");
			facilityService.postFacility("Telehealth");
		};
	}
}
