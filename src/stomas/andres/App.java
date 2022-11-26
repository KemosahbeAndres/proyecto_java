package stomas.andres;

import stomas.andres.controller.LoginController;
import stomas.andres.controller.LogoutController;
import stomas.andres.controller.RegisterController;
import stomas.andres.view.HomeView;
import stomas.andres.view.LoginView;
import stomas.andres.view.RegisterView;

import java.util.Scanner;

public class App {
    private LoginController lController;
    private LogoutController locontroller;
    private RegisterController rController;
    private LoginView loginView;
    private HomeView homeView;
    private RegisterView registerView;
    public App(){
        homeView = new HomeView();
        rController = new RegisterController();
        registerView = new RegisterView(rController);
        lController = new LoginController(homeView);
        loginView = new LoginView(lController, registerView);
        locontroller = new LogoutController(loginView);
        homeView.setController(locontroller);
        this.run();
    }
    public void run(){
        loginView.setVisible(true);
    }

    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new App();

        /*
        List<Orden> ordenes = new ArrayList<Orden>();
        boolean run = true;
        while(run){
            int opcion = menu();
            if(opcion == 0) run = false;
        }*/
    }
    public static int menu(){
        System.out.println("SISTEMA ORDENES DE COMPRA");
        System.out.println("1. Crear Orden");
        System.out.println("2. Ver Ordenes");
        System.out.println("3. Eliminar Orden");
        System.out.println("0. Salir");
        return getInt();
    }
    public static int getInt(){
        return getInt("> ");
    }
    public static int getInt(String mensaje){
        try{
            System.out.print(mensaje.trim() + " ");
            return sc.nextInt();
        }catch(Exception e){
            System.out.println("Debes ingresar un numero entero!");
            sc.nextLine();
            return getInt(mensaje);
        }
    }

}
