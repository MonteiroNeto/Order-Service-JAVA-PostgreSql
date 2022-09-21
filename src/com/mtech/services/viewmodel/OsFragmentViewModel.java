package com.mtech.services.viewmodel;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mtech.services.dao.DaoClient;
import com.mtech.services.values.MyStrings;

public class OsFragmentViewModel {
	private MyStrings mStrings = new MyStrings();

	public ResultSet findClientForName(String name) {
		return new ClientsFragmentViewModel().findClientForName(name);
	}

	public void addOs() {

	}

	public void editOs() {

	}

	public void removeOs() {

	}
}
