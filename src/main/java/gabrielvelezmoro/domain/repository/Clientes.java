package gabrielvelezmoro.domain.repository;

import gabrielvelezmoro.domain.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {
    @Query(value = "  select c from CLIENTE c where c.nome like :nome")
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query("delete from CLIENTE c where c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);
    boolean existsByNome(String nome);

    @Query(" select c from CLIENTE c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos( @Param("id") Integer id);


}
