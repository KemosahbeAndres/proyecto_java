package stomas.andres.views;

import stomas.andres.controllers.RegisterController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterView extends JFrame {
    private JButton guardar_btn, cancelar_btn;
    private JPanel main_panel, user_panel, pass_panel, repeat_panel, action_panel;
    private JTextField user_tf, pass_tf, repeat_tf;
    private RegisterController rController;
    private LoginView lView;
    public void setLoginView(LoginView view){
        this.lView = view;
    }
    public RegisterView(RegisterController controller){
        super("Registrar usuario");

        rController = controller;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setLocationByPlatform(true);
        setResizable(false);

        setLayout(new GridBagLayout());

        main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.setBorder(new EmptyBorder(10, 10 ,10, 10));

        user_panel = new JPanel();
        user_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        user_panel.setBorder(new EmptyBorder(10,0,10,0));

        user_tf = new JTextField(12);
        user_panel.add(new JLabel("Usuario:"));
        user_panel.add(user_tf);

        main_panel.add(user_panel);

        pass_panel = new JPanel();
        pass_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pass_panel.setBorder(new EmptyBorder(10,0,10,0));

        pass_tf = new JPasswordField(12);
        pass_panel.add(new JLabel("Contraseña:"));
        pass_panel.add(pass_tf);

        main_panel.add(pass_panel);

        repeat_panel = new JPanel();
        repeat_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        repeat_panel.setBorder(new EmptyBorder(10,0,10,0));

        repeat_tf= new JPasswordField(12);
        repeat_panel.add(new JLabel("Repita contraseña:"));
        repeat_panel.add(repeat_tf);

        main_panel.add(repeat_panel);

        action_panel = new JPanel();
        action_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        action_panel.setBorder(new EmptyBorder(10,0,10,0));

        cancelar_btn = new JButton("Cancelar");
        //cancelar_btn.setBorder(new EmptyBorder(0,10,0,10));
        cancelar_btn.setMargin(new Insets(0,40,0,40));
        cancelar_btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancelando...");
                setVisible(false);
                lView.setVisible(true);
                lView.resetFields();
            }
        });
        guardar_btn = new JButton("Guardar");
        //guardar_btn.setBorder(new EmptyBorder(0,10,0,10));
        guardar_btn.setMargin(new Insets(0,40,0,40));
        guardar_btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Guardando...");
                setVisible(false);
                lView.setVisible(true);
                lView.resetFields();
            }
        });
        action_panel.add(cancelar_btn);
        action_panel.add(guardar_btn);

        main_panel.add(action_panel);

        add(main_panel);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                setVisible(false);
                lView.setVisible(true);
                lView.resetFields();
            }
        });
        //setVisible(true);
    }

}
