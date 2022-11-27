package stomas.andres.view;

import stomas.andres.controller.ListOrdersController;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    private LoginView lView;
    private ListOrdersController controller;
    private JMenuBar mainBar;
    private JMenu mClientes, mProductos, mOrdenes;
    private JMenuItem iAddClient, iListClients, iAddProduct, iListProducts, iAddOrder;
    public HomeView(ListOrdersController loController){
        super("Bienvenido");
        controller = loController;
        setSize(600,400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Saliendo");
                lView.resetFields();
                lView.setVisible(true);
            }
        });

        mainBar = new JMenuBar();

        mClientes = new JMenu("Clientes");
        iAddClient = new JMenuItem("Agregar Cliente");
        iListClients = new JMenuItem("Listar Clientes");

        mClientes.add(iAddClient);
        mClientes.add(iListClients);

        mProductos = new JMenu("Productos");
        iAddProduct = new JMenuItem("Agregar Producto");
        iListProducts = new JMenuItem("Listar Productos");

        mProductos.add(iAddProduct);
        mProductos.add(iListProducts);

        mOrdenes = new JMenu("Compras");
        iAddOrder = new JMenuItem("Generar Orden");

        mOrdenes.add(iAddOrder);

        mainBar.add(mClientes);
        mainBar.add(mProductos);
        mainBar.add(mOrdenes);
        add(mainBar, BorderLayout.NORTH);
        JPanel main = new JPanel();
        main.setLayout(new FlowLayout());
        main.add(new JLabel("HOME"));

        add(main, BorderLayout.CENTER);

        //setVisible(true);
    }
    public void setLoginView(LoginView view){
        this.lView = view;
    }


}
