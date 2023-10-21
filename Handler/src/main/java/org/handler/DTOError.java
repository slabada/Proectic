package org.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DTOError {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
