package com.isaque.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isaque.crudspring.enums.Category;
import com.isaque.crudspring.enums.Status;
import com.isaque.crudspring.model.Course;
import com.isaque.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			
			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);
			c.setStatus(Status.ATIVO);
			courseRepository.save(c);
		};
	}

}
