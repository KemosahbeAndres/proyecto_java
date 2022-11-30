package stomas.andres.views;

import stomas.andres.controllers.*;
import stomas.andres.models.ProductItemModel;
import stomas.andres.models.ClientModel;
import stomas.andres.models.OrderModel;
import stomas.andres.models.ProductModel;
import stomas.andres.views.tablas.OrderTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class HomeView extends View {
    private LoginView loginView;
    private ListClientsView listClientsView;
    private NewClientView newClientView;
    private AddProductView addProductView;
    private ListProductsView listProductsView;
    private NewOrderView newOrderView;
    private LoginController loginController;
    private RegisterController registerController;
    private ListOrdersController controller;
    private OrderTable ordenes;
    private JPanel main;
    private JButton refrescar;
    private JMenuBar mainBar;
    private JMenu mClientes, mProductos, mOrdenes, mUsuarios;
    private JMenuItem iAddClient, iListClients, iAddProduct, iListProducts, iAddOrder, iAddUser;
    public HomeView(ListOrdersController loController, LoginController loginController, RegisterController registerController){
        super("Bienvenido");
        this.loginController = loginController;
        this.registerController = registerController;
        loginView = new LoginView(this, loginController, registerController);
        controller = loController;
        listClientsView = new ListClientsView(this, new ListClientsController(new ClientModel()));
        newClientView = new NewClientView(this, new NewClientController());
        addProductView = new AddProductView(this, new SearchProductController(new ProductModel()));
        listProductsView = new ListProductsView(this, new ListProductsController(new ProductModel(), new ProductItemModel()));
        newOrderView = new NewOrderView(this, new NewOrderController(new OrderModel()));

        setSize(600,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ordenes = new OrderTable();
        refrescar = new JButton("Refrescar");
        refrescar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ordenes.inserData(controller.execute());
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Error al refrescar: "+ex.getMessage());
                }
            }
        });

        mainBar = new JMenuBar();

        mClientes = new JMenu("Clientes");
        iAddClient = new JMenuItem("Agregar Cliente");
        iAddClient.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newClientView.setVisible(true);
            }
        });
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
        iAddProduct.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductView.setVisible(true);
            }
        });
        iListProducts = new JMenuItem("Listar Productos");
        iListProducts.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listProductsView.setVisible(true);
            }
        });

        mProductos.add(iAddProduct);
        mProductos.add(iListProducts);

        mOrdenes = new JMenu("Compras");
        iAddOrder = new JMenuItem("Generar Orden");
        iAddOrder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newOrderView.setVisible(true);
            }
        });

        mOrdenes.add(iAddOrder);

        mUsuarios = new JMenu("Usuarios");
        iAddUser = new JMenuItem("Agregar Usuario");

        mUsuarios.add(iAddUser);

        mainBar.add(mClientes);
        mainBar.add(mProductos);
        //mainBar.add(mOrdenes);
        //mainBar.add(mUsuarios);

        add(mainBar, BorderLayout.NORTH);


        if(loginView.doLogin()){
            renderContent();
        }else{
            loginView.setVisible(true);
        }

        //setVisible(true);
    }
    public void renderContent(){
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(10,10,10,10));

/*
        List<List<Object>> compras = new ArrayList<List<Object>>();
        compras.add(new ArrayList<>(Arrays.asList(new Object[]{0, 100, 2022, "Andres", 20.20, "Hoy" })));
        compras.add(new ArrayList<>(Arrays.asList(new Object[]{1, 300, 2021, "PEPE", 50.00, "Hoy" })));
*/
        try{
            ordenes.inserData(controller.execute());
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error SQL: "+e.getMessage());
        }
        System.out.println(ordenes.getColumnCount());
        JPanel line = new JPanel();
        line.setLayout(new FlowLayout(FlowLayout.LEFT));
        line.add(refrescar);
        line.add(new JLabel("Lista de Compras:", SwingConstants.LEFT));

        main.add(line);

        main.add(ordenes.getScrollTable());
        add(main, BorderLayout.CENTER);
    }
    @Override
    public void refresh(){
        /*try{
            if(ordenes != null){
                ordenes.inserData(controller.execute());
            }else{
                System.out.println("Es nulo;");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error SQL: "+e.getMessage());
        }*/
    }


}
