package stomas.andres.views;

import stomas.andres.controllers.LoginController;
import stomas.andres.controllers.RegisterController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import static javax.swing.SwingConstants.CENTER;

public class LoginView extends Dialog {
    private BoxLayout layout;
    private JPanel mainpanel, userpanel, passpanel, actionpanel;
    private JTextField userField, passwordField;
    private JButton loginBtn, registerBtn;
    private Dimension dimension;
    private LoginController controller;
    private RegisterView registerView;

    public void resetFields(){
        userField.setText("");
        passwordField.setText("");
    }
    public boolean doLogin(){
        return false;
    }
    private static final Font tFont = new Font("Dialog", Font.BOLD, 20);
    public LoginView(View parent, LoginController controller, RegisterController registerController){
        super(parent, "Iniciar sesion");
        registerView = new RegisterView(parent, registerController);
        this.controller = controller;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400,280);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

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

        passpanel.add(new JLabel("Contrase??a:"));
        passwordField = new JPasswordField(10);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginBtn.doClick();
                }
            }
        });
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
                String user = userField.getText().trim();
                String pass = passwordField.getText().trim();
                try {
                    if(hasNumber(user)) throw new Exception("El usuario no puede tener numeros.");
                    if(user.isBlank() || pass.isBlank()) throw new Exception("Hay campos vacios.");
                    if(user.length() > 255) throw new Exception("Excede el maximo de caracteres");
                    if(pass.length() > 255) throw new Exception("Excede el maximo de caracteres");
                    if(controller.execute(user, pass)){
                        ((HomeView) parent).renderContent();
                        setVisible(false);
                    }else{
                        throw new Exception("Intenta de nuevo, el usuario y contrase??a no coinciden.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parent, "Error de comunicacion con base de datos. No se puede conectar a la Base de Datos.");
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(parent, "Error: "+ exception.getMessage());
                    refresh();
                }

            }
        });
        registerBtn = new JButton("Registrar");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Intento de registrar");
                registerView.setVisible(true);
            }
        });


        actionpanel.add(loginBtn);
        actionpanel.add(registerBtn);
        actionpanel.setBorder(new EmptyBorder(10,0,25,0));

        //mainpanel.add(actionpanel);

        add(center, BorderLayout.CENTER);
        add(actionpanel, BorderLayout.SOUTH);

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
    private boolean hasNumber(String text){
        char[] array = text.trim().toCharArray();
        boolean flag = true;
        for(char c: array){
            try{
                int n = Integer.parseInt(String.valueOf(c));
                flag = true;
            }catch (Exception e){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    protected void refresh() {
        userField.setText("");
        userField.requestFocus();
        passwordField.setText("");
    }
}
