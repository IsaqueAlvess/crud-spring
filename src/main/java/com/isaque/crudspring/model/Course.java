package com.isaque.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 10, nullable = false)
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
