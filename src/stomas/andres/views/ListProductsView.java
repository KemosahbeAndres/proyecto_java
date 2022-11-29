package stomas.andres.views;

import stomas.andres.controllers.ListProductsController;
import stomas.andres.entitys.Producto;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.views.tablas.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ListProductsView extends Dialog{
    private ListProductsController controller;
    private ProductTable table;
    public ListProductsView(Frame parent, ListProductsController controller){
        super(parent, "Listar Productos");
        this.controller = controller;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        table = new ProductTable();

        refresh();

        add(table.getScrollTable(), BorderLayout.CENTER);
    }

    @Override
    protected void refresh() {
        try{
            table.inserData(controller.execute());
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error SQL: "+e.getMessage());
        }
    }
}
