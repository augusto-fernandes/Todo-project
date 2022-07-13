package com.augusto.todo.resources;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.domain.dto.TodoDTO;
import com.augusto.todo.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoResourcesTest {

    public static final int ID = 1;
    public static final String TITULO = "Estudar";
    public static final String DESCRICAO = "Estudar Spring Boot 2 e Angular 11";
    public static final String DATA = "10/07/2022 ";
    public static final boolean OPEN = false;
    public static final boolean CLOSE = true;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static final int INDEX = 0;

    private Todo todo;
    private Todo todoClose;
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
    void whenFindByIdReturnSuccess() throws ParseException {
        when(service.findById(anyInt())).thenReturn(todo);
        when(mapper.map(any(),any())).thenReturn(todoDTO);

        ResponseEntity<TodoDTO> response = resources.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TodoDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(TITULO, response.getBody().getTitulo());
        assertEquals(DESCRICAO, response.getBody().getDescricao());
        assertEquals(SDF.parse(DATA), response.getBody().getDataParaFinalizar());
        assertEquals(OPEN, response.getBody().getFinalizado());
    }

    @Test
    void whenListOpenReturnATodoOpen() throws ParseException {
        when(service.findAllOpen()).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> response =resources.listOpen();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Todo.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(TITULO, response.getBody().get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.getBody().get(INDEX).getDataParaFinalizar());
        assertEquals(OPEN, response.getBody().get(INDEX).getFinalizado());
    }

    @Test
    void whenListCloseReturnATodoClose() throws ParseException {
       when(service.findAllClose()).thenReturn(List.of(todoClose));
        ResponseEntity<List<Todo>> response =resources.listClose();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Todo.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(TITULO, response.getBody().get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.getBody().get(INDEX).getDataParaFinalizar());
        assertEquals(CLOSE, response.getBody().get(INDEX).getFinalizado());
    }

    @Test
    void whenListAllThenReturnAListOfTodoDTO() throws ParseException {
        when(service.findAll()).thenReturn(List.of(todo));
        when(mapper.map(any(),any())).thenReturn(todoDTO);

        ResponseEntity<List<TodoDTO>> response = resources.listAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(TodoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(TITULO, response.getBody().get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.getBody().get(INDEX).getDataParaFinalizar());
        assertEquals(OPEN, response.getBody().get(INDEX).getFinalizado());
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
        todoClose = new Todo(ID, TITULO, DESCRICAO, SDF.parse(DATA), CLOSE);
        todoDTO = new TodoDTO(ID, TITULO, DESCRICAO, SDF.parse(DATA), OPEN);
    }
}