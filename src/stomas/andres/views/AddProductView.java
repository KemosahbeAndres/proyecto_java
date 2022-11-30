package stomas.andres.views;

import stomas.andres.controllers.AddProductController;

import java.awt.*;

public class AddProductView extends Dialog{
    private AddProductController controller;
    public AddProductView(Frame parent, AddProductController controller){
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
