package gabrielvelezmoro;

import gabrielvelezmoro.domain.entity.Cliente;
import gabrielvelezmoro.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.*;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            out.println("Salvando Clientes");
            clientes.save(new Cliente( "Gabriel"));
            clientes.save(new Cliente("Outro Cliente"));


            boolean exist  = clientes.existsByNome("Gabriel");
            out.println("Exists : " + exist);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
