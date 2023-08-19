package gabrielvelezmoro;
import gabrielvelezmoro.domain.entity.Cliente;
import gabrielvelezmoro.domain.entity.Pedido;
import gabrielvelezmoro.domain.repository.Clientes;
import gabrielvelezmoro.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.lang.System.*;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ){
        return args -> {
            out.println("Salvando Clientes");
            Cliente gabriel = new Cliente("Gabriel");
            clientes.save(gabriel);

            Pedido p = new Pedido();

            p.setCliente(gabriel);
            p.setDataPedidio(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);


       pedidos.findByCliente(gabriel).forEach(out::println);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
