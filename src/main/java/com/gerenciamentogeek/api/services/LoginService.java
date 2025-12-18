package com.gerenciamentogeek.api.services;

import com.gerenciamentogeek.api.classes.Funcionario;
import com.gerenciamentogeek.api.dto.loginDTO;
import com.gerenciamentogeek.api.dto.response.LoginResponse;
import com.gerenciamentogeek.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final FuncionarioRepository funcionarioRepository;

    public LoginService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(loginDTO loginDTO) {

        Funcionario funcionario = funcionarioRepository.findByUsuario(loginDTO.getLogin());

        if (funcionario == null) {
            return null;
        }

        boolean senhaCorreta = passwordEncoder.matches(
                loginDTO.getSenha(),
                funcionario.getLogin().getSenha()
        );

        if (!senhaCorreta) {
            return null;
        }

        return new LoginResponse(
                funcionario.getId(),
                funcionario.getNomeF(),
                funcionario.getCargo().getFuncao()
        );
    }
}
