package ua.nure.kn.ovsiienko.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.ovsiienko.db.DatabaseException;
import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.util.Messages;

public class EditPanel extends JPanel implements ActionListener {
	  
	    private static final int WIDTH = 800;
	    private static final int HEIGHT = 600;
	    
	    private final MainFrame parent;

	    private JPanel fieldPanel;
	    private JPanel buttonPanel;

	    private JTextField firstNameField;
	    private JTextField dateOfBirthField;
	    private JTextField lastNameField;

	    private JButton submitButton;
	    private JButton cancelButton;
		private User user;

	    EditPanel(MainFrame frame) {
	        this.parent = frame;
	        user = parent.getSelectedUser();
	        initialize();
	    }

	    private void initialize() {
	        this.setName("editPanel");
	        this.setSize(WIDTH, HEIGHT);
	        this.setLayout(new BorderLayout());
	        this.add(getFieldPanel(), BorderLayout.NORTH);
	        this.add(getButtonPanel(), BorderLayout.SOUTH);
	    }

	    private JPanel getButtonPanel() {
	        if (buttonPanel == null) {
	            buttonPanel = new JPanel();
	            buttonPanel.add(getOkButton(), null);
	            buttonPanel.add(getCancelButton(), null);
	        }
	        return buttonPanel;
	    }

	    private JButton getCancelButton() {
	        if (cancelButton == null) {
	            cancelButton = new JButton();
	            cancelButton.setName("cancelButton");
	            cancelButton.setText(Messages.getString(Messages.getString("EditPanel.cancel_text")));  //$NON-NLS-1$
	            cancelButton.setActionCommand("cancel");
	            cancelButton.addActionListener(this);
	        }
	        return cancelButton;
	    }

	    private JButton getOkButton() {
	        if (submitButton == null) {
	            submitButton = new JButton();
	            submitButton.setName("okButton");
	            submitButton.setText(Messages.getString(Messages.getString("EditPanel.ok_text"))); //$NON-NLS-1$
	            submitButton.setActionCommand("ok");
	            submitButton.addActionListener(this);
	        }
	        return submitButton;
	    }

	    private JPanel getFieldPanel() {
	        if (fieldPanel == null) {
	            fieldPanel = new JPanel();
	            fieldPanel.setLayout(new GridLayout(3, 2));
	            addLabelField(fieldPanel,Messages.getString("EditPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
	            addLabelField(fieldPanel,Messages.getString("EditPanel.last_name") , getLastNameField()); //$NON-NLS-1$
	            addLabelField(fieldPanel, Messages.getString("EditPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
	        }
	        return fieldPanel;
	    }

	    private JTextField getLastNameField() {
	        if (lastNameField == null) {
	            lastNameField = new JTextField();
	            lastNameField.setName("lastNameField");
	            lastNameField.setText(user.getLastName());
	        }
	        return lastNameField;
	    }

	    private JTextField getDateOfBirthField() {
	        if (dateOfBirthField == null) {
	            dateOfBirthField = new JTextField();
	            dateOfBirthField.setName("dateOfBirthField");
	            dateOfBirthField.setText(user.getDateOfBirth().toString());
	        }
	        return dateOfBirthField;
	    }

	    private JTextField getFirstNameField() {
	        if (firstNameField == null) {
	            firstNameField = new JTextField();
	            firstNameField.setName("firstNameField");
	            firstNameField.setText(user.getFirstName());
	        }
	        return firstNameField;
	    }

	    private void addLabelField(JPanel panel, String name, JTextField field) {
	    	JLabel label = new JLabel(name);
	        label.setLabelFor(field);
	        panel.add(label);
	        panel.add(field);
	    }

	    public void actionPerformed(ActionEvent e) {
	    	String command = e.getActionCommand();
	        if ("ok".equalsIgnoreCase(e.getActionCommand())) {
	        	String firstName = getFirstNameField().getText();
	        	String lastName = getLastNameField().getText();
	        	String dateOfBirth = getDateOfBirthField().getText();
	        	
	        	if(!firstName.isEmpty()&&!lastName.isEmpty()&&!dateOfBirth.isEmpty()){
	        		user.setFirstName(firstName);
	        		user.setLastName(lastName);
	        		DateFormat format = DateFormat.getDateInstance();
	        		try{
	        			Date date = format.parse(getDateOfBirthField().getText());
	        			user.setDateOfBirth(date);
	        		}catch(ParseException e1){
	        			JOptionPane.showMessageDialog(this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	        		}
	        		
	        		try{
	        			parent.getDao().update(user);
	        		}catch(DatabaseException e1){
	        			JOptionPane.showMessageDialog(this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	        		}
	        		
	        		clearFields();
	        		this.setVisible(false);
	        		parent.showBrowsePanel();
	        	}else{
	        		JOptionPane.showMessageDialog(this, Messages.getString("EditPanel.fill_fields"),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
	    
	        	}
	        	
	        } else if ("cancel".equalsIgnoreCase(e.getActionCommand())) {
	            clearFields();
	            this.setVisible(false);
	            parent.showBrowsePanel();
	        }
	    }

	    private void clearFields() {
	        Color bgColor = Color.WHITE;

	        getFirstNameField().setText(""); //$NON-NLS-1$
	        getFirstNameField().setBackground(bgColor);

	        getLastNameField().setText(""); //$NON-NLS-1$
	        getLastNameField().setBackground(bgColor);

	        getDateOfBirthField().setText(""); //$NON-NLS-1$
	        getDateOfBirthField().setBackground(bgColor);
	    }
	    
}
