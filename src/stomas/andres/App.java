package stomas.andres;

import stomas.andres.controllers.ListOrdersController;
import stomas.andres.controllers.LoginController;
import stomas.andres.controllers.RegisterController;
import stomas.andres.views.HomeView;
import stomas.andres.views.LoginView;
import stomas.andres.views.RegisterView;

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
