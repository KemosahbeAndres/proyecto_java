package stomas.andres.views;

import stomas.andres.controllers.NewOrderController;
import stomas.andres.entitys.Orden;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.Instant;

public class NewOrderView extends Dialog{
    private NewOrderController controller;
    public NewOrderView(Frame parent, NewOrderController controller){
        super(parent, "Nueva Orden de Compra");
        this.controller = controller;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        refresh();
    }

    @Override
    protected void refresh() {
        try {
            controller.execute(new Orden(1, 50, 2022, 1, 220.5, (int) Instant.now().getEpochSecond()));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "SQL Error: "+e.getMessage() );
        }
    }

}
