package com.augusto.todo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate timestamp;
    private Integer status;
    private String message;
}
