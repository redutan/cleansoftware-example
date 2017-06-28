package pattern.mediator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author myeongju.jung
 */
public class QuickEntryMediator {
    private JTextField itsTextField;
    private JList itsList;

    public QuickEntryMediator(JTextField t, JList l) {
        itsTextField = t;
        itsList = l;

        itsTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textFieldChanged();
            }
        });
    }

    private void textFieldChanged() {
        String prefix = itsTextField.getText();
        if (prefix.length() == 0) {
            itsList.clearSelection();
            return;
        }
        ListModel m = itsList.getModel();
        boolean found = false;
        for (int i = 0; found == false && i < m.getSize(); i++) {
            Object o = m.getElementAt(i);
            String s = o.toString();
            if (s.startsWith(prefix)) {
                itsList.setSelectedValue(o, true);
                found = true;
            }
        }
        if (!found) {
            itsList.clearSelection();
        }
    }
}
