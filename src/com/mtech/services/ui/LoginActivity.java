package com.mtech.services.ui;

import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mtech.services.dao.Service;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.LoginViewModel;

public class LoginActivity extends JFrame {
	private JTextField edtUser;
	private JPasswordField edtPassword;
	private JLabel tvStatus;
	private JLabel tvPassword;
	private JLabel tvUser;
	private JButton btnLogin;
	private MyStrings mStrings = new MyStrings();
	private Window activity = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginActivity frame = new LoginActivity();
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
	public LoginActivity() {
		initComponents();
		initChangeIconDatabaseConnection();
		onClickLogin();

	}

	private void onClickLogin() {
		btnLogin.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {

				validateTextField();

			}

		});

	}

	private void validateTextField() {
		String pass = new String(edtPassword.getPassword());

		if (edtUser.getText().isBlank()) {
			JOptionPane.showConfirmDialog(null, mStrings.LOGIN_FIELD_IS_EMPTY, mStrings.ERROR,
					JOptionPane.CLOSED_OPTION);
		} else if (pass.isBlank()) {
			JOptionPane.showConfirmDialog(null, mStrings.PASSWORD_FIELD_IS_EMPTY, mStrings.ERROR,
					JOptionPane.CLOSED_OPTION);
		} else {
			new LoginViewModel(activity).login(edtUser.getText(), pass);
		}

	}

	private void initChangeIconDatabaseConnection() {
		Connection con = new Service().conector();
		if (con != null) {
			tvStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource(mStrings.APP_PACKAGE + "icon/dbok.png")));
		} else {
			tvStatus.setIcon(
					new javax.swing.ImageIcon(getClass().getResource(mStrings.APP_PACKAGE + "icon/dberror.png")));
		}

	}

	private void initComponents() {
		setResizable(false);
		setSize(500, 200);
		this.setLocationRelativeTo(null);
		setTitle(mStrings.LOGIN);
		getContentPane().setLayout(null);

		btnLogin = new JButton(mStrings.LOGIN);
		btnLogin.setBounds(195, 111, 207, 25);
		getContentPane().add(btnLogin);

		tvUser = new JLabel(mStrings.USER);
		tvUser.setBounds(12, 33, 70, 15);
		getContentPane().add(tvUser);

		tvPassword = new JLabel(mStrings.PASSWORD);
		tvPassword.setBounds(12, 81, 70, 15);
		getContentPane().add(tvPassword);

		edtUser = new JTextField();
		edtUser.setBounds(115, 31, 361, 19);
		getContentPane().add(edtUser);
		edtUser.setColumns(10);

		edtPassword = new JPasswordField();
		edtPassword.setColumns(10);
		edtPassword.setBounds(115, 79, 361, 19);
		getContentPane().add(edtPassword);

		tvStatus = new JLabel("");
		tvStatus.setBounds(12, 92, 129, 66);
		getContentPane().add(tvStatus);

	}
}
