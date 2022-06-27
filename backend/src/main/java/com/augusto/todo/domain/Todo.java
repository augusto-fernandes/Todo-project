package com.augusto.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo implements Serializable {
    @Serial
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//gera o id automaticamente
    private Integer id;

    private String titulo;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataParaFinalizar;
    private Boolean finalizado = false;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
