package br.com.portaljc.jcconsultoria.controllers;

public class LoginController {

    public static String validarLogin(String email, String senha) {
        if (email.equals(""))
            return "E-mail não informado!";
        else if (senha.equals(""))
            return "Senha não informada!";
        else
            return "";
    }
}
