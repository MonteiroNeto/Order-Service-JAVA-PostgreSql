package com.mtech.services.viewmodel;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mtech.services.dao.DaoUser;
import com.mtech.services.model.User;
import com.mtech.services.values.MyStrings;

public class UserFragmentViewModel {
	private MyStrings mStrings = new MyStrings();

	public User findUserForId(int iduser) {
		User user = new DaoUser().getUser(iduser);

		if (user != null) {
			return new DaoUser().getUser(iduser);
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.USER_NOT_FOUND, mStrings.ERROR, JOptionPane.CLOSED_OPTION);

			return null;
		}

	}

	public ResultSet findUsersForName(String name) {
		ResultSet resultSet = new DaoUser().findUserForName(name);

		if (resultSet != null) {
			return resultSet;
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.USER_NOT_FOUND, mStrings.ERROR, JOptionPane.CLOSED_OPTION);
			return null;
		}

	}

	public Boolean addUser(User user) {
		String messenger = new DaoUser().addUser(user);

		if (messenger.equals("")) {
			JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
			return true;
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.ERROR_INSERT_USER + "\n " + messenger, mStrings.ERROR,
					JOptionPane.CLOSED_OPTION);
			return false;
		}
	}

	public Boolean updateUser(User user) {
		String messenge = new DaoUser().UpdateUser(user);

		if (messenge.equals("")) {
			JOptionPane.showConfirmDialog(null, mStrings.SUCCESS, mStrings.SUCCESS, JOptionPane.CLOSED_OPTION);
			return true;
		} else {
			JOptionPane.showConfirmDialog(null, mStrings.ERROR_UPDATE_USER + "\n " + messenge, mStrings.ERROR,
					JOptionPane.CLOSED_OPTION);
			return false;
		}

	}

	public Boolean removeUser(int idUser) {

		String message = confirmDeletion(idUser);

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

	private String confirmDeletion(int idUser) {

		int btnClicked = JOptionPane.showConfirmDialog(null, mStrings.ARE_YOU_SURE_REMOVE, mStrings.WARNING,
				JOptionPane.YES_NO_OPTION);
		if (btnClicked == JOptionPane.OK_OPTION) {
			String message = new DaoUser().removeUser(idUser);
			return message;
		} else {

			return null;
		}

	}

}
