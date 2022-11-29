package stomas.andres.views;

import stomas.andres.controllers.NewOrderController;
import stomas.andres.entitys.Orden;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class NewOrderView extends Dialog{
    private NewOrderController controller;
    private JPanel main, action;

    private JButton cancelar, guardar, buscarP, buscarC;
    public NewOrderView(View parent, NewOrderController controller){
        super(parent, "Nueva Orden de Compra");
        this.controller = controller;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20,20,20,20));

        add(main, BorderLayout.CENTER);

        action = new JPanel();
        action.setLayout(new FlowLayout(FlowLayout.CENTER));
        action.setBorder(new EmptyBorder(20,20,20,20));

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
                try {
                    controller.execute(new Orden(1, 50, 2022, 1, 220.5, Timestamp.from(Instant.now()) ));
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "SQL Error: "+ex.getMessage() );
                }
            }
        });

        action.add(cancelar);
        action.add(guardar);

        add(action, BorderLayout.SOUTH);

        refresh();


    }

    @Override
    protected void refresh() {

    }

}
