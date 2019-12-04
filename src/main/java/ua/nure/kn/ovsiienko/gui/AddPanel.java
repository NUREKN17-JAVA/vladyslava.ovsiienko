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

public class AddPanel extends JPanel implements ActionListener {
	
	private static final int COLS = 2;
	private static final int ROWS = 3;
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor ;

	public AddPanel(MainFrame parent){
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(),BorderLayout.NORTH);
		this.add(getButtonPanel(),BorderLayout.SOUTH);
		
	}

	private JPanel getButtonPanel() {
		if (buttonPanel==null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(),null);
			buttonPanel.add(getCancelButton(),null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null){
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(ROWS,COLS));
			addLabeledField(fieldPanel,Messages.getString("AddPanel.first_name"),getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.last_name"),getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.date_of_birth"),getDateOfBirthField()); //$NON-NLS-1$
			
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null){
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if(lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText,
			JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JTextField getFirstNameField() {
		if(firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); //$NON-NLS-1$
		}
		return firstNameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("ok".equalsIgnoreCase(e.getActionCommand())){ //$NON-NLS-1$
			User user = new User();
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format= DateFormat.getDateInstance();
			try {
				Date date = format.parse(getDateOfBirthField().getText());
				user.setDateOfBirth(date);
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				parent.getDao().create(user);
			} catch (DatabaseException el) {
				JOptionPane.showMessageDialog(this,el.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
		
	}

	private void clearFields() {
		getFirstNameField().setText(""); //$NON-NLS-1$
		getFirstNameField().setBackground(bgColor );
		
		getLastNameField().setText(""); //$NON-NLS-1$
		getLastNameField().setBackground(bgColor );
		
		getDateOfBirthField().setText(""); //$NON-NLS-1$
		getDateOfBirthField().setBackground(bgColor );
		
	}
}
