package com.mtech.services.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mtech.services.values.MyStrings;

public class LockTableEdit {
	private JTable table;
	private String[] title;
	private MyStrings mStrings;

	public LockTableEdit(JTable table, String[] title) {
		this.table = table;
		this.title = title;
	}

	public JTable lockTable() {
		String body[][] = {};

		// Sobre escrita de metodo;
		// faz com que tabela bloqueei a edicao das celulas.
		table = new javax.swing.JTable(new DefaultTableModel(body, title)) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		table.getTableHeader().setReorderingAllowed(false);// Bloqueia o movimento das colunas

		return table;
	}

}
