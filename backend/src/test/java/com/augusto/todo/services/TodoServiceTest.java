package com.augusto.todo.services;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.domain.dto.TodoDTO;
import com.augusto.todo.exceptions.ObjectNotFoundException;
import com.augusto.todo.repositories.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class TodoServiceTest {

    public static final int ID = 1;
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto Não encontrado! Id: " + ID + ", Tipo: " + Todo.class.getName();
    public static final String TITULO = "Estudar";
    public static final String DESCRICAO = "Estudar Spring Boot 2 e Angular 11";
    public static final String DATA = "10/07/2022 ";
    public static final boolean OPEN = false;
    public static final boolean CLOSE = true;
   public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static final int INDEX = 0;
    @InjectMocks
    private TodoService service;

    @Mock
    private TodoRepository repository;

    @Mock
    private ModelMapper mapper;
    private Todo todo;
    private TodoDTO todoDTO;
    private Todo todoClose;
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
       assertEquals(SDF.parse(DATA), response.getDataParaFinalizar());
       assertEquals(OPEN, response.getFinalizado());

    }

    @Test
    void  whenFindByIdReturnAnObjectNotFound(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllOpenReturnTodosOpen() throws ParseException {
        when(repository.findAllOpen()).thenReturn(List.of(todo));

        List<Todo> response = service.findAllOpen();
        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals(Todo.class, response.get(INDEX).getClass());


        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITULO, response.get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.get(INDEX).getDataParaFinalizar());
        assertEquals(OPEN, response.get(INDEX).getFinalizado());
    }

    @Test
    void whenFindAllCloseReturnATodoClose() throws ParseException {
        when(repository.findAllClose()).thenReturn(List.of(todoClose));

        List<Todo> response = service.findAllClose();
        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals(Todo.class, response.get(INDEX).getClass());


        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITULO, response.get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.get(INDEX).getDataParaFinalizar());
        assertEquals(CLOSE, response.get(INDEX).getFinalizado());
    }

    @Test
    void whenFindAllThenReturnAListOfTodo() throws ParseException {
        when(repository.findAll()).thenReturn(List.of(todo));

        List<Todo> response = service.findAll();
        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals(Todo.class, response.get(INDEX).getClass());


        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITULO, response.get(INDEX).getTitulo());
        assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        assertEquals(SDF.parse(DATA), response.get(INDEX).getDataParaFinalizar());
        assertEquals(OPEN, response.get(INDEX).getFinalizado());
    }

    @Test
    void whenCreateThenReturnSuccess() throws ParseException {
        when(repository.save(any())).thenReturn(todo);

        Todo response = service.create(todoDTO);

        assertNotNull(response);
        assertEquals(Todo.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(DESCRICAO, response.getDescricao());
        assertEquals(SDF.parse(DATA), response.getDataParaFinalizar());
        assertEquals(OPEN, response.getFinalizado());
    }
    @Test
    void whenUpdateThenReturnSuccess() throws ParseException {
        when(repository.save(any())).thenReturn(todo);

        Todo response = service.update(todoDTO);

        assertNotNull(response);
        assertEquals(Todo.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(DESCRICAO, response.getDescricao());
        assertEquals(SDF.parse(DATA), response.getDataParaFinalizar());
        assertEquals(OPEN, response.getFinalizado());
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalTodo);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository,times(1)).deleteById(anyInt());
    }


    private void startTodo() throws ParseException {
        todo = new Todo(ID, TITULO, DESCRICAO, SDF.parse(DATA), OPEN);
        todoDTO = new TodoDTO(ID, TITULO, DESCRICAO, SDF.parse(DATA), OPEN);
        todoClose = new Todo(ID, TITULO, DESCRICAO, SDF.parse(DATA), CLOSE);

         optionalTodo = Optional.of(new Todo(ID, TITULO, DESCRICAO,SDF.parse(DATA),OPEN));
    }
}