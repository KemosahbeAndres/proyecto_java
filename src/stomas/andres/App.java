package stomas.andres;

import stomas.andres.controller.ListOrdersController;
import stomas.andres.controller.LoginController;
import stomas.andres.controller.RegisterController;
import stomas.andres.view.HomeView;
import stomas.andres.view.LoginView;
import stomas.andres.view.RegisterView;

import java.util.Scanner;

public class App {
    private LoginController lController;
    private RegisterController rController;
    private ListOrdersController loController;
    private LoginView loginView;
    private HomeView homeView;
    private RegisterView registerView;
    public App(){
        loController = new ListOrdersController();
        homeView = new HomeView(loController);
        rController = new RegisterController();
        registerView = new RegisterView(rController);
        lController = new LoginController();
        loginView = new LoginView(lController, registerView, homeView);
        this.run();
    }
    public void run(){
        loginView.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

}
