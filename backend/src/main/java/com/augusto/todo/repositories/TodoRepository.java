package com.augusto.todo.repositories;

import com.augusto.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = false ORDER BY obj.dataParaFinalizar")
    List<Todo> findAllOpen();
}
