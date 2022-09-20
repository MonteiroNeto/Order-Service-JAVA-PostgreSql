package com.mtech.services.ui.fragments;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import com.mtech.services.model.User;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.UserFragmentViewModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

public class UsersFragment extends JInternalFrame {

	private UsersFragment activity = this;
	private MyStrings mStrings = new MyStrings();
	private JTextField edtId, edtPass, edtName, edtPhone, edtLogin;
	private JButton btnAdd, btnFind, btnEdit, btnRemove;
	private User user = null;
	private JComboBox comboBoxPerfil;
	private JTable table;
	private JScrollPane panelTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersFragment frame = new UsersFragment();
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
	public UsersFragment() {
		initComponents();
		onClickFindUser();
		onClickInsertUser();
		onClickUpdateUser();
		onClickRemoveUser();
		onClikedtNameRelease();
		onCLickItemTable();

	}

	
	//CLick no item da tabela
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

				//Pegar posicao da linha na tabela
				int position = table.getSelectedRow();
				//pegar o id de acordo com a posicao da linha na tabela
				int idUser = (Integer) table.getModel().getValueAt(position, 0);
				//buscar usuario pelo id
				user = new UserFragmentViewModel().findUserForId(idUser);

				//enviar os dados do usuario para os edit TExt
				setTextToEditText(user);

			}
		});

	}
	
	
	//Pesquisar usuario pelo nome de acordo com que for digitando
	private void onClikedtNameRelease() {
		edtName.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent arg0) {
				ResultSet resultSet = new UserFragmentViewModel().findUsersForName(edtName.getText().toString());

				table.setModel(DbUtils.resultSetToTableModel(resultSet));

			}

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void onClickRemoveUser() {
		btnRemove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				removeUser();

			}
		});

	}

	protected void removeUser() {

		int idUser = validateIdUser();

		if (idUser > 0) {
			new UserFragmentViewModel().removeUser(idUser);
		}

	}

	
	//Validar ID se o caracter é um INTEIRO
	private int validateIdUser() {

		try {

			if (edtId.getText().isBlank()) {
				JOptionPane.showConfirmDialog(null, mStrings.ID_FIELD_IS_EMPTY, mStrings.ERROR,
						JOptionPane.CLOSED_OPTION);
				return 0;
			} else {
				int idUser = Integer.parseInt(edtId.getText());
				return idUser;
			}

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, mStrings.INVALID_ID + "\n" + e, mStrings.ERROR,
					JOptionPane.CLOSED_OPTION);
			return 0;
		}

	}

	private void onClickUpdateUser() {
		btnEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				updateUser();
			}

		});

	}

	private void updateUser() {
		int idUser = validateIdUser();

		String messenge = validateEmptyField();
		int iduser = Integer.parseInt(edtId.getText());
		if (messenge.equals("") || idUser > 0) {
			User user = new User(iduser, edtName.getText(), edtPhone.getText(), edtLogin.getText(), edtPass.getText(),
					comboBoxPerfil.getSelectedItem().toString());
			Boolean success = new UserFragmentViewModel().updateUser(user);
			if (success) {
				clearForm();
			}
		} else {
			JOptionPane.showConfirmDialog(null, messenge, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}
	}

	private void onClickInsertUser() {
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				insertUser();
			}

		});

	}

	private void insertUser() {

		String messenge = validateEmptyField();

		// INSERT USER
		if (messenge.equals("")) {
			User user = new User(1, edtName.getText(), edtPhone.getText(), edtLogin.getText(), edtPass.getText(),
					comboBoxPerfil.getSelectedItem().toString());
			Boolean success = new UserFragmentViewModel().addUser(user);
			if (!success) {
				clearForm();
			}
		} else {// SHOW MENSEGER FIELD EMPTY
			JOptionPane.showConfirmDialog(null, messenge, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}

	}
	
	
	//validar se os edit text estão vazios
	private String validateEmptyField() {
		String messenger = "";

		if (edtName.getText().isBlank()) {
			return messenger = mStrings.NAME_FIELD_IS_EMPTY;
		} else if (edtPhone.getText().isBlank()) {
			return messenger = mStrings.PHONE_FIELD_IS_EMPTY;
		} else if (edtLogin.getText().isBlank()) {
			return messenger = mStrings.LOGIN_FIELD_IS_EMPTY;
		} else if (edtPass.getText().isBlank()) {
			return messenger = mStrings.PASSWORD_FIELD_IS_EMPTY;
		} else {
			return "";
		}

	}

	private void onClickFindUser() {
		btnFind.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				findUser();

			}

		});

	}

	private void findUser() {
		int idUser = validateIdUser();

		if (idUser > 0) {
			int iduser = Integer.parseInt(edtId.getText());
			user = new UserFragmentViewModel().findUserForId(iduser);
			setTextToEditText(user);
		}

	}

	
	//Setar os dados do usuario nos campos do formulario
	private void setTextToEditText(User user) {
		String[] perfis = mStrings.LIST_PERFIL;
		if (user != null) {
			edtId.setText(String.valueOf(user.getIduser()));
			edtName.setText(user.getUsuario());
			edtPhone.setText(user.getFone());
			edtLogin.setText(user.getLogin());
			edtPass.setText(user.getSenha());

			for (int i = 0; i < perfis.length; i++) {
				if (user.getPerfil().equals(perfis[i])) {
					comboBoxPerfil.setSelectedIndex(i);

				} else {

				}

			}

		} else {
			clearForm();

		}

	}

	private void clearForm() {
		edtId.setText("");
		edtName.setText("");
		edtPhone.setText("");
		edtLogin.setText("");
		edtPass.setText("");
		comboBoxPerfil.setSelectedIndex(0);
	}

	private void initComponents() {
		setBounds(0, 0, 1240, 805);
		setTitle(mStrings.USER);
		setResizable(false);
		setIconifiable(true);// botao de minimizar
		setClosable(true);// botao de fechar
		setMaximizable(true);
		getContentPane().setLayout(null);

		JLabel tvId = new JLabel(mStrings.ID);
		tvId.setFont(new Font("Serif", Font.BOLD, 18));
		tvId.setBounds(32, 100, 70, 15);
		getContentPane().add(tvId);

		edtId = new JTextField();
		edtId.setBounds(64, 90, 80, 32);
		getContentPane().add(edtId);
		edtId.setColumns(10);

		JLabel tvName = new JLabel(mStrings.NAME);
		tvName.setBounds(32, 200, 70, 15);
		tvName.setFont(new Font("Serif", Font.BOLD, 18));
		getContentPane().add(tvName);

		edtName = new JTextField();
		edtName.setBounds(100, 190, 1023, 32);
		getContentPane().add(edtName);
		edtName.setColumns(10);

		JLabel tvPhone = new JLabel(mStrings.PHONE);
		tvPhone.setBounds(32, 300, 70, 15);
		tvPhone.setFont(new Font("Serif", Font.BOLD, 18));
		getContentPane().add(tvPhone);

		edtPhone = new JTextField();
		edtPhone.setBounds(100, 290, 388, 32);
		getContentPane().add(edtPhone);
		edtPhone.setColumns(10);

		JLabel tvPerfil = new JLabel(mStrings.PERFIL);
		tvPerfil.setBounds(600, 300, 70, 15);
		tvPerfil.setFont(new Font("Serif", Font.BOLD, 18));
		getContentPane().add(tvPerfil);

		comboBoxPerfil = new JComboBox();
		comboBoxPerfil.setBounds(690, 290, 388, 32);
		comboBoxPerfil.addItem(mStrings.USER);
		comboBoxPerfil.addItem(mStrings.ADMIN);
		getContentPane().add(comboBoxPerfil);

		JLabel tvLogin = new JLabel(mStrings.LOGIN);
		tvLogin.setBounds(32, 400, 70, 33);
		tvLogin.setFont(new Font("Serif", Font.BOLD, 18));
		getContentPane().add(tvLogin);

		edtLogin = new JTextField();
		edtLogin.setBounds(100, 400, 388, 32);
		getContentPane().add(edtLogin);
		edtLogin.setColumns(10);

		JLabel tvPass = new JLabel(mStrings.PASSWORD);
		tvPass.setBounds(600, 400, 175, 33);
		tvPass.setFont(new Font("Serif", Font.BOLD, 18));
		getContentPane().add(tvPass);

		edtPass = new JTextField();
		edtPass.setColumns(10);
		edtPass.setBounds(710, 400, 388, 32);
		getContentPane().add(edtPass);

		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_add.png")));
		btnAdd.setToolTipText(mStrings.ADD);
		btnAdd.setBounds(182, 524, 80, 80);
		getContentPane().add(btnAdd);

		btnFind = new JButton("");
		btnFind.setBounds(444, 524, 80, 80);
		btnFind.setToolTipText(mStrings.FIND);
		btnFind.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_find.png")));
		getContentPane().add(btnFind);

		btnEdit = new JButton("");
		btnEdit.setBounds(706, 524, 80, 80);
		btnEdit.setToolTipText(mStrings.EDIT);
		btnEdit.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_edit.png")));
		getContentPane().add(btnEdit);

		btnRemove = new JButton("");
		btnRemove.setBounds(968, 524, 80, 80);
		btnRemove.setToolTipText(mStrings.REMOVE);
		btnRemove.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_remove.png")));
		getContentPane().add(btnRemove);

		initTableUser();

	}

	private void initTableUser() {
		String title[] = { mStrings.ID, mStrings.NAME, mStrings.PHONE, mStrings.LOGIN, mStrings.PERFIL };
		String body[][] = {};

		table = new JTable(body, title);
		table.setBounds(5, 17, 1009, 129);

		panelTable = new JScrollPane(table);
		panelTable.setBorder(
				new TitledBorder(null, mStrings.USER + "s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.setBounds(177, 11, 1023, 160);
		getContentPane().add(panelTable);
		panelTable.setLayout(new ScrollPaneLayout());

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UsersFragment.class.getResource("/com/mtech/services/icon/ic_find2.png")));
		lblNewLabel.setBounds(1141, 178, 70, 55);
		getContentPane().add(lblNewLabel);

	}
}
