package com.mtech.services.ui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.mtech.services.model.User;
import com.mtech.services.ui.fragments.UsersFragment;
import com.mtech.services.util.GenerateReport;
import com.mtech.services.util.GetDate;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.MainViewModel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.time.OffsetDateTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class MainActivity extends JFrame {
	private Window activity = this;
	private MainViewModel mainViewModel = new MainViewModel(activity);
	private MyStrings mString = new MyStrings();
	private JMenuBar menuTopBar;
	private JMenu mnRegistration, mnHelp, mnOption;
	private JMenuItem iMnRegisClient, iMnRegisOs, iMnRepoService, iMnHelpAbout, iMnOptExit;
	private JLabel tvDate;
	public JLabel tvUser;
	private JDesktopPane pane;

	// esta publico para poder alterar a permissao de acordo com o perfil logado
	public static JMenu mnReport;
	public static JMenuItem iMnRegisUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity frame = new MainActivity();
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
	public MainActivity() {

		initComponents();
	}

	private void initComponents() {
		settingActivityMain();
		initMenus();
		setDate();
		onClickExit();
		onClickAbout();
		onClickUsers();
		onCLickClient();
		onClickOs();
		onclickGenerateServiceReport();

	}

	private void onclickGenerateServiceReport() {
		iMnRepoService.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				generateServiceReport();
				
			}
		});
		
	}

	protected void generateServiceReport() {
		new MainViewModel(activity).generateServicesReport();
		
	}

	private void onClickOs() {
		iMnRegisOs.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				mainViewModel.openOsFragment(pane);
				
			}
		});
		
	}

	private void onCLickClient() {
		iMnRegisClient.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				mainViewModel.openClientFragment(pane);
				
			}
		});
		
	}

	private void onClickUsers() {
		// Abrir o fragmento de usuario no painel principal
		iMnRegisUsers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainViewModel.openUserFragment(pane);

			}
		});

	}

	private void onClickAbout() {
		iMnHelpAbout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainViewModel.openAboutActivity();

			}
		});

	}

	private void onClickExit() {
		iMnOptExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainViewModel.closeSystem();

			}
		});

	}

	private void setDate() {

		tvDate.setText(new GetDate().getFormatDate());

	}

	private void settingActivityMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);
		setTitle(mString.MAIN_TITLE);
		setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		pane = new JDesktopPane();
		pane.setBackground(new Color(0, 0, 0));
		pane.setBounds(12, 12, 1240, 805);
		getContentPane().add(pane);

		JLabel lblIconMain = new JLabel("");
		lblIconMain.setBounds(1291, 400, 285, 296);
		lblIconMain.setIcon(
				new javax.swing.ImageIcon(getClass().getResource(mString.APP_PACKAGE + "icon/ic_main_256.png")));
		getContentPane().add(lblIconMain);

		tvUser = new JLabel("User");
		tvUser.setFont(new Font("Dialog", Font.BOLD, 18));
		tvUser.setBounds(1291, 77, 270, 76);
		getContentPane().add(tvUser);

		tvDate = new JLabel("Date");
		tvDate.setFont(new Font("Fira Mono Medium", Font.ITALIC | Font.BOLD, 18));
		tvDate.setBounds(1380, 33, 246, 25);
		getContentPane().add(tvDate);

	}

	private void initMenus() {
		menuTopBar = new JMenuBar();
		setJMenuBar(menuTopBar);

		menuRegistration();
		menuReport();
		menuHelp();
		menuOption();

	}

	private void menuRegistration() {
		mnRegistration = new JMenu(mString.REGISTRATIO);
		menuTopBar.add(mnRegistration);

		iMnRegisClient = new JMenuItem(mString.CLIENT);
		iMnRegisClient.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		mnRegistration.add(iMnRegisClient);

		iMnRegisOs = new JMenuItem(mString.OS);
		iMnRegisOs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		mnRegistration.add(iMnRegisOs);

		iMnRegisUsers = new JMenuItem(mString.USER);
		iMnRegisUsers.setEnabled(false);
		iMnRegisUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		mnRegistration.add(iMnRegisUsers);

	}

	private void menuReport() {
		mnReport = new JMenu(mString.REPORT);
		mnReport.setEnabled(false);
		menuTopBar.add(mnReport);

		iMnRepoService = new JMenuItem(mString.SERVICES);
		iMnRepoService.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		mnReport.add(iMnRepoService);
	}

	private void menuHelp() {
		mnHelp = new JMenu(mString.HELP);
		menuTopBar.add(mnHelp);

		iMnHelpAbout = new JMenuItem(mString.ABOUT);
		iMnHelpAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
		mnHelp.add(iMnHelpAbout);

	}

	private void menuOption() {
		mnOption = new JMenu(mString.OPTIONS);
		menuTopBar.add(mnOption);

		iMnOptExit = new JMenuItem(mString.EXIT);
		iMnOptExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnOption.add(iMnOptExit);
	}
}
