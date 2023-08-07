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


            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(out::println);

           out.println("Atualizado Clientes");
           todosClientes.forEach(c -> {
               c.setNome( c.getNome() + " atualizado");
               clientes.save(c);
           });

          out.println("Buscando clientes");
          clientes.findByNomeLike("Cli").forEach(out::println);

            out.println("Deletando clientes");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.findAll();
            if (todosClientes.isEmpty()){
                out.println("Nenhum cliente encontrado");
            }
            todosClientes.forEach(out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
