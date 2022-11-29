package stomas.andres.views;

import stomas.andres.controllers.NewClientController;
import stomas.andres.entitys.Cliente;

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
                try{
                    controller.execute(new Cliente(
                            0,
                            nombre.getText(),
                            Integer.parseInt(telefono.getText()),
                            direccion.getText(),
                            run.getText(),
                            Timestamp.from(Instant.now())
                    ));
                    setVisible(false);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Error SQL: "+ ex.getMessage());
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null, "Datos incorrectos: "+exception.getMessage());
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

    public static void main(String[] args) {
        NewClientView view = new NewClientView(null, new NewClientController());
        view.setVisible(true);
    }
}
