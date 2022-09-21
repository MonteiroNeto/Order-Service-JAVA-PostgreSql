package com.mtech.services.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mtech.services.model.User;
import com.mtech.services.ui.LoginActivity;
import com.mtech.services.util.OpenActivity;
import com.mtech.services.values.MyStrings;
import com.mtech.services.values.TblReference;
import com.mtech.services.values.TblReference.TblUser;

public class DaoUser {
	private Connection connection = new Service().conector();
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	private MyStrings mStrings = new MyStrings();
	private TblUser tblReference;

	public DaoUser() {

	}

	public Boolean getSignIn(User _user) {
		User user = new User();
		String getUserPass = "SELECT * FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_LOGIN
				+ "=? and " + tblReference.COLUMN_PASS + "=?";
		try {
			// Metodo abaixo irá buscar no DB os dados corresponde ao user e pass digitada.
			pStatement = connection.prepareStatement(getUserPass);
			pStatement.setString(1, _user.getLogin());
			pStatement.setString(2, _user.getSenha());

			// a linha abaixo execulta a query
			resultSet = pStatement.executeQuery();

			// Verificar se existe user e senha correspondente
			if (resultSet.next()) {
				// pegar perfil de usuario
				String perfil = resultSet.getString(6);// o numero '6' é referente a coluna que esta o perfil no banco
														// de dados.

				// setar os dados do usuario para manipular na tela
				user.setPerfil(perfil);
				user.setUsuario(resultSet.getString(2));

				new OpenActivity().openMainActivity(user);

				// fechar conexao com o banco
				connection.close();
				return true;

			} else {
				// fechar conexao com o banco
				connection.close();
				JOptionPane.showMessageDialog(null, mStrings.MSG_USER_OR_PASS_INVALID);
				return false;

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}

	}

	// *** GET USER ****
	public User getUser(int iduser) {
		User user = null;
		String sql = "SELECT * FROM " + tblReference.TBL_NAME + " where " + tblReference.COLUMN_ID_USER + " = ? ;";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, iduser);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

				return user;
			} else {
				return user;
			}

		} catch (Exception e) {
			System.out.println("Erro ao GET USER****************" + e);
			return user;
		}

	}

	/***** INSERT USER *****/
	public String addUser(User user) {
		String sql = "INSERT INTO " + tblReference.TBL_NAME + " (" + tblReference.COLUMN_NAME + ","
				+ tblReference.COLUMN_PHONE + "," + tblReference.COLUMN_LOGIN + "," + tblReference.COLUMN_PASS + ","
				+ tblReference.COLUMN_PERFIL + ") VALUES(?,?,?,?,?);";

		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getUsuario());
			pStatement.setString(2, user.getFone());
			pStatement.setString(3, user.getLogin());
			pStatement.setString(4, user.getSenha());
			pStatement.setString(5, user.getPerfil());

			int inserted = pStatement.executeUpdate();
			connection.close();
			// LOGICA se a execulcao no banco for maior que ZERO é pq foi inserido com
			// sucesso, caso contrario deu erro ao inserir
			if (inserted > 0) {
				return "";
			} else {
				return mStrings.USER_NOT_INSERTED;
			}

		} catch (Exception e) {

			System.out.println("ERRO INSERT INTO TBL USER*************************************" + e);
			// TODO: handle exception

			return e.toString();
		}
	}

	/***** UPDATE USER *****/
	public String UpdateUser(User user) {
		String messenge = "";
		String sql = "UPDATE " + tblReference.TBL_NAME + " SET " + tblReference.COLUMN_NAME + " =?,"
				+ tblReference.COLUMN_PHONE + " =?," + tblReference.COLUMN_LOGIN + " =?," + tblReference.COLUMN_PASS
				+ " =?," + tblReference.COLUMN_PERFIL + " =? WHERE " + tblReference.COLUMN_ID_USER + "=?";

		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getUsuario());
			pStatement.setString(2, user.getFone());
			pStatement.setString(3, user.getLogin());
			pStatement.setString(4, user.getSenha());
			pStatement.setString(5, user.getPerfil());
			pStatement.setInt(6, user.getIduser());

			int updated = pStatement.executeUpdate();
			connection.close();

			if (updated > 0) {
				return messenge = "";
			} else {
				return messenge = mStrings.ERROR_UPDATE_USER;
			}

		} catch (Exception e) {

			return messenge = e.toString();
		}

	}

	/***** DELETE USER *****/
	public String removeUser(int idUser) {
		String message = "";
		String sql = "DELETE FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_ID_USER + " =? ;";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, idUser);
			int removed = pStatement.executeUpdate();
			connection.close();

			if (removed > 0) {
				return "";
			} else {
				return mStrings.ERROR_REMOVE_USER;
			}

		} catch (Exception e) {

			return message + e;
		}

	}

	/***** FIND USERS FOR NAME *****/
	public ResultSet findUserForName(String name) {
		String titleAndColuns = titleTableRefactory();

		String sql = "SELECT " + titleAndColuns + " FROM " + tblReference.TBL_NAME + " WHERE "
				+ tblReference.COLUMN_NAME + " like ? ;";

		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name + "%");
			resultSet = pStatement.executeQuery();
			connection.close();
			return resultSet;

		} catch (Exception e) {

			System.out.println();
			return null;
		}

	}

	private String titleTableRefactory() {
		String title = "";

		String id = tblReference.COLUMN_ID_USER + " as " + mStrings.ID + ", ";
		String name = tblReference.COLUMN_NAME + " as " + mStrings.NAME + ", ";
		String phone = tblReference.COLUMN_PHONE + " as " + mStrings.PHONE + ", ";
		String login = tblReference.COLUMN_LOGIN + " as " + mStrings.LOGIN + ", ";
		String perfil = tblReference.COLUMN_PERFIL + " as " + mStrings.PERFIL;

		title = id + name + phone + login + perfil;

		return title;
	}

}
