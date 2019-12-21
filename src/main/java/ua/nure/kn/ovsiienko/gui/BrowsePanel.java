package ua.nure.kn.ovsiienko.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn.ovsiienko.db.DatabaseException;
import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
		parent = frame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(),BorderLayout.CENTER);
		this.add(getButtonsPanel(),BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if (buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null);
			buttonPanel.add(getDetailsButton(),null);
		}
		
		return buttonPanel;
	}

	private JButton getDetailsButton() {
		if (detailsButton == null){
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details")); //$NON-NLS-1$
			detailsButton.setName("detailsButton"); //$NON-NLS-1$
			detailsButton.setActionCommand("details"); //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null){
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if (editButton == null){
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); //$NON-NLS-1$
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if(addButton == null){
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); //$NON-NLS-1$
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
			addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane getTablePanel() {
		if(tablePanel == null){
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		if(userTable == null){
			userTable = new JTable();
			userTable.setName("userTable"); //$NON-NLS-1$
			
		}
		return userTable;
	}
	public User getSelectedUser(){
		int selectedRow = getUserTable().getSelectedRow();
		int idColumn = 0;
		
		Long userId = null;
		User user = null;
		if(selectedRow==-1){
			JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.choosing_user2"),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
		userId = (Long) userTable.getValueAt(userTable.getSelectedRow(),idColumn);
		try{
			user = parent.getDao().find(userId);
		}catch(DatabaseException e){
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		}
		return user;
	}
	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		getUserTable().setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if("add".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
		}
		if("edit".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			int selectedRow = userTable.getSelectedRow();
			int selectedColumn = userTable.getSelectedColumn();
			if (selectedColumn !=-1 || selectedRow!=-1){
				this.setVisible(false);
				parent.showEditPanel();
			}else{
				JOptionPane.showMessageDialog(this,Messages.getString("BrowsePanel.choosing_user1"),"Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
		}
        if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
            User selectedUser = getSelectedUser();
            if (selectedUser != null) {
                int result = JOptionPane.showConfirmDialog(this, Messages.getString("BrowsePanel.accept_deleting"), //$NON-NLS-1$
                        "Confirm deleting", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        parent.getDao().delete(selectedUser);
                        getUserTable().setModel(new UserTableModel(parent.getDao().findAll()));
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
                    }
                }
            }
        }
        if ("details".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
        	User user = getSelectedUser();
			this.setVisible(false);
			parent.showDetailsPanel(user);
        }
	}

}
