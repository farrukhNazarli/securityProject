package com.example.security.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class ErrorDetails {
    String message;
    LocalDateTime time;

}
