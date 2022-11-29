package stomas.andres.views.tablas;

import stomas.andres.views.DocumentAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public abstract class DefaultTable extends JTable {
    private Vector<String> columnas;
    public DefaultTable(){
        columnas = new Vector<>();
        setCellSelectionEnabled(false);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(true);

    }

    public DefaultTable(String[] columns){
        this();
        insertColumns(columns);
    }
    protected void insertColumns(String[] columns){
        this.insertColumns(new Vector<>(Arrays.asList(columns)));
    }
    private void insertColumns(Vector<String> columnas){
        this.columnas = new Vector<>(columnas);
    }
    public void inserData(List<List<Object>> filas){
        Vector<Vector<Object>> vectorized = new Vector<>();
        for(List<Object> list: filas){
            Vector<Object> vector = new Vector<>();
            for(Object value: list){
                vector.add(value);
            }
            vectorized.add(vector);
        }
        insertData(vectorized);
    }
    public void insertData(Vector<Vector<Object>> filas){
        try {
            DefaultTableModel model = new DefaultTableModel(filas, columnas){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return getRealColumnClass(columnIndex);
                }
            };
            setModel(model);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    protected Class<?> getRealColumnClass(int columnIndex){
        return String.class;
    }

    public JTextField getFilterField(){
        RowSorter<? extends TableModel> rowSorter = this.getRowSorter();
        if (rowSorter == null) {
            this.setAutoCreateRowSorter(true);
            rowSorter = this.getRowSorter();
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
    public JScrollPane getScrollTable(){
        JScrollPane scroll = new JScrollPane(this);
        scroll.setBorder(new EmptyBorder(10,10,10,10));
        return scroll;
    }
}
