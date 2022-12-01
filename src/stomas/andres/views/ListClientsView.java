package stomas.andres.views;

import stomas.andres.controllers.ListClientsController;
import stomas.andres.views.tablas.ClientTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListClientsView extends Dialog {
    private ClientTable table;
    private JScrollPane scPanel;
    private JTextField text;
    private ListClientsController controller;
    public ListClientsView(ListClientsController ctr){
        this(null, ctr);
    }
    public ListClientsView(Frame parent, ListClientsController scController){
        super(parent, "Clientes");
        controller = scController;
        setSize(500, 300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        table = new ClientTable();
        /*
        List<List<Object>> lista = new ArrayList<List<Object>>();
        lista.add(new ArrayList<>(Arrays.asList(new Object[]{"Andres", "19.149.514-4", "Avenida Diego Portales", "a.cubillos@ribd.cl"})));
        lista.add(new ArrayList<>(Arrays.asList(new Object[]{"Pedro", "8.435.678-2", "Avenida sIEMPRE VIVA", "p.rebolledo@ribd.cl"})));

        table.inserData(lista);*/

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Buscar"));
        text = table.getFilterField();
        panel.add(text);

        JButton refrescar = new JButton("Refrescar");
        refrescar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        panel.add(refrescar);
        refresh();

        add(panel, BorderLayout.NORTH);
        add(table.getScrollTable(), BorderLayout.CENTER);

    }

    @Override
    protected void refresh() {
        try{
            table.inserData(controller.execute());
            text = table.getFilterField();
            text.setText("");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error SQL: "+e.getMessage());
        }
    }
}
