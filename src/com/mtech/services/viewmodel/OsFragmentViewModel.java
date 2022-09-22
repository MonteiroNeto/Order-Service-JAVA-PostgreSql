package com.mtech.services.viewmodel;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mtech.services.dao.DaoClient;
import com.mtech.services.dao.DaoOs;
import com.mtech.services.model.Os;
import com.mtech.services.values.MyStrings;

public class OsFragmentViewModel {
	private MyStrings mStrings = new MyStrings();

	public ResultSet findClientForName(String name) {
		return new ClientsFragmentViewModel().findClientForName(name);
	}

	public Boolean addOs(Os os) {
		String message = new DaoOs().save(os);

		if (message.equals("")) {
			JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
			return true;
		} else {
			JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);

			return false;
		}

	}

	public Os findOs(int idOs) {
		Os os = new DaoOs().findOs(idOs);

		if (os != null) {
			return os;
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.OS_NOT_FOUND, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			return null;
		}

	}

	public void editOs() {

	}

	public void removeOs() {

	}
}
