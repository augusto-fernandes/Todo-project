package com.augusto.todo.resources;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.domain.dto.TodoDTO;
import com.augusto.todo.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoResourcesTest {

    public static final int ID = 1;
    public static final String TITULO = "Estudar";
    public static final String DESCRICAO = "Estudar Spring Boot 2 e Angular 11";
    public static final String DATA = "10/07/2022 ";
    public static final boolean OPEN = false;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static final int INDEX = 0;

    private Todo todo;
    private TodoDTO todoDTO;

    @InjectMocks
    TodoResources resources;

    @Mock
    TodoService service;

    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startTodo();
    }

    @Test
    void findById() {

    }

    @Test
    void listOpen() {
    }

    @Test
    void listClose() {
    }

    @Test
    void listAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startTodo() throws ParseException {
        todo = new Todo(ID, TITULO, DESCRICAO, SDF.parse(DATA), OPEN);
        todoDTO = new TodoDTO(ID, TITULO, DESCRICAO, SDF.parse(DATA), OPEN);
    }
}