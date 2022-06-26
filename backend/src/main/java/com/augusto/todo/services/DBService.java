package com.augusto.todo.services;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private TodoRepository todoRepository;

    public void instaciaBaseDeDados(){
    DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    Todo t1 =new Todo(null,"Estudar", "Estudar Spring Boot 2 e Angular 11", LocalDateTime.parse("10/07/2022 10:40",formatter), false);
    Todo t2 =new Todo(null,"Ler", "Ler livro de desenvolvimento pessoal", LocalDateTime.parse("10/07/2022 13:00",formatter), true);
    Todo t3 =new Todo(null,"Exercicios", "Fazer flexão", LocalDateTime.parse("10/07/2022 10:40",formatter), false);
    Todo t4 =new Todo(null,"Meditar", "Meditar 20 minutos pela manhã", LocalDateTime.parse("10/07/2022 07:40",formatter), true);

        todoRepository.saveAll(Arrays.asList(t1,t2, t3, t4));
    }

}
