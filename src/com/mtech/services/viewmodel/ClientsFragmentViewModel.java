package com.mtech.services.viewmodel;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mtech.services.dao.DaoClient;
import com.mtech.services.model.Client;
import com.mtech.services.values.MyStrings;

public class ClientsFragmentViewModel {
	private MyStrings mStrings = new MyStrings();

	public ResultSet findClientForName(String name) {
		ResultSet resultSet = new DaoClient().findClientForName(name);

		if (resultSet != null) {

			return resultSet;
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.CLIENT_NOT_FOUND, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			return null;
		}
	}

	public Boolean addClient(Client client) {
		String message = new DaoClient().addClient(client);
		if (message.equals("")) {
			JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
			return true;
		} else {
			JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			return false;
		}
	}

	public Boolean editClient(Client client) {
		String message = new DaoClient().editClient(client);
		if (message.equals("")) {
			JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
			return true;
		} else {
			JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			return false;
		}

	}

	public Boolean removeClient(int idCli) {

		String message = confirmeRemove(idCli);
		
		if (message != null) {
			if (message.equals("")) {
				JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
				return true;
			} else {
				JOptionPane.showConfirmDialog(null, message, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
				return false;
			}
		} else {
			return false;
		}
		
		
	}

	private String confirmeRemove(int idCli) {
		String message = "";
		int option = JOptionPane.showConfirmDialog(null, mStrings.ARE_YOU_SURE_REMOVE, mStrings.WARNING,
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			message = new DaoClient().removeCLient(idCli);
			return message;
		} else {
			return null;
		}

	}

}
