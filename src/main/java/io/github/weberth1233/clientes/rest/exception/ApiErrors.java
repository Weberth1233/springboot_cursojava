package io.github.weberth1233.clientes.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String message){
        //Transformando mensagem recebida em um array
        this.errors = Arrays.asList(message);
    }


}
