package com.isaque.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaque.crudspring.dto.CourseDTO;
import com.isaque.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {     

    private final CourseService courseService;
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id){
        return courseService.findById(id);
    }
    
    @PostMapping                    //Anotação que substitui @RequestMappaing(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED )
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course){
        return courseService.create(course);
    }


    @PutMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CourseDTO update(@NotNull @PathVariable Long id, 
            @RequestBody @Valid CourseDTO course) {
        return courseService.update(id, course);
    }

    //Hard Delete - remoção física do Banco de Dados
    //Soft Delete - Apenas torna o dado inválido para amostras (mantem guardado)
    @DeleteMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT )
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);
    }

    

}
