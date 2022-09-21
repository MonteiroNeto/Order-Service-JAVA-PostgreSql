package com.mtech.services.ui.fragments;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;

import com.mtech.services.util.LockTableEdit;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.OsFragmentViewModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class OsFragment extends JInternalFrame {
	private MyStrings mStrings = new MyStrings();
	private JTextField editTextIdOs;
	private JTextField editTextDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField editTextFindClient;
	private JTextField editTextIdClient;
	private JTable table;
	private JTextField editTextEquipment;
	private JTextField editTextDefect;
	private JTextField editTextService;
	private JTextField editTextTechnician;
	private JTextField editTextPrice;
	private JButton btnAdd;
	private JButton btnFind;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OsFragment frame = new OsFragment();
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
	public OsFragment() {
		initComponents();
		onClickEditClientRelease();
		onClickTablePosition();
	}

	private void onClickTablePosition() {
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
				setIdCLientEditText();

			}
		});

	}

	private void onClickEditClientRelease() {
		editTextFindClient.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent arg0) {
				ResultSet resultSet = new OsFragmentViewModel().findClientForName(editTextFindClient.getText());
				table.setModel(DbUtils.resultSetToTableModel(resultSet));

			}

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	// Setar id client no edit text quando for clicado na linha tabela
	private void setIdCLientEditText() {
		int position = table.getSelectedRow();
		String idCli = table.getModel().getValueAt(position, 0).toString();
		editTextIdClient.setText(idCli);

	}

	private void initComponents() {
		setBounds(0, 0, 1240, 805);
		setTitle(mStrings.OS);
		setResizable(false);
		setIconifiable(true);// botao de minimizar
		setClosable(true);// botao de fechar
		setMaximizable(true);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(23, 12, 382, 159);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(mStrings.NUMBER_OS);
		lblNewLabel.setBounds(80, 31, 70, 15);
		panel.add(lblNewLabel);

		JLabel lblData = new JLabel(mStrings.DATE);
		lblData.setBounds(230, 31, 70, 15);
		panel.add(lblData);

		editTextIdOs = new JTextField();
		editTextIdOs.setEditable(false);
		editTextIdOs.setBounds(53, 58, 100, 25);
		panel.add(editTextIdOs);
		editTextIdOs.setColumns(10);

		editTextDate = new JTextField();
		editTextDate.setEditable(false);
		editTextDate.setColumns(10);
		editTextDate.setBounds(206, 58, 123, 25);
		panel.add(editTextDate);

		JRadioButton radioBtnBudget = new JRadioButton(mStrings.BUDGET);
		buttonGroup.add(radioBtnBudget);
		radioBtnBudget.setBounds(31, 112, 149, 23);
		panel.add(radioBtnBudget);

		JRadioButton radioBtnOrderService = new JRadioButton(mStrings.ORDER_SERVICE);
		buttonGroup.add(radioBtnOrderService);
		radioBtnOrderService.setBounds(208, 112, 149, 23);
		panel.add(radioBtnOrderService);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), mStrings.CLIENT,
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panel_1.setBounds(445, 12, 762, 247);
		getContentPane().add(panel_1);

		editTextFindClient = new JTextField();
		editTextFindClient.setColumns(10);
		editTextFindClient.setBounds(25, 34, 454, 25);
		panel_1.add(editTextFindClient);

		JLabel lblidClient = new JLabel("*" + mStrings.ID_CLIENT);
		lblidClient.setBounds(617, 12, 99, 15);
		panel_1.add(lblidClient);

		JLabel lblData_1 = new JLabel("");
		lblData_1.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_find2.png")));
		lblData_1.setBounds(485, 22, 70, 48);
		panel_1.add(lblData_1);

		editTextIdClient = new JTextField();
		editTextIdClient.setEditable(false);
		editTextIdClient.setColumns(10);
		editTextIdClient.setBounds(593, 34, 123, 25);
		panel_1.add(editTextIdClient);

		JScrollPane scrollPaneToTable = new JScrollPane();
		scrollPaneToTable.setBounds(25, 83, 713, 139);
		panel_1.add(scrollPaneToTable);

		// Metodo para bloquear a edicao das celulas e os movimentos das colunas e setar
		// o titulo a tabela
		String title[] = { mStrings.ID, mStrings.NAME, mStrings.ADRESS, mStrings.PHONE, mStrings.EMAIL };
		table = new JTable();
		table = new LockTableEdit(table, title).lockTable();
		scrollPaneToTable.setViewportView(table);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(38, 227, 70, 15);
		getContentPane().add(lblStatus);

		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(126, 222, 246, 24);
		// Setar um array existetente em um comboBox
		comboBoxStatus.setModel(new DefaultComboBoxModel(mStrings.LIST_STATUS_OS));
		getContentPane().add(comboBoxStatus);

		JLabel lblequipamento = new JLabel("*" + mStrings.EQUIPMENT);
		lblequipamento.setBounds(81, 350, 88, 24);
		getContentPane().add(lblequipamento);

		JLabel lbldefeito = new JLabel("*" + mStrings.DEFECT);
		lbldefeito.setBounds(99, 403, 70, 15);
		getContentPane().add(lbldefeito);

		JLabel lblServio = new JLabel(mStrings.SERVICES);
		lblServio.setBounds(99, 451, 70, 15);
		getContentPane().add(lblServio);

		JLabel lblTecnico = new JLabel(mStrings.TECHNICIAN);
		lblTecnico.setBounds(89, 502, 80, 15);
		getContentPane().add(lblTecnico);

		JLabel lblValor = new JLabel(mStrings.PRICE);
		lblValor.setBounds(956, 502, 70, 15);
		getContentPane().add(lblValor);

		editTextEquipment = new JTextField();
		editTextEquipment.setColumns(10);
		editTextEquipment.setBounds(187, 343, 990, 39);
		getContentPane().add(editTextEquipment);

		editTextDefect = new JTextField();
		editTextDefect.setColumns(10);
		editTextDefect.setBounds(187, 391, 990, 39);
		getContentPane().add(editTextDefect);

		editTextService = new JTextField();
		editTextService.setColumns(10);
		editTextService.setBounds(187, 439, 990, 39);
		getContentPane().add(editTextService);

		editTextTechnician = new JTextField();
		editTextTechnician.setColumns(10);
		editTextTechnician.setBounds(187, 490, 713, 39);
		getContentPane().add(editTextTechnician);

		editTextPrice = new JTextField();
		editTextPrice.setColumns(10);
		editTextPrice.setBounds(1012, 490, 163, 39);
		getContentPane().add(editTextPrice);

		btnAdd = new JButton("");
		btnAdd.setToolTipText(mStrings.ADD);
		btnAdd.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_add.png")));
		btnAdd.setBounds(138, 614, 80, 80);
		getContentPane().add(btnAdd);

		btnFind = new JButton("");
		btnFind.setToolTipText(mStrings.FIND);
		btnFind.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_find.png")));
		btnFind.setBounds(356, 614, 80, 80);
		getContentPane().add(btnFind);

		btnEdit = new JButton("");
		btnEdit.setToolTipText(mStrings.EDIT);
		btnEdit.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_edit.png")));
		btnEdit.setBounds(574, 614, 80, 80);
		getContentPane().add(btnEdit);

		btnRemove = new JButton("");
		btnRemove.setToolTipText(mStrings.REMOVE);
		btnRemove.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_remove.png")));
		btnRemove.setBounds(792, 614, 80, 80);
		getContentPane().add(btnRemove);

		btnPrint = new JButton("");
		btnPrint.setToolTipText(mStrings.PRINT);
		btnPrint.setIcon(new ImageIcon(OsFragment.class.getResource("/com/mtech/services/icon/ic_print.png")));
		btnPrint.setBounds(1010, 614, 80, 80);
		getContentPane().add(btnPrint);

	}
}
