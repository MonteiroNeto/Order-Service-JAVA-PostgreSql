package com.mtech.services.ui.fragments;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.TitledBorder;

import com.mtech.services.model.Client;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.ClientsFragmentViewModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ClientFragment extends JInternalFrame {

	private MyStrings mStrings = new MyStrings();
	private JTable table;
	private JScrollPane panelTable;
	private JTextField edtTextFindClient;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField edtTextName;
	private JTextField edtTextAdress;
	private JTextField edtTextPhone;
	private JTextField edtTextEmail;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private int idCli = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFragment frame = new ClientFragment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientFragment() {
		initComponent();
		onClickAddClient();
		onCLickEditClient();
		onClikedtNameRelease();
		onCLickItemTable();
		onCLickRemoveClient();

	}

	private void onCLickRemoveClient() {
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeClient();
			}
		});

	}

	protected void removeClient() {
		if (idCli > 0) {

			Boolean deleted = new ClientsFragmentViewModel().removeClient(idCli);
			clearEditText(deleted);
			ResultSet resultSet = new ClientsFragmentViewModel().findClientForName("");
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.SELECT_A_CLIENT, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}

	}

	private void onCLickItemTable() {
		table.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent arg0) {
				int linePosition = table.getSelectedRow();

				// converter o valor recebido da tabela para inteiro
				idCli = Integer.parseInt(table.getModel().getValueAt(linePosition, 0).toString());

				edtTextName.setText(table.getModel().getValueAt(linePosition, 1).toString());
				edtTextAdress.setText(table.getModel().getValueAt(linePosition, 2).toString());
				edtTextPhone.setText(table.getModel().getValueAt(linePosition, 3).toString());
				edtTextEmail.setText(table.getModel().getValueAt(linePosition, 4).toString());

			}
		});

	}

	private void onClikedtNameRelease() {
		edtTextFindClient.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent arg0) {
				ResultSet resultSet = new ClientsFragmentViewModel().findClientForName(edtTextFindClient.getText());
				table.setModel(DbUtils.resultSetToTableModel(resultSet));

			}

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void onCLickEditClient() {
		btnEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EditClient();

			}
		});

	}

	private void EditClient() {
		String message = "";
		if (idCli > 0) {
			message = validateTextField();
			if (message.equals("")) {
				Boolean updated = new ClientsFragmentViewModel().editClient(getClientForEditText());

				clearEditText(updated);
				if (updated) {
					ResultSet resultSet = new ClientsFragmentViewModel().findClientForName("");
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
				}

			} else {
				JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			}

		} else {
			JOptionPane.showConfirmDialog(null, mStrings.SELECT_A_CLIENT, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}

	}

	private void onClickAddClient() {

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				addClient();

			}

		});

	}

	private void addClient() {
		String message = "";
		message = validateTextField();
		if (message.equals("")) {
			Boolean added = new ClientsFragmentViewModel().addClient(getClientForEditText());
			clearEditText(added);
		} else {
			JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}

	}

	// limpar os campos de textos
	private void clearEditText(Boolean ok) {
		if (ok) {
			idCli = 0;
			edtTextName.setText("");
			edtTextAdress.setText("");
			edtTextPhone.setText("");
			edtTextEmail.setText("");
		}

	}

	// Obter um Cliente dos campos de textos
	private Client getClientForEditText() {
		Client client = new Client(idCli, edtTextName.getText(), edtTextAdress.getText(), edtTextPhone.getText(),
				edtTextEmail.getText());

		return client;
	}

	// VERIFICAR CAMPOS DE TEXTOS VAZIOS
	private String validateTextField() {
		String message = "";
		if (edtTextName.getText().isBlank()) {
			message = mStrings.NAME_FIELD_IS_EMPTY;
		} else if (edtTextPhone.getText().isBlank()) {
			message = mStrings.PHONE_FIELD_IS_EMPTY;
		} else if (edtTextEmail.getText().isBlank()) {
			message = mStrings.EMAIL_FIELD_IS_EMPTY;
		} else {
			message = "";
		}

		return message;
	}

	private void initComponent() {
		setBounds(0, 0, 1240, 805);
		setTitle(mStrings.CLIENT);
		setResizable(false);
		setIconifiable(true);// botao de minimizar
		setClosable(true);// botao de fechar
		setMaximizable(true);
		getContentPane().setLayout(null);

		initTableClient();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_find2.png")));
		lblNewLabel.setBounds(48, 16, 60, 60);
		getContentPane().add(lblNewLabel);

		edtTextFindClient = new JTextField();
		edtTextFindClient.setBounds(104, 30, 499, 41);
		getContentPane().add(edtTextFindClient);
		edtTextFindClient.setColumns(10);

		lblNewLabel_1 = new JLabel("*" + mStrings.REQUIRED_FIELDS);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_1.setBounds(1027, 16, 172, 42);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("*" + mStrings.NAME);
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_2.setBounds(69, 348, 70, 15);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel(mStrings.ADRESS);
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_3.setBounds(69, 406, 70, 15);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("*" + mStrings.EMAIL);
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_4.setBounds(69, 522, 70, 15);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("*" + mStrings.PHONE);
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_5.setBounds(69, 464, 95, 15);
		getContentPane().add(lblNewLabel_5);

		edtTextName = new JTextField();
		edtTextName.setColumns(10);
		edtTextName.setBounds(173, 336, 499, 41);
		getContentPane().add(edtTextName);

		edtTextAdress = new JTextField();
		edtTextAdress.setColumns(10);
		edtTextAdress.setBounds(173, 392, 817, 41);
		getContentPane().add(edtTextAdress);

		edtTextPhone = new JTextField();
		edtTextPhone.setColumns(10);
		edtTextPhone.setBounds(173, 452, 381, 41);
		getContentPane().add(edtTextPhone);

		edtTextEmail = new JTextField();
		edtTextEmail.setColumns(10);
		edtTextEmail.setBounds(173, 510, 741, 41);
		getContentPane().add(edtTextEmail);

		btnAdd = new JButton();
		btnAdd.setBounds(247, 615, 80, 80);
		btnAdd.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_add.png")));
		btnAdd.setToolTipText(mStrings.ADD);
		getContentPane().add(btnAdd);

		btnEdit = new JButton();
		btnEdit.setBounds(574, 615, 80, 80);
		btnEdit.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_edit.png")));
		btnEdit.setToolTipText(mStrings.EDIT);
		getContentPane().add(btnEdit);

		btnRemove = new JButton();
		btnRemove.setBounds(901, 615, 80, 80);
		btnRemove.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_remove.png")));
		btnRemove.setToolTipText(mStrings.REMOVE);
		getContentPane().add(btnRemove);
	}

	private void initTableClient() {
		String title[] = { mStrings.ID, mStrings.NAME, mStrings.ADRESS, mStrings.PHONE, mStrings.EMAIL };
		String body[][] = {};

		table = new JTable(body, title);
		table.setBounds(5, 17, 1009, 129);

		panelTable = new JScrollPane(table);
		panelTable.setBorder(
				new TitledBorder(null, mStrings.CLIENT + "s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(32, 100, 1167, 194);
		getContentPane().add(panelTable);
		panelTable.setLayout(new ScrollPaneLayout());

	}
}
