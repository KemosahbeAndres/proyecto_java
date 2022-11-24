package stomas.andres.controller;

import stomas.andres.model.Usuario;

import javax.swing.*;

final public class LoginController {
    private JFrame home;
    public LoginController(JFrame view){
        home = view;
    }
    public void execute(){
        home.setVisible(true);
    }
}
