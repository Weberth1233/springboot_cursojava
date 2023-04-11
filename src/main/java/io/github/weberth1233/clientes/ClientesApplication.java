package io.github.weberth1233.clientes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientesApplication {
    //@Autowired -  injeção de dependencias
    //CommandoLineRunner - vai executar quando o aplicação subir no servidor
    /*@Bean
    public CommandLineRunner run( @Autowired ClienteRepository repository ){
        return args ->{
            Cliente cliente = Cliente.builder().cpf("00000000000").nome("Weberth").build();
            repository.save(cliente);
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }
}
