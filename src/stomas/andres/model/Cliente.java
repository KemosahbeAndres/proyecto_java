package stomas.andres.model;

public class Cliente extends Usuario {

    public Cliente(String nombre, String run, String direccion, String mail) {
        this.nombre = nombre;
        this.run = run;
        this.direccion = direccion;
        this.mail = mail;
    }


    @Override
    public String toString(){
        return "";
    }
}
