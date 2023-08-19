package gabrielvelezmoro.domain.repository;

import gabrielvelezmoro.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
