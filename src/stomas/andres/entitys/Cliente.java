package stomas.andres.entitys;

public class Cliente extends Usuario {
    private String nombre, run, direccion, email;
    public Cliente(String nombre, String run, String direccion, String email) {
        this.nombre = nombre;
        this.run = run;
        this.direccion = direccion;
        this.email = email;
    }
    @Override
    public String toString(){
        return "";
    }
}
