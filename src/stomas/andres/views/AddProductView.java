package stomas.andres.views;

import stomas.andres.controllers.AddProductController;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ProductItemModel;
import stomas.andres.views.tablas.ProductItemTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Vector;

public class AddProductView extends Dialog{
    private AddProductController controller;
    private JPanel main, action, busqueda;
    private JButton guardar, agregar, quitar, cancelar, mas, menos;
    private JTextField buscar;
    private JFormattedTextField cantidad;
    private ProductItemTable table;
    private Vector<Vectorizable> glosa;

    public AddProductView(View parent, AddProductController controller){
        super(parent, "Agregar Producto");
        this.controller = controller;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600,500);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        glosa = new Vector<>();


        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20,20,20,20));

        busqueda = new JPanel();
        busqueda.setLayout(new FlowLayout(FlowLayout.CENTER));
        busqueda.setBorder(new EmptyBorder(20,5,20,5));
        buscar = new JTextField(14);
        buscar.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void changed(DocumentEvent e) {
                String texto = buscar.getText().trim().toLowerCase();
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
        busqueda.add(new JLabel("Producto"));
        busqueda.add(buscar);
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

        main.add(table);

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
        AddProductView v =new AddProductView(null, new AddProductController(new ProductItemModel()));
        v.setVisible(true);

    }

}
