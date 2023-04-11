package io.github.weberth1233.clientes.rest;

import io.github.weberth1233.clientes.model.entity.Cliente;
import io.github.weberth1233.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

//RestController - Esteriotipo de controle e possui o ResponseBody - resposta do metodo vai o corpo da resposta
@RestController
//RequestMapping -  mapear a url da base deste classe controle
@RequestMapping("/api/clientes")
public class ClienteController {
    //Injetando o repositorio no construtor da classe para quando ela ser criado o repositorio seja criado junto
    private ClienteRepository repository;
    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    //PostMapping -  criar algo no servidor
    //Adicionar cliente na base de dados
    @PostMapping
    //ResponseStatus - resposta retornada do servidor. Caso não coloque nada vai ser retornado o status OK(200)
    @ResponseStatus(HttpStatus.CREATED)
    //@RequestBody -  O cliente vai ser um json que vai vim no scopo da requisição post enviado para este metodo
    //Valid - validação vai está sendo feita tanto pelo spring quando no momento da requisição para que possa se retornado para Api
    public Cliente salvar( @RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    //Obter cliente pelo id
    @GetMapping("{id}")
    public Cliente obterClienteId( @PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    //Deletar cliente
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        //Map vai pegar o cliente com id e vai deletar ele do repositorio e não retornar nada void.type
        repository.findById(id).map( cliente -> {
            repository.delete(cliente);
                    return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    //Atualizar cliente
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        repository.findById(id).map(cliente -> {
            //Map vai pegar o cliente com id da base de dados e atualizar com os dados da variavel clienteAtualizado
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            return repository.save(cliente);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}