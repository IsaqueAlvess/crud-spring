package com.isaque.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.isaque.crudspring.dto.CourseDTO;
import com.isaque.crudspring.enums.Category;
import com.isaque.crudspring.enums.Status;
import com.isaque.crudspring.model.Course;

@Component
public class CourseMapper {
    
    public CourseDTO toDTO(Course course){
        if(course ==null){
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO){

        if(courseDTO == null){
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue( courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value){
        if(value == null){
            return null;
        }

        return switch (value) {
            case "Back-end" ->  Category.BACK_END;
            case "Front-end" -> Category.FRONT_END;
            default -> throw new IllegalArgumentException("Categoria Inválida: "+ value);
        };
    }
}