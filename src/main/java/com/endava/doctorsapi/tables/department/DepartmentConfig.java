package com.endava.doctorsapi.tables.department;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfig {
//    @Bean
    CommandLineRunner departmentConfigRunner(DepartmentService departmentService) {
        return args -> {
            departmentService.postDepartment("name");
            departmentService.postDepartment("Baby");
            departmentService.postDepartment("Electronics");
            departmentService.postDepartment("Industrial");
            departmentService.postDepartment("Grocery");
            departmentService.postDepartment("Beauty");
            departmentService.postDepartment("Shoes");
            departmentService.postDepartment("Toys");
            departmentService.postDepartment("Kids");
            departmentService.postDepartment("Kids");
            departmentService.postDepartment("Home");
            departmentService.postDepartment("Movies");
            departmentService.postDepartment("Kids");
            departmentService.postDepartment("Clothing");
            departmentService.postDepartment("Books");
            departmentService.postDepartment("Automotive");
            departmentService.postDepartment("Shoes");
            departmentService.postDepartment("Tools");
            departmentService.postDepartment("Shoes");
            departmentService.postDepartment("Baby");
            departmentService.postDepartment("Garden");
            departmentService.postDepartment("Tools");
            departmentService.postDepartment("Outdoors");
            departmentService.postDepartment("Games");
            departmentService.postDepartment("Sports");
            departmentService.postDepartment("Kids");
            departmentService.postDepartment("Movies");
            departmentService.postDepartment("Health");
            departmentService.postDepartment("Automotive");
            departmentService.postDepartment("Shoes");
            departmentService.postDepartment("Health");
        };
    }
}
