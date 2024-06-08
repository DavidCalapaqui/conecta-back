package com.example.conecta_test.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class ApiResponseDto<T> {
    private boolean ok;
    private String message;
    private T data;

    HttpStatus httpStatus;

    public ApiResponseDto() {
    }

    public ApiResponseDto(boolean ok,  T data, String message,  HttpStatus httpStatus) {
        this.ok = ok;
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
