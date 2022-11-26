package stomas.andres.controller;

import stomas.andres.view.LoginView;

import javax.swing.*;

public class LogoutController {
    private LoginView login;
    public LogoutController(LoginView view){
        login = view;
    }
    public void execute(){
        login.resetFields();
        login.setVisible(true);
    }
}
