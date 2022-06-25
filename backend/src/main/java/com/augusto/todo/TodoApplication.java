package com.augusto.todo;

import com.augusto.todo.domain.Todo;
import com.augusto.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Todo t1 =new Todo(null,"Estudar", "Estudar Spring Boot 2 e Angular 11", LocalDateTime.parse("10/07/2022 10:40",formatter), false);

		todoRepository.saveAll(List.of(t1));
	}
}
