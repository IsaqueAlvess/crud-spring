package com.isaque.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotNull String category) {
    
}