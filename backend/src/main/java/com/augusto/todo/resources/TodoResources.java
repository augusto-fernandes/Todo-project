package com.augusto.todo.resources;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.domain.dto.TodoDTO;
import com.augusto.todo.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/todos")
public class TodoResources {

    @Autowired
    private TodoService service;

    @Autowired
    ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id),TodoDTO.class));
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<Todo>> listOpen(){
        List<Todo> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<Todo>> listClose(){
        List<Todo> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> listAll(){
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x-> mapper.map(x,TodoDTO.class)).collect(Collectors.toList()));

    }
    @PostMapping
    public ResponseEntity<TodoDTO> create (@RequestBody TodoDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable Integer id, @RequestBody TodoDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(obj), TodoDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TodoDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
