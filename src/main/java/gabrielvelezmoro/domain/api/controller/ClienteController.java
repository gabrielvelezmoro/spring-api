package gabrielvelezmoro.domain.api.controller;
import gabrielvelezmoro.domain.entity.Cliente;
import gabrielvelezmoro.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping( value = {"/{id}"})
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping( value = {"/"})
    @ResponseBody
    public ResponseEntity save( @RequestBody Cliente cliente ){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping( value = {"/{id}"})
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping( value = {"/{id}"})
    @ResponseBody
    public ResponseEntity update( @RequestBody Cliente cliente, @PathVariable("id") Integer id){

        return  clientes.findById(id).map(clienteExistente -> {
           cliente.setId(clienteExistente.getId());
           clientes.save(cliente);
           return ResponseEntity.noContent().build();
       }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity find(Cliente filtro) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example =  Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }

}
