package gabrielvelezmoro.domain.repositorio;

import ch.qos.logback.core.net.server.Client;
import gabrielvelezmoro.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;


@Repository
public class Clientes {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    };
    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }

        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = "select c from CLIENTE c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome","%"+ nome + "%");
        return query.getResultList();
    }

    @Transactional
    public List<Cliente> obterTodos() {
        return entityManager.createQuery("from CLIENTE", Cliente.class).getResultList();

    }


}
