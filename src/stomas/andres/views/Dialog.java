package stomas.andres.views;

import javax.swing.*;
import java.awt.*;

public abstract class Dialog extends JDialog {
    public Dialog(){
        this(null, "");
    }
    public Dialog(String title){
        this(null, title);
    }
    public Dialog(Frame parent, String title){
        super(parent, title);
    }

}
