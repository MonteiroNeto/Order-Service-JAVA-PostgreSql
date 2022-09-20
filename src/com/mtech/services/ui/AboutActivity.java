package com.mtech.services.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mtech.services.values.MyStrings;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AboutActivity extends JFrame {
	private Window activity;
	private MyStrings mStrings = new MyStrings();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutActivity frame = new AboutActivity();
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
	public AboutActivity() {
		setResizable(false);
	
		
		initComponents();
			}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(609, 398);
		setTitle(mStrings.ABOUT);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JLabel lblIconAboutGNU = new JLabel("");
		lblIconAboutGNU.setBounds(397, 48, 175, 277);
		lblIconAboutGNU.setIcon(
				new javax.swing.ImageIcon(getClass().getResource(mStrings.APP_PACKAGE + "icon/ic_gnu.png")));
		getContentPane().add(lblIconAboutGNU);
		
		JLabel tvTitle = new JLabel("");
		tvTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		tvTitle.setBounds(32, 73, 386, 32);
		tvTitle.setText(mStrings.SERVICE_ORDER_CONTROL);
		getContentPane().add(tvTitle);
		
		
		
		JLabel  tvAutor = new JLabel("");
		tvAutor.setFont(new Font("Fira Mono Medium", Font.ITALIC | Font.BOLD, 18));
		tvAutor.setBounds(32, 156, 286, 32);
		tvAutor.setText(mStrings.DEVELOPED);
		getContentPane().add(tvAutor);
		
		JLabel tvGplLicense = new JLabel("");
		tvGplLicense.setBounds(69, 238, 201, 32);
		tvGplLicense.setFont(new Font("Dialog", Font.BOLD, 18));
		tvGplLicense.setText(mStrings.GPL_LICENSE);
		getContentPane().add(tvGplLicense);

		

		

		
	}
}
