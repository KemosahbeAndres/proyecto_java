package stomas.andres.views;

import javax.swing.*;

public abstract class View extends JFrame {
    public View(String text){
        super(text);
    }
    public View(){
        this("");
    }
    public abstract void refresh();

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        refresh();
    }
}
