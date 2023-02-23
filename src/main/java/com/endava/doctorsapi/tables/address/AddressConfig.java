package com.endava.doctorsapi.tables.address;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {
//	@Bean
	CommandLineRunner cmlRunner(AddressService addressService) {
		return args -> {
			addressService.postAddress("Bartelt","3402",64044,"Pau");
			addressService.postAddress("Porter","6",300999,"Tula");
			addressService.postAddress("8th","01041",95320000,"Nova Prata");
			addressService.postAddress("Merchant","67572",762027,"Cartago");
			addressService.postAddress("La Follette","70",29755,"Lazaro Cardenas");
			addressService.postAddress("2nd","3",3319,"Dordrecht");
			addressService.postAddress("Brickson Park","5635",362049,"Vladikavkaz");
			addressService.postAddress("Carioca","225",1219,"Baganga");
			addressService.postAddress("Veith","68058",4343,"Raojān");
			addressService.postAddress("Farragut","6584",412378,"Kamenka");
			addressService.postAddress("Surrey","9731",649787,"Tashanta");
			addressService.postAddress("Bluejay","08",5200,"Calapan");
			addressService.postAddress("Sachtjen","2207",201018,"El Copey");
			addressService.postAddress("Arrowood","43974",363503,"Chikola");
			addressService.postAddress("Crescent Oaks","8",57210,"Nakhon Si Thammarat");
			addressService.postAddress("Shoshone","91",321-2342,"Nemuro");
		};
	}
}
