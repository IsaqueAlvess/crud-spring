package com.isaque.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isaque.crudspring.enums.Category;
import com.isaque.crudspring.enums.Status;
import com.isaque.crudspring.enums.converters.CategoryConverter;
import com.isaque.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
//@Table(name = "cursos")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")        //SQL que vai ser utilizado quando o método DELETE For feito
@Where(clause = "status = 'Ativo' ")    //Adição de filtro SQL na cláusula WHERE - GET com adição aut. 
public class Course {
    
    @Id //Auto incremento de Id no banco
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)      //(name = "nome")definição do nome no banco / length - tamanho do campo / nullable - se pode ser nulo ou não.
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")   //Saber se deve ser passado para classes filhas Curso removido então é necessário remover as aulas.
    private List<Lesson> lessons = new ArrayList<>();
}
