package stomas.andres;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        List<Orden> ordenes = new ArrayList<Orden>();
        boolean run = true;
        while(run){
            int opcion = menu();
            if(opcion == 0) run = false;
        }
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
