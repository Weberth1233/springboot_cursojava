package io.github.weberth1233.clientes.rest;

import io.github.weberth1233.clientes.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErros(MethodArgumentNotValidException ex){
        //Obtendo resposta da requisição de erro da api
        BindingResult bindingResult = ex.getBindingResult();
        //Pegando todas as mensagem de erro retornando pela requisição e passando para uma lista de String
        List<String> message = bindingResult.getAllErrors().
                stream().
                    map( objectError -> objectError.getDefaultMessage() ).
                        collect(Collectors.toList());
        //Pegando a lista de String e passando para o contrutor da minha classe ApiError que vai formatar o retorno json para api correta
        return new ApiErrors(message);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        //Pegando retorno da mensagem adicionado no controle(Cliente não encontrado!) e o status do erro
        String messageErro = ex.getMessage();
        HttpStatus codigoStatus = ex.getStatus();
        //Passando a mensagem de erro para minha classe api errors no contrutor que transforma a mensagem em uma lista de errors para retorna para api
        ApiErrors apiErrors = new ApiErrors(messageErro);
        //Formando um reposta com a mensagem de erro formata pelo classe ApiErrors e o status do erro
        return new ResponseEntity(apiErrors, codigoStatus);
    }
}
