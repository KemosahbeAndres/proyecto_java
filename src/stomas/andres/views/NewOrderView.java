package stomas.andres.views;

import stomas.andres.controllers.NewOrderController;

import java.awt.*;

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
    }

    @Override
    protected void refresh() {

    }

    public static void main(String[] args) {
        NewOrderView view = new NewOrderView(null, new NewOrderController());
        view.setVisible(true);
    }
}
