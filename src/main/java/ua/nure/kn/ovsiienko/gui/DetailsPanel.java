package ua.nure.kn.ovsiienko.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final int ROWS = 2;
    private static final int COLS = 2;
   

    private MainFrame parent;
    private JButton cancelButton;
    private JPanel fieldPanel;
    private JTextField fullNameField;
    private JTextField ageField;

    public DetailsPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        parent.showBrowsePanel();
    }

    public void showUserDetails(User user) {
        fullNameField.setText(user.getFullName());
        ageField.setText(String.valueOf(user.getAge()));
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setName("detailsPanel"); //$NON-NLS-1$
        add(getFieldPanel(), BorderLayout.NORTH);
        add(getCancelButton(), BorderLayout.SOUTH);
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
        	cancelButton = new JButton();
        	cancelButton.setText(Messages.getString("DetailsPanel.close")); //$NON-NLS-1$
        	cancelButton.setName("cancel"); //$NON-NLS-1$
        	cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(ROWS, COLS));
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.full_name"), getFullNameField()); //$NON-NLS-1$
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.age"), getAgeField()); //$NON-NLS-1$
        }
        return fieldPanel;
    }

    private void addLabeledImmutableField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JTextField getFullNameField() {
        if (fullNameField == null) {
            fullNameField = new JTextField();
            fullNameField.setEditable(false);
            fullNameField.setName("fullNameField"); //$NON-NLS-1$
        }
        return fullNameField;
    }

    private JTextField getAgeField() {
        if (ageField == null) {
            ageField = new JTextField();
            ageField.setEditable(false);
            ageField.setName("ageField"); //$NON-NLS-1$
        }
        return ageField;
    }
}
