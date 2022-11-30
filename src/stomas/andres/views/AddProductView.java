package stomas.andres.views;

import stomas.andres.controllers.AddProductController;
import stomas.andres.controllers.SearchProductController;
import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ProductItemModel;
import stomas.andres.models.ProductModel;
import stomas.andres.views.tablas.ProductItemTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Vector;

public class AddProductView extends Dialog{
    private SearchProductController controller;
    private JPanel main, action, busqueda, panelCliente;
    private JButton guardar, agregar, quitar, cancelar, mas, menos, seleccionar;
    private JTextField buscar, buscarCliente;
    private JLabel id, nombre, run, direccion, email, coincidencias;
    private JFormattedTextField cantidad;
    private ProductItemTable table;
    private Vector<Vectorizable> glosa;
    private Cliente cliente;

    public AddProductView(View parent, SearchProductController controller){
        super(parent, "Agregar Producto");
        this.controller = controller;
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

        JPanel datos = new JPanel();
        datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
        datos.setBorder(new EmptyBorder(0,20,0,50));

        id = new JLabel("");
        nombre = new JLabel("");
        run = new JLabel("");
        direccion = new JLabel("");
        email = new JLabel("");

        datos.add(new JLabel("ID:"));
        datos.add(id);
        datos.add(new JLabel("Nombre:"));
        datos.add(nombre);
        datos.add(new JLabel("RUN:"));
        datos.add(run);
        datos.add(new JLabel("Direccion:"));
        datos.add(direccion);
        datos.add(new JLabel("Email:"));
        datos.add(email);

        panelCliente.add(new JLabel("Cliente"));
        panelCliente.add(buscarCliente);
        panelCliente.add(seleccionar);
        panelCliente.add(datos);

        main.add(panelCliente);

        busqueda = new JPanel();
        busqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        busqueda.setBorder(new EmptyBorder(10,0,20,0));
        buscar = new JTextField(14);
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
        quitar = new JButton("Quitar");
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
        table.setVisible(true);

        main.add(table.getScrollTable());

        action = new JPanel();
        action.setLayout(new FlowLayout(FlowLayout.CENTER));
        action.setBorder(new EmptyBorder(20,20,20,20));

        cancelar = new JButton("Cancelar");
        guardar = new JButton("Guardar");

        action.add(cancelar);
        action.add(guardar);

        add(main, BorderLayout.CENTER);
        add(action, BorderLayout.SOUTH);
    }

    @Override
    protected void refresh() {
        buscar.setText("");
        cantidad.setText("");
        //table.inserData(controller.execute());
    }

    public static void main(String[] args) {
        AddProductView v =new AddProductView(null, new SearchProductController(new ProductModel()));
        v.setVisible(true);

    }

}
