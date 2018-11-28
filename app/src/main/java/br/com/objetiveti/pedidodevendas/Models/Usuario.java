package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 24/01/2018.
 */

public class Usuario {
    private String login;
    private String senha;

    public Usuario(String uLogin, String uSenha) {
        login = uLogin;
        senha = uSenha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
