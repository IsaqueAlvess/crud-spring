package com.isaque.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaque.crudspring.model.Course;
import com.isaque.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    

    private final CourseRepository courseRepository;
        
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
                .map(rec -> ResponseEntity.ok().body(rec))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping        //Anotação que substitui @RequestMappaing(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED )
    public Course create(@RequestBody Course course){
        //System.out.println(course.getName());
        return courseRepository.save(course);
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }


    @PutMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity <Course> update(@PathVariable Long id, @RequestBody Course course) {
        
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        return courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());        
    }

}
