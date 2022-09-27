package com.mtech.services.viewmodel;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mtech.services.dao.DaoClient;
import com.mtech.services.dao.DaoOs;
import com.mtech.services.model.Os;
import com.mtech.services.util.GenerateReport;
import com.mtech.services.values.MyStrings;

public class OsFragmentViewModel {
	private MyStrings mStrings = new MyStrings();

	public ResultSet findClientForName(String name) {
		return new ClientsFragmentViewModel().findClientForName(name);
	}

	public Boolean addOs(Os os) {
		String message = new DaoOs().save(os);

		if (message.equals("")) {
			showDialog(mStrings.SUCCESS, mStrings.SUCCESS);
			return true;
		} else {
			showDialog(mStrings.ERROR, message);

			return false;
		}

	}

	public Os findOs(int idOs) {
		Os os = new DaoOs().findOs(idOs);

		if (os != null) {
			return os;
		} else {
			showDialog(mStrings.ERROR, mStrings.OS_NOT_FOUND);
			return null;
		}

	}

	public Boolean updateOs(Os os) {
		String message = new DaoOs().update(os);

		if (message.equals("")) {
			showDialog(mStrings.SUCCESS, mStrings.SUCCESS);
			return true;
		} else {

			showDialog(mStrings.ERROR, message);
			return false;
		}
	}

	public Boolean removeOs(int idOs) {

		int confirmed = JOptionPane.showConfirmDialog(null, mStrings.ARE_YOU_SURE_REMOVE, mStrings.WARNING,
				JOptionPane.YES_NO_OPTION);

		if (confirmed == JOptionPane.YES_OPTION) {
			String message = new DaoOs().remove(idOs);

			if (message.equals("")) {
				showDialog(mStrings.SUCCESS, mStrings.SUCCESS);
				return true;
			} else {
				showDialog(mStrings.ERROR, message);
				return false;
			}
		} else {
			return false;
		}

	}

	public String getNextOs() {
		String message = new DaoOs().getNextOs();

		if (message.equals(mStrings.ERROR_FIND_LAST_OS)) {

			return message;
		} else {
			return message;
		}

	}

	public void printOs(int idos) {

		int reported = JOptionPane.showConfirmDialog(null, mStrings.DO_YOU_WANT_ISSUE_REPORT, mStrings.REPORT,
				JOptionPane.YES_NO_OPTION);

		if (reported == JOptionPane.YES_OPTION) {

			Os os = new DaoOs().findOs(idos);

			new GenerateReport(mStrings.OS).generateOsToClientReport(mStrings.LIST_REPORT_OS_CLIENT_TITLE_COLUMN_TABLE,
					os);

		}

	}

	private void showDialog(String title, String message) {
		JOptionPane.showConfirmDialog(null, message, title, JOptionPane.CLOSED_OPTION);
	}
}
