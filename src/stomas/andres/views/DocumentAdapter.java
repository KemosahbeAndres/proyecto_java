package stomas.andres.views;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class DocumentAdapter implements DocumentListener {
    @Override
    public void insertUpdate(DocumentEvent e) {
        changed(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changed(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changed(e);
    }
    public void changed(DocumentEvent e){

    }
}
