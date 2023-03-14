package gabrielvelezmoro.service;

import gabrielvelezmoro.model.Cliente;
import gabrielvelezmoro.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository repository;

    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.persistir(cliente);
        System.out.println("Salvando cliente: " + cliente);
    }

    public void validarCliente(Cliente client){
        //aplica validações
    }
}
