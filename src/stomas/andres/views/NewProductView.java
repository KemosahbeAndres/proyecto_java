package stomas.andres.views;

import stomas.andres.controllers.NewProductController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewProductView extends Dialog{
    private NewProductController controller;
    private JPanel main, action, pNombre, pPrecio, pStock;
    private JTextField nombre, precio, stock;
    private JButton cancelar, guardar;
    public NewProductView(Frame parent, NewProductController controller){
        super(parent, "Nuevo Producto");
        this.controller = controller;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300,250);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20,20,10,20));

        pNombre = new JPanel();
        pNombre.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pNombre.add(new JLabel("Nombre"));
        nombre = new JTextField(14);
        pNombre.add(nombre);

        main.add(pNombre);

        pPrecio = new JPanel();
        pPrecio.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pPrecio.add(new JLabel("Precio"));
        precio = new JTextField(14);
        pPrecio.add(precio);

        main.add(pPrecio);

        pStock = new JPanel();
        pStock.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pStock.add(new JLabel("Stock"));
        stock = new JTextField(14);
        pStock.add(stock);

        main.add(pStock);

        action = new JPanel();
        action.setLayout(new FlowLayout(FlowLayout.CENTER));
        action.setBorder(new EmptyBorder(10,20,20,20));

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        guardar = new JButton("Guardar");
        action.add(cancelar);
        action.add(guardar);

        add(main, BorderLayout.CENTER);
        add(action, BorderLayout.SOUTH);
    }

    @Override
    protected void refresh() {

    }

    public static void main(String[] args) {
        NewProductView view = new NewProductView(null, new NewProductController());
        view.setVisible(true);
    }
}
