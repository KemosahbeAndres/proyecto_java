package stomas.andres.views;

import stomas.andres.controllers.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.SwingConstants.CENTER;

public class LoginView extends View {
    private BoxLayout layout;
    private JPanel mainpanel, userpanel, passpanel, actionpanel;
    private JTextField userField, passwordField;
    private JButton loginBtn, registerBtn;
    private Dimension dimension;
    private LoginController controller;
    private RegisterView rView;
    private HomeView hView;

    public void resetFields(){
        userField.setText("");
        passwordField.setText("");
    }
    private static final Font tFont = new Font("Dialog", Font.BOLD, 20);
    public LoginView(LoginController controller, RegisterView registerView, HomeView homeView){
        super("Iniciar sesion");
        this.controller = controller;
        this.rView = registerView;
        rView.setLoginView(this);
        this.hView = homeView;
        hView.setLoginView(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setSize(400,280);
        setLocationByPlatform(true);

        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        mainpanel = new JPanel();
        layout = new BoxLayout(mainpanel, BoxLayout.Y_AXIS);
        mainpanel.setLayout(layout);

        center.add(mainpanel);

        //JSeparator top = new JSeparator(HORIZONTAL);

        //mainpanel.add(top);

        dimension = new Dimension(300,50);

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
                //controller.tryLogin(userField.getText(), passwordField.getText());
                System.out.println("Intento de login");
                setVisible(false);
                hView.setVisible(true);
            }
        });
        registerBtn = new JButton("Registrar");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Intento de registrar");
                setVisible(false);
                rView.setVisible(true);
            }
        });


        actionpanel.add(loginBtn);
        actionpanel.add(registerBtn);
        actionpanel.setBorder(new EmptyBorder(10,0,25,0));

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
