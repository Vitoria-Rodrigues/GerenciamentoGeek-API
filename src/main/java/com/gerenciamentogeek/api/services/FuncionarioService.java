package com.gerenciamentogeek.api.services;

import com.gerenciamentogeek.api.classes.Cargo;
import com.gerenciamentogeek.api.classes.Funcionario;
import com.gerenciamentogeek.api.classes.Login;
import com.gerenciamentogeek.api.dto.funcionarioDTO;
import com.gerenciamentogeek.api.dto.response.FuncionarioResponse;
import com.gerenciamentogeek.api.repository.CargoRepository;
import com.gerenciamentogeek.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }


    public FuncionarioResponse salvarFuncionario(funcionarioDTO funcionarioDTO) {
        Login login = new Login(funcionarioDTO.getLogin(), passwordEncoder.encode(funcionarioDTO.getSenha()));
        Optional<Cargo> cargo = cargoRepository.findById(funcionarioDTO.getCargo());

        if (cargo.isEmpty()) {
            throw new RuntimeException("Cargo não encontrado");
        } else {
            Cargo cargoAchado = cargo.get();

            Funcionario funcionario = new Funcionario(funcionarioDTO.getNome(), funcionarioDTO.getCpf(), funcionarioDTO.getLogradouro(),
                    funcionarioDTO.getCep(), funcionarioDTO.getNumero(), funcionarioDTO.getComplemento(), funcionarioDTO.getTelefone(),
                    login, cargoAchado);

            Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

            return new FuncionarioResponse(
                    funcionarioSalvo.getId(),
                    funcionarioSalvo.getNomeF(),
                    funcionarioSalvo.getCpfF(),
                    funcionarioSalvo.getLogradouro(),
                    funcionarioSalvo.getCep(),
                    funcionarioSalvo.getNumero(),
                    funcionarioSalvo.getComplemento(),
                    funcionarioSalvo.getTelefoneF(),
                    funcionarioSalvo.getLogin().getLogin(),
                    funcionarioSalvo.getCargo().getFuncao()
            );

        }
    }

    public FuncionarioResponse editarFuncionario(funcionarioDTO funcionarioDTO, Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(funcionarioDTO.getCargo());
        Optional<Funcionario> funcionarioEditar = funcionarioRepository.findById(id);
        Cargo cargoAchado;

        if (cargo.isPresent()) {
            cargoAchado = cargo.get();
        } else {
            throw new RuntimeException("Cargo não encontrado");
        }

        if (funcionarioEditar.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            Funcionario funcionario = funcionarioEditar.get();

            funcionario.setNomeF(funcionarioDTO.getNome());
            funcionario.setCpfF(funcionarioDTO.getCpf());
            funcionario.setLogradouro(funcionarioDTO.getLogradouro());
            funcionario.setCep(funcionarioDTO.getCep());
            funcionario.setNumero(funcionarioDTO.getNumero());
            funcionario.setComplemento(funcionarioDTO.getComplemento());
            funcionario.setTelefoneF(funcionarioDTO.getTelefone());
            funcionario.getLogin().setSenha(funcionarioDTO.getSenha());
            funcionario.setCargo(cargoAchado);

            Funcionario fucionarioEditado = funcionarioRepository.save(funcionario);
            return new FuncionarioResponse(
                    funcionario.getId(),
                    funcionario.getNomeF(),
                    funcionario.getCpfF(),
                    funcionario.getLogradouro(),
                    funcionario.getCep(),
                    funcionario.getNumero(),
                    funcionario.getComplemento(),
                    funcionario.getTelefoneF(),
                    funcionario.getLogin().getLogin(),
                    funcionario.getCargo().getFuncao()
            );
        }
    }

    public FuncionarioResponse buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNomeF(),
                funcionario.getCpfF(),
                funcionario.getLogradouro(),
                funcionario.getCep(),
                funcionario.getNumero(),
                funcionario.getComplemento(),
                funcionario.getTelefoneF(),
                funcionario.getLogin().getLogin(),
                funcionario.getCargo().getFuncao()
        );
    }

    public List<FuncionarioResponse> listarfuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<FuncionarioResponse> listaFuncionario = new ArrayList<>();

        if (funcionarios.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            for (Funcionario funcionario : funcionarios) {
                FuncionarioResponse funcionarioResponse = new FuncionarioResponse(
                        funcionario.getId(),
                        funcionario.getNomeF(),
                        funcionario.getCpfF(),
                        funcionario.getLogradouro(),
                        funcionario.getCep(),
                        funcionario.getNumero(),
                        funcionario.getComplemento(),
                        funcionario.getTelefoneF(),
                        funcionario.getLogin().getLogin(),
                        funcionario.getCargo().getFuncao()
                );
                listaFuncionario.add(funcionarioResponse);
            }
            return listaFuncionario;
        }
    }

    public FuncionarioResponse buscarPorCPF(String cpf) {
        Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNomeF(),
                funcionario.getCpfF(),
                funcionario.getLogradouro(),
                funcionario.getCep(),
                funcionario.getNumero(),
                funcionario.getComplemento(),
                funcionario.getTelefoneF(),
                funcionario.getLogin().getLogin(),
                funcionario.getCargo().getFuncao()
        );
    }

    public void excluirFuncionario(Long id) {
        if(!funcionarioRepository.existsById(id)){
            throw new RuntimeException("Funcionario não existe");
        }
        funcionarioRepository.deleteById(id);
    }


}

