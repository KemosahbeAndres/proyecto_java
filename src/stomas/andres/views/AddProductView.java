package stomas.andres.views;

import stomas.andres.controllers.NewOrderController;
import stomas.andres.controllers.SearchClientController;
import stomas.andres.controllers.SearchProductController;
import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.Producto;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ClientModel;
import stomas.andres.models.ProductModel;
import stomas.andres.views.tablas.ProductItemTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Vector;

public class AddProductView extends Dialog{
    private SearchProductController controller;
    private SearchClientController clientController;
    private NewOrderController orderController;
    private JPanel main, action, busqueda, panelCliente;
    private JButton guardar, agregar, quitar, cancelar, mas, menos, seleccionar;
    private JTextField buscar, buscarCliente;
    private JLabel id, nombre, run, direccion, telefono, coincidencias, total;
    private JFormattedTextField cantidad;
    private ProductItemTable table;
    private Vector<Vectorizable> glosa;
    private Cliente cliente = null;
    private int seleccionado;

    public AddProductView(View parent, SearchProductController controller, SearchClientController cController, NewOrderController oController){
        super(parent, "Agregar Producto");
        this.controller = controller;
        this.clientController = cController;
        this.orderController = oController;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600,500);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        glosa = new Vector<>();
        coincidencias = new JLabel("");

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20,20,20,20));

        panelCliente = new JPanel();
        panelCliente.setLayout(new FlowLayout(FlowLayout.LEFT));
        buscarCliente = new JTextField(14);
        seleccionar = new JButton("Seleccionar");
        seleccionar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = buscarCliente.getText().trim();
                try{
                    if(clientController.existencias() > 0 && texto.length() > 0){
                        cliente = clientController.search(texto);
                        id.setText(String.valueOf(cliente.getId()));
                        nombre.setText(cliente.getNombre());
                        run.setText(cliente.getRun());
                        direccion.setText(cliente.getDireccion());
                        telefono.setText(String.valueOf(cliente.getTelefono()));
                    }else {
                        JOptionPane.showMessageDialog(parent, "No existen clientes, debes ingresar al menos 1 cliente.");
                    }
                }catch (SQLException sql){
                    System.out.println(sql.getMessage());
                }
                refresh();
            }
        });
        JPanel datos = new JPanel();
        datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
        datos.setBorder(new EmptyBorder(0,20,0,50));

        id = new JLabel("");
        nombre = new JLabel("");
        run = new JLabel("");
        direccion = new JLabel("");
        telefono = new JLabel("");

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        JPanel iPanel = new JPanel(layout);
        JPanel nPanel = new JPanel(layout);
        JPanel rPanel = new JPanel(layout);
        JPanel dPanel = new JPanel(layout);
        JPanel tPanel = new JPanel(layout);

        iPanel.add(new JLabel("ID:"));
        iPanel.add(id);
        datos.add(iPanel);

        nPanel.add(new JLabel("Nombre:"));
        nPanel.add(nombre);
        datos.add(nPanel);

        rPanel.add(new JLabel("Run:"));
        rPanel.add(run);
        datos.add(rPanel);

        dPanel.add(new JLabel("Direccion:"));
        dPanel.add(direccion);
        datos.add(dPanel);

        tPanel.add(new JLabel("Telefono:"));
        tPanel.add(telefono);
        datos.add(tPanel);

        panelCliente.add(new JLabel("Cliente"));
        panelCliente.add(buscarCliente);
        panelCliente.add(seleccionar);
        panelCliente.add(datos);

        main.add(panelCliente);

        busqueda = new JPanel();
        busqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        busqueda.setBorder(new EmptyBorder(10,0,20,0));
        buscar = new JTextField(14);
        buscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                refresh();
            }
        });
        buscar.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void changed(DocumentEvent e) {
                String texto = buscar.getText().trim().toLowerCase();
                try{
                    if(texto.length() > 0) {
                        coincidencias.setText(String.valueOf(controller.existencias(texto)));
                    }else{
                        coincidencias.setText("");
                    }
                }catch(SQLException sql){
                    System.out.println(sql.getMessage());
                    coincidencias.setText("");
                }
            }
        });
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false); //this is the key

        cantidad = new JFormattedTextField(numberFormatter);
        cantidad.setColumns(2);
        cantidad.setValue(1);

        agregar = new JButton("Agregar");
        agregar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = buscar.getText().trim().toLowerCase();
                if(texto.length()>0) {
                    try {
                        if(controller.existencias(texto) > 0) {
                            Producto producto = controller.search(texto);
                            int st = producto.getStock();
                            int cant = (int) cantidad.getValue();
                            if(st >= 0 && st-cant >= 0){
                                ProductoCompra productoCompra = new ProductoCompra(producto, cant, 1);
                                agregar(productoCompra);
                            }else{
                                JOptionPane.showMessageDialog(parent, "No hay stock suficiente.");
                            }
                            refresh();
                        }
                    } catch (SQLException sql) {
                        System.out.println(sql.getMessage());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                }
                refresh();
            }
        });
        quitar = new JButton("Quitar");
        quitar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(seleccionado >= 0) {
                    glosa.remove(seleccionado);
                }
                refresh();
            }
        });
        quitar.setEnabled(false);

        JPanel prod = new JPanel();
        prod.setLayout(new BoxLayout(prod, BoxLayout.Y_AXIS));
        JPanel prodiv = new JPanel();
        prodiv.setLayout(new FlowLayout(FlowLayout.LEFT));
        prodiv.add(new JLabel("Producto"));
        prodiv.add(buscar);
        prod.add(prodiv);
        JPanel co = new JPanel();
        co.setLayout(new FlowLayout(FlowLayout.LEFT));
        co.add(new JLabel("Encontrados:"));
        co.add(coincidencias);
        prod.add(co);
        busqueda.add(prod);
        busqueda.add(new JLabel("Cantidad"));
        cantidad.setHorizontalAlignment(SwingConstants.CENTER);
        cantidad.setEditable(false);
        busqueda.add(cantidad);
        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(2,1));
        mas = new JButton("+");
        menos = new JButton("-");
        menos.setEnabled(false);
        mas.setMargin(new Insets(1,2,1,2));
        menos.setMargin(new Insets(1,2,1,2));
        mas.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantidad.setValue((int)cantidad.getValue()+1);
                menos.setEnabled(true);
            }
        });
        menos.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantidad.setValue((int)cantidad.getValue()-1);
                if((int)cantidad.getValue() <= 1){
                    cantidad.setValue(1);
                    menos.setEnabled(false);
                }
            }
        });
        controles.add(mas);
        controles.add(menos);
        busqueda.add(controles);
        busqueda.add(agregar);
        busqueda.add(quitar);

        main.add(busqueda);

        table = new ProductItemTable();

        table.inserData(glosa);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                seleccionado = table.getSelectedRow();
                System.out.println("Seleccion: "+seleccionado);
                quitar.setEnabled(true);
                ProductoCompra producto = (ProductoCompra) glosa.get(seleccionado);
                cantidad.setValue(producto.getCantidad());
            }
        });

        main.add(table.getScrollTable());

        JPanel tpanel = new JPanel();
        tpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        tpanel.add(new JLabel("Total:"));
        total = new JLabel("");
        tpanel.add(total);

        main.add(tpanel);

        action = new JPanel();
        action.setLayout(new FlowLayout(FlowLayout.CENTER));
        action.setBorder(new EmptyBorder(0,20,20,20));

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                refresh();
                cliente = null;
                glosa.clear();
            }
        });
        guardar = new JButton("Guardar");
        guardar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(glosa.size()>0 && cliente != null){
                    try{
                        orderController.execute(glosa, cliente);
                    }catch (SQLException sql){
                        JOptionPane.showMessageDialog(parent, "No se pudo generar la orden. ERROR: "+ sql.getMessage());
                    }
                    refresh();
                    cliente = null;
                    glosa.clear();
                    JOptionPane.showMessageDialog(parent, "Orden generada con exito.");
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(parent, "No puedes generar una orden vacia.");
                }
            }
        });

        action.add(cancelar);
        action.add(guardar);

        add(main, BorderLayout.CENTER);
        add(action, BorderLayout.SOUTH);
    }

    @Override
    protected void refresh() {
        table.clearSelection();
        buscar.setText("");
        cantidad.setValue(1);
        menos.setEnabled(false);
        table.inserData(glosa);
        seleccionado = -1;
        quitar.setEnabled(false);
        table.clearSelection();
        sumar();
    }
    private void sumar(){
        int suma = 0;
        for (Vectorizable object : this.glosa) {
            ProductoCompra productoCompra = (ProductoCompra) object;
            suma += productoCompra.getTotal();
        }
        total.setText(String.valueOf(suma));
    }
    protected void agregar(ProductoCompra producto){
/*
        if(glosa.size() > 0){
            for (Vectorizable object : this.glosa) {
                int index = this.glosa.indexOf(object);
                ProductoCompra productoCompra = (ProductoCompra) object;
                this.glosa.remove(index);
                // unir productos y calcular el subtotal
                if (productoCompra.getNombre().equals(producto.getNombre())) {
                    productoCompra.setCantidad(productoCompra.getCantidad() + producto.getCantidad());
                }
                this.glosa.add(index, productoCompra);
            }
        }
*/
        glosa.add(producto);
        System.out.println("Agregado: "+ producto.getNombre()+ " | ID: "+ producto.getId()+ " | Cantidad: "+ producto.getCantidad());
        System.out.println("Tamaño Glosa"+ glosa.size());
/*
        for (Vectorizable object : this.glosa) {
            int index = this.glosa.indexOf(object);
            ProductoCompra productoCompra = (ProductoCompra) object;
            this.glosa.remove(index);
            System.out.println("Indice: " + String.valueOf(index));
            System.out.println("Producto: " + productoCompra.getNombre() + " | ID: " + productoCompra.getId());
            //ordenar los indices
            if (productoCompra.getId() != index + 1) {
                productoCompra.setId(index + 1);
            }
            this.glosa.add(index, productoCompra);
        }

 */
        sumar();

    }


}
