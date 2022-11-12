package stomas.andres.view;

import stomas.andres.App;
import stomas.andres.controller.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.SwingConstants.CENTER;

public class LoginView extends JFrame {

    public static boolean flag = false;
    private BoxLayout layout;
    private JPanel mainpanel, userpanel, passpanel, actionpanel;
    private JTextField userField, passwordField;
    private JButton loginBtn, registerBtn;
    private Dimension dimension;
    private App controller;
    private static final Font tFont = new Font("Dialog", Font.BOLD, 20);
    public LoginView(App parent){
        super("Iniciar sesion");
        this.controller = parent;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setBounds(200,200,400,280);

        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        mainpanel = new JPanel();
        layout = new BoxLayout(mainpanel, BoxLayout.Y_AXIS);
        mainpanel.setLayout(layout);

        center.add(mainpanel);

        //JSeparator top = new JSeparator(HORIZONTAL);

        //mainpanel.add(top);

        dimension = new Dimension(400,50);

        JLabel title = new JLabel("Iniciar sesion", CENTER);

        title.setFont(tFont);
        title.setBorder(new EmptyBorder(25,0,10,0));

        add(title, BorderLayout.NORTH);

        userpanel = new JPanel();
        userpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 8));
        userpanel.setMaximumSize(dimension);

        userpanel.add(new JLabel("Usuario:"));
        userField = new JTextField(10);
        userpanel.add(userField);

        mainpanel.add(userpanel);

        passpanel = new JPanel();
        passpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 8));
        passpanel.setMaximumSize(dimension);

        passpanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField(10);
        passpanel.add(passwordField);

        mainpanel.add(passpanel);
        //mainpanel.add(new JSeparator(HORIZONTAL));

        actionpanel = new JPanel();
        actionpanel.setLayout(new FlowLayout());
        //actionpanel.setMaximumSize(dimension);

        loginBtn = new JButton("Entrar");
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.tryLogin(userField.getText(), passwordField.getText());
            }
        });
        registerBtn = new JButton("Salir");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        actionpanel.add(registerBtn);
        actionpanel.add(loginBtn);
        actionpanel.setBorder(new EmptyBorder(15,0,25,0));

        //mainpanel.add(actionpanel);

        add(center, BorderLayout.CENTER);
        add(actionpanel, BorderLayout.SOUTH);

        userField.requestFocus();
        //setVisible(true);
        /*
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("Width: "+e.getComponent().getWidth()+ " Height: " +e.getComponent().getHeight());
            }
        });*/
    }
}
