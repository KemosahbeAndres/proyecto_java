package stomas.andres.views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Arrays;
import java.util.Vector;

final public class TableFactory {
    public static JTable buildTable(String[] nombreCols, String[][] dataRows){
        JTable table = new JTable();
        Vector<String> columnas = new Vector<>(Arrays.asList(nombreCols));
        Vector<Vector<Object>> filas = new Vector<>();

        for (String[] row: dataRows) {
            Vector<Object> v = new Vector<>();
            for(String value: row) {
                v.add(value);
            }
            filas.add(v);
        }
        DefaultTableModel model = new DefaultTableModel(filas, columnas){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
                //switch (columnIndex){

                //}
            }
        };
        table.setModel(model);
        return table;
    }

    public static JTextField buildFilter(JTable tabla){
        RowSorter<? extends TableModel> rowSorter = tabla.getRowSorter();
        if (rowSorter == null) {
            tabla.setAutoCreateRowSorter(true);
            rowSorter = tabla.getRowSorter();
        }
        JTextField textField = new JTextField(14);
        TableRowSorter<? extends TableModel> trSorter = (TableRowSorter<? extends TableModel>) rowSorter;
        textField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void changed(DocumentEvent e) {
                String texto = textField.getText();
                if(texto.trim().length() != 0){
                    trSorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }else{
                    trSorter.setRowFilter(null);
                }
            }
        });
        return textField;
    }
}
