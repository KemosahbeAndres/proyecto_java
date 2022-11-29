package stomas.andres.views;

import stomas.andres.controllers.NewProductController;

import java.awt.*;

public class AddProductView extends Dialog{
    private NewProductController controller;
    public AddProductView(Frame parent, NewProductController controller){
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

    public static void main(String[] args) {
        AddProductView view = new AddProductView(null, new NewProductController());
        view. setVisible(true);
    }
}
