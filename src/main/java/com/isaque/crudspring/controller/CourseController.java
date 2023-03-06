package com.isaque.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaque.crudspring.model.Course;
import com.isaque.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    

    private final CourseRepository courseRepository;
        
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }
    
    @PostMapping        //Anotação que substitui @RequestMappaing(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED )
    public Course create(@RequestBody Course course){
        //System.out.println(course.getName());
        return courseRepository.save(course);
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

}
