package com.example.drone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class GenericResponse<T> {
    String responseCode, responseMessage, status;

    T data;

    public GenericResponse(String responseCode,String responseMessage, String status){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;

    }

    public GenericResponse(String responseCode,String responseMessage, String status, T data){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
        this.data = data;

    }

}
