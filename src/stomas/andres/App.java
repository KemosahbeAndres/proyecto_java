package stomas.andres;

import stomas.andres.controllers.ListOrdersController;
import stomas.andres.controllers.LoginController;
import stomas.andres.controllers.RegisterController;
import stomas.andres.views.HomeView;
import stomas.andres.views.LoginView;
import stomas.andres.views.RegisterView;

public class App {

    LoginController loginController;
    private RegisterController registerController;
    private ListOrdersController loController;
    private HomeView homeView;
    public App(){

        registerController = new RegisterController();
        loginController = new LoginController();
        loController = new ListOrdersController();

        homeView = new HomeView(loController, loginController, registerController);
        this.run();
    }
    public void run(){
        homeView.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

}
