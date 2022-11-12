package stomas.andres;

import stomas.andres.controller.LoginController;
import stomas.andres.model.Administrador;
import stomas.andres.model.Usuario;
import stomas.andres.view.LoginView;

import java.util.Scanner;

public class App {
    private LoginController lcontroller;
    private LoginView loginView;
    public App(){
        lcontroller = new LoginController();
        loginView = new LoginView(this);
        this.run();
    }
    public void run(){
        loginView.setVisible(true);
    }

    public void tryLogin(String user, String pass){
        Usuario u = lcontroller.execute(user, pass);
        System.out.println(u.getClass());
        if(u.getClass().getName().equals(Administrador.class.getName())){
            System.out.println("Es administrador");
        }else{
            System.out.println("Es cliente");
        }
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
