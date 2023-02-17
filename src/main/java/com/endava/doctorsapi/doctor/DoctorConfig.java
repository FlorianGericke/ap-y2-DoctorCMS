package com.endava.doctorsapi.doctor;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorConfig {

	@Bean
	CommandLineRunner cmRun(DoctorService doctorService) {
		return args -> {

//			doctorService.postDoctor("Joey", "William");
//			doctorService.postDoctor("Enid", "Kerkham");
//			doctorService.postDoctor("Megan", "Huntingford");
//			doctorService.postDoctor("Romona", "Gwillim");
//			doctorService.postDoctor("Hakim", "Marishenko");
//			doctorService.postDoctor("Allistir", "Heeley");
//			doctorService.postDoctor("Elmo", "Digby");
//			doctorService.postDoctor("Quinn", "Selcraig");
//			doctorService.postDoctor("Muffin", "Marrow");
//			doctorService.postDoctor("Paule", "Foucard");
//			doctorService.postDoctor("Valeda", "Riseborough");
//			doctorService.postDoctor("Jan", "Veness");
//			doctorService.postDoctor("Gottfried", "Spellward");
//			doctorService.postDoctor("Ardella", "Morpeth");
//			doctorService.postDoctor("Romeo", "Oak");

		};
	}
}
