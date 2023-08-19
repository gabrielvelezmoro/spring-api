package gabrielvelezmoro.domain.repository;

import gabrielvelezmoro.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
