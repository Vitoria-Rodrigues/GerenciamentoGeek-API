package com.gerenciamentogeek.api.dto.response;

public class LoginResponse {
    private String login;
    private String cargo;

    public LoginResponse() {
    }

    public LoginResponse(String login, String cargo) {
        this.login = login;
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
