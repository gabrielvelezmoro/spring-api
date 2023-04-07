package gabrielvelezmoro.domain.repositorio;

import ch.qos.logback.core.net.server.Client;
import gabrielvelezmoro.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO cliente (nome) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM cliente";

    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?";
    private static String DELETE = "DELETE FROM cliente WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    };

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"),
                new Object[] { "%" + nome + "%"},
                getRowMapper());
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                String nome = resultSet.getString("nome");
                Integer id = resultSet.getInt("id");
                return new Cliente(nome, id);
            }
        };
    }

}
