package com.augusto.todo.services;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.repositories.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoServiceTest {

    public static final int ID = 1;
    public static final String TITULO = "Estudar";
    public static final String DESCRICAO = "Estudar Spring Boot 2 e Angular 11";
    public static final String DATA = "10/07/2022 ";
    public static final boolean NAO_FINALIZADO = false;
   public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    @InjectMocks
    private TodoService service;

    @Mock
    private TodoRepository repository;

    private Todo todo;
    private Optional<Todo> optionalTodo;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startTodo();
    }

    @Test
    void whenFindByIdReturnATodoInstance() throws ParseException {
        when(repository.findById(anyInt())).thenReturn(optionalTodo);

        Todo response = service.findById(ID);

       assertNotNull(response);
       assertEquals(Todo.class, response.getClass());

       assertEquals(ID, response.getId());
       assertEquals(TITULO, response.getTitulo());
       assertEquals(DESCRICAO, response.getDescricao());
       assertEquals(ID, response.getId());
       assertEquals(SDF.parse(DATA), response.getDataParaFinalizar());
       assertEquals(NAO_FINALIZADO, response.getFinalizado());

    }

    @Test
    void findAllOpen() {
    }

    @Test
    void findAllClose() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    private void startTodo() throws ParseException {
         todo = new Todo(ID, TITULO, DESCRICAO, SDF.parse(DATA), NAO_FINALIZADO);
         optionalTodo = Optional.of(new Todo(ID, TITULO, DESCRICAO,SDF.parse(DATA),NAO_FINALIZADO));

    }
}