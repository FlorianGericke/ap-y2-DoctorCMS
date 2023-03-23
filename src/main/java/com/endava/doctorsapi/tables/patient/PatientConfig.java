package com.endava.doctorsapi.tables.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfig {
	@Bean
	CommandLineRunner paRun(PatientService patientService) {
		return args -> {
			patientService.postPatient("72-835-4093", "Christabella", "Darnody", 6);
			patientService.postPatient("80-021-6720", "Ailbert", "Fouracres", 15);
			patientService.postPatient("87-884-2244", "Dimitry", "Horder", 36);
			patientService.postPatient("64-245-3223", "Waite", "Tatford", 24);
			patientService.postPatient("30-301-0864", "Randi", "Feore", 42);
			patientService.postPatient("44-491-8883", "Diego", "Kisar", 30);
			patientService.postPatient("49-435-1100", "Kirk", "Head", 16);
			patientService.postPatient("00-615-5657", "Carmelita", "Roeby", 77);
			patientService.postPatient("34-848-5230", "Margarita", "Cassius", 90);
			patientService.postPatient("19-873-4738", "Bobby", "Reams", 12);
		};
	}
}
