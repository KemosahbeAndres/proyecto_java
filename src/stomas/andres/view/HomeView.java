package stomas.andres.view;

import stomas.andres.controller.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    private LogoutController controller;
    public HomeView(){
        super("Bienvenido");
        setBounds(200,200,600,400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Saliendo");
                controller.execute();
            }
        });

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem item = new JMenuItem("Cerrar Sesion");
        item.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo...");
                setVisible(false);
                controller.execute();
            }
        });
        menu.add(new JMenuItem("Abrir"));
        menu.addSeparator();
        menu.add(item);
        bar.add(menu);
        add(bar, BorderLayout.NORTH);
        JPanel main = new JPanel();
        main.setLayout(new FlowLayout());
        main.add(new JLabel("HOME"));

        add(main, BorderLayout.CENTER);

        //setVisible(true);
    }

    public void setController(LogoutController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        new HomeView();
    }

}
