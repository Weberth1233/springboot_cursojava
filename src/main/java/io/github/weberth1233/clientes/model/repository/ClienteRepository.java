package io.github.weberth1233.clientes.model.repository;
import io.github.weberth1233.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
