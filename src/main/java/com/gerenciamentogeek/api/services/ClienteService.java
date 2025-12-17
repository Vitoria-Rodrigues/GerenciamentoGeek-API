package com.gerenciamentogeek.api.services;

import com.gerenciamentogeek.api.classes.Cliente;
import com.gerenciamentogeek.api.dto.clienteDTO;
import com.gerenciamentogeek.api.dto.response.ClienteResponse;
import com.gerenciamentogeek.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse salvarCliente(clienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getSexo(), clienteDTO.getTelefone());

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponse(
                clienteSalvo.getId(),
                clienteSalvo.getNomeC(),
                clienteSalvo.getCpfC(),
                clienteSalvo.getSexoC(),
                clienteSalvo.getTelefoneC()
        );
    }


    public ClienteResponse editarCliente(clienteDTO clienteDTO, Long id) {
        Optional<Cliente> clienteEditar = clienteRepository.findById(id);

        if(clienteEditar.isEmpty()){
            throw new RuntimeException("Cliente não encontrado");
        } else {
            Cliente cliente = clienteEditar.get();

            cliente.setNomeC(clienteDTO.getNome());
            cliente.setCpfC(clienteDTO.getCpf());
            cliente.setSexoC(clienteDTO.getSexo());
            cliente.setTelefoneC(clienteDTO.getTelefone());

            Cliente clienteEditado = clienteRepository.save(cliente);
            return new ClienteResponse(
                    clienteEditado.getId(),
                    clienteEditado.getNomeC(),
                    clienteEditado.getCpfC(),
                    clienteEditado.getSexoC(),
                    clienteEditado.getTelefoneC()
            );

        }
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNomeC(),
                cliente.getCpfC(),
                cliente.getSexoC(),
                cliente.getTelefoneC()
        );
    }

    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> listaCliente = new ArrayList<>();

        if(clientes.isEmpty()){
            throw new RuntimeException("Cliente não encontrado");
        } else {
            for(Cliente cliente : clientes){
                ClienteResponse clienteResponse = new ClienteResponse(
                        cliente.getId(),
                        cliente.getNomeC(),
                        cliente.getCpfC(),
                        cliente.getSexoC(),
                        cliente.getTelefoneC()
                );
                listaCliente.add(clienteResponse);
            }
            return listaCliente;
        }
    }

    public ClienteResponse buscarPorCPF(String cpf) {
        Cliente cliente = clienteRepository.buscarPorCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNomeC(),
                cliente.getCpfC(),
                cliente.getSexoC(),
                cliente.getTelefoneC()
        );
    }

    public void excluirCliente(Long id) {
        if(!clienteRepository.existsById(id)){
            throw new RuntimeException("Cliente não existe");
        }
        clienteRepository.deleteById(id);
    }
}
