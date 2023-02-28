package com.isaque.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
//@Table(name = "cursos")
public class Course {
    
    @Id //Auto incremento de Id no banco
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)      //(name = "nome")definição do nome no banco / length - tamanho do campo / nullable - se pode ser nulo ou não.
    private String name;

    @Column(length = 20, nullable = false)
    private String category;


}
