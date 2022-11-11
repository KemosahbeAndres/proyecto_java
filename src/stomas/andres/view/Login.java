package stomas.andres.view;

import stomas.andres.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Login extends JFrame {
    private BoxLayout layout;
    private JPanel userpanel, passpanel, actionpanel;
    private JTextField userField, passwordField;
    private JButton loginBtn, exitBtn;
    private Dimension dimension;
    private LoginController controller;
    public Login(LoginController loginController){
        super("Iniciar sesion");
        this.controller = loginController;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setAlwaysOnTop(true);
        setBounds(200,200,300,150);

        layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);

        dimension = new Dimension(200,50);

        add(new JLabel("Iniciar sesion"));

        userpanel = new JPanel();
        userpanel.setLayout(new FlowLayout());
        userpanel.setMaximumSize(dimension);

        userpanel.add(new JLabel("Usuario:"));
        userField = new JTextField(10);
        userpanel.add(userField);

        add(userpanel);

        passpanel = new JPanel();
        passpanel.setLayout(new FlowLayout());
        passpanel.setMaximumSize(dimension);

        passpanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField(10);
        passpanel.add(passwordField);

        add(passpanel);

        actionpanel = new JPanel();
        actionpanel.setLayout(new FlowLayout());
        actionpanel.setMaximumSize(dimension);

        loginBtn = new JButton("Entrar");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.execute(userField.getText(), passwordField.getText())){
                    System.out.println("Exito al iniciar sesion.");
                }
            }
        });
        exitBtn = new JButton("Salir");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        actionpanel.add(exitBtn);
        actionpanel.add(loginBtn);

        add(actionpanel);

        userField.requestFocus();
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("Width: "+e.getComponent().getWidth()+ " Height: " +e.getComponent().getHeight());
            }
        });
    }

    public static void main(String[] args) {
        new Login(new LoginController());
    }
}
