package com.mtech.services.ui.fragments;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Savepoint;

import javax.swing.JInternalFrame;

import com.mtech.services.model.Os;
import com.mtech.services.util.GetDate;
import com.mtech.services.util.LockTableEdit;
import com.mtech.services.values.MyStrings;
import com.mtech.services.viewmodel.OsFragmentViewModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
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
	private final ButtonGroup radioBtnGroup = new ButtonGroup();
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
	private JComboBox comboBoxStatus;
	private JRadioButton radioBtnBudget, radioBtnOrderService;

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
		onClickBtnSaveOs();
		setDate();
		onclickBtnFindOs();

	}

	private void onclickBtnFindOs() {
		btnFind.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				findOs();

			}
		});

	}

	protected void findOs() {
		JOptionPane jPane = new JOptionPane();

		String idForValidation = jPane.showInputDialog(null, mStrings.ENTER_OS_NUMBER);

		int idOs = validationId(idForValidation);

		if (idOs > 0) {

			Os osReceived = new OsFragmentViewModel().findOs(idOs);
			if (osReceived != null) {
				sendTextToForm(osReceived);
			}

		} else if (idForValidation != null) {
			JOptionPane.showMessageDialog(null, mStrings.INVALID_ID);
		}

	}

	private void sendTextToForm(Os os) {
		// desabilitar componentes que não podem ser usado durante a edicao
		btnAdd.setEnabled(false);
		table.setVisible(false);
		editTextFindClient.setEnabled(false);

		// enviar os dados para o formulario
		editTextIdOs.setText(String.valueOf(os.getId()));
		editTextDate.setText(os.getData());
		if (os.getServiceType() == 1) {
			radioBtnBudget.setSelected(true);
		} else {
			radioBtnOrderService.setSelected(true);
		}
		editTextIdClient.setText(String.valueOf(os.getIdCli()));
		comboBoxStatus.setSelectedIndex(os.getStatus());
		editTextEquipment.setText(os.getEquipment());
		editTextDefect.setText(os.getDefect());
		editTextService.setText(os.getService());
		editTextTechnician.setText(os.getTechnician());
		editTextPrice.setText(String.valueOf(os.getPrice()));

	}

	private int validationId(String idForValidation) {
		int idOs = 0;

		try {

			idOs = Integer.parseInt(idForValidation);
			return idOs;
		} catch (Exception e) {
			return 0;
		}

	}

	private void clearForm() {
		editTextIdOs.setText("");
		radioBtnBudget.setSelected(false);
		radioBtnOrderService.setSelected(false);
		editTextIdClient.setText("");
		editTextFindClient.setEnabled(true);
		btnAdd.setEnabled(true);
		table.setVisible(true);
		editTextEquipment.setText("");
		editTextDefect.setText("");
		editTextService.setText("");
		editTextTechnician.setText("");
		editTextPrice.setText("");

	}

	private void setDate() {
		editTextDate.setText(new GetDate().getFormatDate());

	}

	private void onClickBtnSaveOs() {
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				save();
			}

		});

	}

	private void save() {
		String message = validateForm();

		if (message.equals("")) {
			Os os = getOsEditOfForm();
			new OsFragmentViewModel().addOs(os);
		} else {
			JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
		}

	}

	private String validateForm() {
		String message = "";

		if (editTextIdClient.getText().isBlank()) {
			message = mStrings.SELECT_A_CLIENT;
		} else if (!radioBtnBudget.isSelected() && !radioBtnOrderService.isSelected()) {
			message = mStrings.SELECT_SERVICE_TYPE;
		} else if (comboBoxStatus.getSelectedIndex() == 0) {
			message = mStrings.SELECT_STATUS_OS;
		} else if (editTextEquipment.getText().isBlank()) {
			message = mStrings.EQUIPMENT_FIELD_IS_EMPTY;
		} else if (editTextDefect.getText().isBlank()) {
			message = mStrings.DEFECT_FIELD_IS_EMPTY;
		} else {
			message = "";
		}

		return message;

	}

	private Os getOsEditOfForm() {
		double price = Double.parseDouble(editTextPrice.getText().replace(",", "."));
		int type_service = 1;

		// obter a posicao do radio button que esta selecionado
		if (radioBtnBudget.isSelected()) {
			type_service = 1;
		} else if (radioBtnOrderService.isSelected()) {
			type_service = 2;
		}

		Os os = new Os(editTextEquipment.getText(), editTextDefect.getText(), editTextService.getText(),
				editTextTechnician.getText(), price, Integer.parseInt(editTextIdClient.getText()),
				comboBoxStatus.getSelectedIndex(), type_service);

		return os;
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
		panel.setBounds(23, 12, 387, 173);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(mStrings.NUMBER_OS);
		lblNewLabel.setBounds(80, 31, 70, 15);
		panel.add(lblNewLabel);

		JLabel lblData = new JLabel(mStrings.DATE);
		lblData.setBounds(241, 31, 70, 15);
		panel.add(lblData);

		editTextIdOs = new JTextField();
		editTextIdOs.setEditable(false);
		editTextIdOs.setHorizontalAlignment(JTextField.CENTER);
		editTextIdOs.setBounds(53, 58, 100, 25);
		panel.add(editTextIdOs);
		editTextIdOs.setColumns(10);

		editTextDate = new JTextField();
		editTextDate.setEditable(false);
		editTextDate.setHorizontalAlignment(JTextField.CENTER);
		editTextDate.setColumns(10);
		editTextDate.setBounds(178, 58, 197, 25);
		panel.add(editTextDate);

		radioBtnBudget = new JRadioButton(mStrings.BUDGET);
		radioBtnGroup.add(radioBtnBudget);
		radioBtnBudget.setBounds(25, 128, 149, 23);
		panel.add(radioBtnBudget);

		radioBtnOrderService = new JRadioButton(mStrings.ORDER_SERVICE);
		radioBtnGroup.add(radioBtnOrderService);
		radioBtnOrderService.setBounds(202, 128, 149, 23);
		panel.add(radioBtnOrderService);

		JLabel lblNewLabel_1 = new JLabel("*" + mStrings.SERVICE_TYPE);
		lblNewLabel_1.setBounds(135, 105, 138, 15);
		panel.add(lblNewLabel_1);

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
		editTextIdClient.setHorizontalAlignment(JTextField.CENTER);
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

		JLabel lblStatus = new JLabel("*" + mStrings.STATUS);
		lblStatus.setBounds(38, 227, 70, 15);
		getContentPane().add(lblStatus);

		comboBoxStatus = new JComboBox();
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
