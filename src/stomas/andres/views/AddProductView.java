package stomas.andres.views;

import stomas.andres.controllers.AddProductController;

import javax.swing.*;
import java.awt.*;

public class AddProductView extends Dialog{
    private AddProductController controller;
    private JPanel main, action, searchProduct;

    public AddProductView(View parent, AddProductController controller){
        super(parent, "Agregar Producto");
        this.controller = controller;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,300);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());



    }

    @Override
    protected void refresh() {

    }

}
