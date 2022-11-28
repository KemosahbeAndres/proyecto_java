package stomas.andres.views;

import stomas.andres.controllers.ListClientsController;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ListClientsView extends Dialog {
    private JTable table;
    private JScrollPane scPanel;
    private ListClientsController controller;
    public ListClientsView(ListClientsController ctr){
        this(null, ctr);
    }
    public ListClientsView(Frame parent, ListClientsController scController){
        super(parent, "Clientes");
        controller = scController;
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };
        String[] columnNames = { "Nombre", "Run", "Direccion", "Email" };

        table = TableFactory.buildTable(columnNames, data);

        table.setEnabled(false);

        scPanel = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Buscar"));
        panel.add(TableFactory.buildFilter(table));

        add(panel, BorderLayout.NORTH);
        add(scPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        ListClientsView dialog = new ListClientsView(new ListClientsController());
        dialog.setVisible(true);
    }
}
