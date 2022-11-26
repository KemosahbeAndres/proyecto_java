package stomas.andres.view;

import stomas.andres.controller.LogoutController;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView extends JFrame {
    private LogoutController controller;
    public HomeView(){
        super("Bienvenido");
        setSize(600,400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Saliendo");
                controller.execute();
            }
        });

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Cerrar Sesion");
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("Saliendo...");
                setVisible(false);
                controller.execute();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
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


}
