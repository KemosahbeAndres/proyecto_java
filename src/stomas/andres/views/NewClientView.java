package stomas.andres.views;

import stomas.andres.controllers.NewClientController;
import stomas.andres.entitys.Cliente;

import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class NewClientView extends Dialog{
    private NewClientController controller;
    private JTextField nombre, telefono, direccion, run;
    private JPanel main, action, left, right, name, dir, rut, tel;
    private JButton cancelar, guardar;
    public NewClientView(Frame parent, NewClientController controller){
        super(parent, "Agregar cliente");
        this.controller = controller;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        main = new JPanel();
        main.setLayout(new GridLayout(1,2));

        name = new JPanel();
        name.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //name.setBorder(new EmptyBorder(10,10,10,10));
        nombre = new JTextField(12);
        name.add(new JLabel("Nombre"));
        name.add(nombre);

        dir = new JPanel();
        dir.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dir.add(new JLabel("Direccion"));
        direccion = new JTextField(12);
        dir.add(direccion);

        left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(20,10,20,10));
        left.add(name);
        left.add(dir);

        main.add(left);

        rut = new JPanel();
        rut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rut.add(new JLabel("RUN"));
        run = new JTextField(12);
        rut.add(run);

        tel = new JPanel();
        tel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        tel.add(new JLabel("Telefono"));
        telefono = new JTextField(12);
        tel.add(telefono);

        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new EmptyBorder(20,10,20,10));
        right.add(rut);
        right.add(tel);

        main.add(right);

        action = new JPanel();
        action.setLayout(new FlowLayout(FlowLayout.CENTER));
        action.setBorder(new EmptyBorder(5,10,20,10));
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        guardar = new JButton("Guardar");
        guardar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nombre.getText();
                String phone = telefono.getText();
                String address = direccion.getText();
                String elrun = run.getText();
                try{
                    if(name.isBlank() || phone.isBlank() || address.isBlank() || elrun.isBlank()) throw new Exception("No puede haber campos vacios");
                    if(!rutValido(run.getText())) throw new Exception("El run no es valido, o el formato es incorrecto.");
                    if(phone.length() > 8) throw new Exception("El formato del telefono es incorrecto.");
                    controller.execute(new Cliente(0, name, elrun, address, Integer.parseInt(phone), Timestamp.from(Instant.now())));
                    JOptionPane.showMessageDialog(parent, "Cliente agregado con exito");
                    setVisible(false);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(parent, "Error SQL: "+ ex.getMessage());
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(parent, "El telefono debe ser un numero de 8 digitos");
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(parent, "Datos incorrectos: "+exception.getMessage());
                }

            }
        });
        action.add(cancelar);
        action.add(guardar);

        add(main, BorderLayout.CENTER);
        add(action, BorderLayout.SOUTH);

    }

    @Override
    protected void refresh() {
        nombre.setText("");
        direccion.setText("");
        run.setText("");
        telefono.setText("");
    }
    private boolean rutValido(String run){
        if(run.trim().split("-").length > 2) return false;
        String rut = run.trim().split("-")[0].trim();
        String dv = run.trim().split("-")[1].trim();
        String[] rutArray = new String[rut.length()];
        int j = 0;
        for (char c: rut.toCharArray()){
            rutArray[j] = String.valueOf(c);
            j++;
        }
        Object[] array = invertir(rutArray);
        int a = 2;
        int rutSumado = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt((String) array[i]) * a;
            rutSumado += Integer.parseInt(String.valueOf(array[i]));
            if (a == 7) {
                a = 1;
            }
            a++;
        }
        int resto = rutSumado % 11;
        String Digito = String.valueOf(11 - resto);
        if (Digito.equals("11")) {
            Digito = "0";
        }
        if (Digito.equals("10")) {
            Digito = "K";
        }
        if(Digito.equals(dv)) return true;
        return false;
    }
    private static Object[] invertir(String[] array) {
        Object[] invertir_int = new Object[array.length];
        int maximo = array.length;

        for (int i = 0; i < array.length; i++) {
            Object j = array[maximo - 1];
            invertir_int[maximo - 1] = array[i];
            maximo--;
        }
        return invertir_int;
    }

    public static void main(String[] args) {
        NewClientView view = new NewClientView(null, new NewClientController());
        view.setVisible(true);
    }
}
