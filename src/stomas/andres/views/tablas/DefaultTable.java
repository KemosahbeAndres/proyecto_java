package stomas.andres.views.tablas;

import stomas.andres.entitys.Vectorizable;
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
    private JTextField textField;
    public DefaultTable(){
        textField = new JTextField(14);
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
        try {
            setModel(new DefaultTableModel(new Vector<Vector<Object>>(), columnas));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
    public void inserData(Vector<Vectorizable> filas) {
        Vector<Vector<Object>> productos = new Vector<>();
        for(Vectorizable v: filas){
            productos.add(v.toVector());
        }
        insertData(productos);
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
        TableRowSorter<? extends TableModel> trSorter = (TableRowSorter<? extends TableModel>) rowSorter;
        textField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void changed(DocumentEvent e) {
                String texto = textField.getText();
                if(texto.trim().length() != 0){
                    trSorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }else{
                    //System.out.println("Busqueda desactivada");
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
