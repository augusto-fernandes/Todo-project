package com.augusto.todo.services;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private TodoRepository todoRepository;

    public void instaciaBaseDeDados() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Todo t1 =new Todo(null,"Estudar", "Estudar Spring Boot 2 e Angular 11", sdf.parse("10/07/2022 "), false);
    Todo t2 =new Todo(null,"Ler", "Ler livro de desenvolvimento pessoal", sdf.parse("10/07/2022 "), true);
    Todo t3 =new Todo(null,"Exercicios", "Fazer flexão", sdf.parse("10/07/2022 "), false);
    Todo t4 =new Todo(null,"Meditar", "Meditar 20 minutos pela manhã", sdf.parse("10/07/2022 "), true);

        todoRepository.saveAll(Arrays.asList(t1,t2, t3, t4));
    }

}
