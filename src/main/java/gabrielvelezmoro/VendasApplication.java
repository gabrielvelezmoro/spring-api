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

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.salvar(new Cliente("Gabriel",1 ));
            clientes.salvar(new Cliente("Outro Cliente",2));


            System.out.println("Listando Clientes");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizado Clientes");
            todosClientes.forEach(c -> {
                c.setNome( c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            System.out.println("Buscando clientes");
            clientes.buscarPorNome("Cli").forEach(System.out::println);

     //       todosClientes = clientes.obterTodos();
            //      todosClientes.forEach(System.out::println);
        };
    };

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
