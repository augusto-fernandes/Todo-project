package com.augusto.todo.services;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.domain.dto.TodoDTO;
import com.augusto.todo.exceptions.ObjectNotFoundException;
import com.augusto.todo.repositories.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    ModelMapper mapper;

    public Todo findById(Integer id){
        Optional<Todo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado! Id: " +id +", Tipo: " + Todo.class.getName()));
    }

    public List<Todo> findAllOpen() {
        return repository.findAllOpen();
    }

    public List<Todo> findAllClose() {
        return repository.findAllClose();
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo create(TodoDTO obj){
        return repository.save(mapper.map(obj, Todo.class));
    }

    public Todo update(TodoDTO obj){
        return repository.save(mapper.map(obj, Todo.class));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
