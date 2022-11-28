package stomas.andres.views;

import stomas.andres.controllers.ListClientsController;
import stomas.andres.controllers.ListOrdersController;
import stomas.andres.controllers.LoginController;
import stomas.andres.controllers.RegisterController;
import stomas.andres.views.tablas.OrderTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    private LoginView loginView;
    private ListClientsView listClientsView;
    private LoginController loginController;
    private RegisterController registerController;
    private ListOrdersController controller;
    private JMenuBar mainBar;
    private JMenu mClientes, mProductos, mOrdenes;
    private JMenuItem iAddClient, iListClients, iAddProduct, iListProducts, iAddOrder;
    public HomeView(ListOrdersController loController, LoginController loginController, RegisterController registerController){
        super("Bienvenido");
        this.loginController = loginController;
        this.registerController = registerController;
        loginView = new LoginView(this, loginController, registerController);
        controller = loController;
        listClientsView = new ListClientsView(this, new ListClientsController());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainBar = new JMenuBar();

        mClientes = new JMenu("Clientes");
        iAddClient = new JMenuItem("Agregar Cliente");
        iListClients = new JMenuItem("Listar Clientes");
        iListClients.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listClientsView.setVisible(true);
            }
        });

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

        if(loginView.doLogin()){
            renderContent();
        }else{
            loginView.setVisible(true);
        }

        //setVisible(true);
    }
    public void renderContent(){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        OrderTable ordenes = new OrderTable();

        List<List<Object>> compras = new ArrayList<List<Object>>();
        compras.add(new ArrayList<>(Arrays.asList(new Object[]{0, 100, 2022, "Andres", 20.20, "Hoy" })));
        compras.add(new ArrayList<>(Arrays.asList(new Object[]{1, 300, 2021, "PEPE", 50.00, "Hoy" })));

        ordenes.inserData(compras);
        System.out.println(ordenes.getColumnCount());
        main.add(new JLabel("HOME"));
        main.add(ordenes.getScrollTable());
        add(main, BorderLayout.CENTER);
    }


}
