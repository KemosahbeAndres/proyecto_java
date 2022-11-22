package stomas.andres.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterView extends JFrame {
    private JButton guardar, cancelar;
    private JPanel main_panel, user_panel, pass_panel, repeat_panel, action_panel;
    public RegisterView(){
        super("Registrar usuario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 300);
        setResizable(false);

        setLayout(new GridBagLayout());

        main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.setBorder(new EmptyBorder(10, 10 ,10, 10));


        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterView();
    }
}
