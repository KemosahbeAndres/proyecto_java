package stomas.andres;

import stomas.andres.controllers.ListOrdersController;
import stomas.andres.controllers.LoginController;
import stomas.andres.controllers.RegisterController;
import stomas.andres.models.OrderModel;
import stomas.andres.models.UserModel;
import stomas.andres.views.HomeView;
import stomas.andres.views.LoginView;
import stomas.andres.views.RegisterView;

public class App {
    private UserModel userModel;
    private LoginController loginController;
    private RegisterController registerController;
    private ListOrdersController loController;
    private HomeView homeView;
    public App(){
        userModel = new UserModel();
        registerController = new RegisterController(userModel);
        loginController = new LoginController(userModel);
        loController = new ListOrdersController(new OrderModel());

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
