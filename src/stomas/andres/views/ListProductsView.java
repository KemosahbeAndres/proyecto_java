package stomas.andres.views;

import stomas.andres.controllers.ListProductsController;
import stomas.andres.controllers.SearchOrderController;
import stomas.andres.entitys.*;
import stomas.andres.views.tablas.ProductItemTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class ListProductsView extends Dialog{
    private ListProductsController controller;
    private SearchOrderController orderController;
    private ProductItemTable table;
    private JTextField buscar;
    private JPanel top, side, oPanel, cPanel;
    private JLabel oID, oNumber, oYear, oPrice, oDate, cID, cName, cPhone, cAddress, cRun, cDate;
    private Vector<Vectorizable> productos;
    public ListProductsView(View parent, ListProductsController controller, SearchOrderController oController){
        super(parent, "Listar Productos");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                refresh();
            }
        });
        this.controller = controller;
        this.orderController = oController;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(900,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        productos = new Vector<>();

        side = new JPanel();
        side.setLayout(new GridLayout(1,2));
        side.setBorder(new EmptyBorder(0,10,0,10));
        side.setMinimumSize(new Dimension(400,200));

        oPanel = new JPanel();
        oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.Y_AXIS));
        oPanel.setBorder(new TitledBorder("Orden"));

        oID = new JLabel("");
        JPanel oip = new JPanel(new FlowLayout(FlowLayout.LEFT));
        oip.add(new JLabel("ID:"));
        oip.add(oID);
        oPanel.add(oip);

        JPanel onp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        onp.add(new JLabel("Numero:"));
        oNumber = new JLabel("");
        onp.add(oNumber);
        oPanel.add(onp);

        JPanel oyp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        oyp.add(new JLabel("Año:"));
        oYear = new JLabel("");
        oyp.add(oYear);
        oPanel.add(oyp);

        JPanel opp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        opp.add(new JLabel("Monto:"));
        oPrice = new JLabel("");
        opp.add(oPrice);
        oPanel.add(opp);

        JPanel odp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        odp.add(new JLabel("Fecha:"));
        oDate = new JLabel("");
        odp.add(oDate);
        oPanel.add(odp);


        cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        cPanel.setBorder(new TitledBorder("Cliente"));

        //cID, cName, cPhone, cAddress, cRun, cDate;
        JPanel cip = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cip.add(new JLabel("ID:"));
        cID = new JLabel("");
        cip.add(cID);
        cPanel.add(cip);

        JPanel cnp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cnp.add(new JLabel("Nombre:"));
        cName = new JLabel("");
        cnp.add(cName);
        cPanel.add(cnp);

        JPanel crp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        crp.add(new JLabel("Run:"));
        cRun = new JLabel("");
        crp.add(cRun);
        cPanel.add(crp);

        JPanel cap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cap.add(new JLabel("Direccion:"));
        cAddress = new JLabel("");
        cap.add(cAddress);
        cPanel.add(cap);

        JPanel cpp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cpp.add(new JLabel("Telefono:"));
        cPhone = new JLabel("");
        cpp.add(cPhone);
        cPanel.add(cpp);

        JPanel cdp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cdp.add(new JLabel("Fecha:"));
        cDate = new JLabel("");
        cdp.add(cDate);
        cPanel.add(cdp);


        side.add(oPanel);
        side.add(cPanel);

        add(side, BorderLayout.SOUTH);

        table = new ProductItemTable(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
                System.out.println("Indice seleccionado: "+ index);
                if(index >= 0){
                    ProductoCompra p = new ProductoCompra(productos.get(index).toVector());
                    int idOrden = p.getId_compra();
                    System.out.println("Orden ID: "+ idOrden);
                    try {
                        OrderData oData = orderController.execute(idOrden);
                        Orden o = oData.getOrden();
                        Cliente c = oData.getCliente();
                        //oID, oNumber, oYear, oPrice, oDate, cID, cName, cPhone, cAddress, cRun, cDate

                        oID.setText(String.valueOf(o.getId()));
                        oNumber.setText(String.valueOf(o.getNumero()));
                        oYear.setText(String.valueOf(o.getYear()));
                        oPrice.setText(String.valueOf(o.getMonto()));
                        oDate.setText(String.valueOf(o.getFecha_compra()));

                        cID.setText(String.valueOf(c.getId()));
                        cName.setText(String.valueOf(c.getNombre()));
                        cPhone.setText(String.valueOf(c.getTelefono()));
                        cAddress.setText(String.valueOf(c.getDireccion()));
                        cRun.setText(String.valueOf(c.getRun()));
                        cDate.setText(String.valueOf(c.getFecha()));

                    } catch (SQLException sql) {
                        System.out.println("Error: "+ sql.getMessage());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(parent, "No se encontro la orden de procedencia.");
                    }
                }
            }
        });

        top = new JPanel(new FlowLayout(FlowLayout.CENTER));

        buscar = table.getFilterField();
        top.add(new JLabel("Buscar"));
        top.add(buscar);
        JButton refrescar = new JButton("Refrescar");
        refrescar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        top.add(refrescar);
        add(top, BorderLayout.NORTH);
        add(table.getScrollTable(), BorderLayout.CENTER);
        refresh();
    }

    @Override
    protected void refresh() {
        try{
            productos = controller.execute();
            table.inserData(productos);
            buscar = null;
            buscar = table.getFilterField();
            buscar.setText("");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error SQL: "+e.getMessage());
        }
    }
}
