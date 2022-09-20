package com.mtech.services.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mtech.services.model.Client;
import com.mtech.services.values.MyStrings;
import com.mtech.services.values.TblReference.TblClient;

public class DaoClient {
	private Connection connection = new Service().conector();
	private PreparedStatement ps;
	private ResultSet resultSet;
	private TblClient tblReference;
	private MyStrings mStrings = new MyStrings();

	public ResultSet findClientForName(String name) {
		String sql = "SELECT * FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_NAME_CLI + " like ? ;";

		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			connection.close();

			return resultSet;
		} catch (Exception e) {
			return null;
		}

	}

	public String addClient(Client client) {
		String sql = "INSERT INTO " + tblReference.TBL_NAME + "(" + tblReference.COLUMN_NAME_CLI + ","
				+ tblReference.COLUMN_ADDRES_CLI + "," + tblReference.COLUMN_PHONE_CLI + ","
				+ tblReference.COLUMN_EMAIL_CLI + ") VALUES (?,?,?,?);";

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, client.getName());
			ps.setString(2, client.getAdress());
			ps.setString(3, client.getPhone());
			ps.setString(4, client.getEmail());
			int add = ps.executeUpdate();
			connection.close();
			if (add > 0) {
				return "";
			} else {
				return mStrings.ERROR_REMOVE_CLIENT;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

	public String editClient(Client client) {
		String sql = "UPDATE " + tblReference.TBL_NAME + " SET " + tblReference.COLUMN_NAME_CLI + " =?,"
				+ tblReference.COLUMN_ADDRES_CLI + " =?," + tblReference.COLUMN_PHONE_CLI + " =?,"
				+ tblReference.COLUMN_EMAIL_CLI + " =? WHERE " + tblReference.COLUMN_ID_CLI + " =? ;";

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, client.getName());
			ps.setString(2, client.getAdress());
			ps.setString(3, client.getPhone());
			ps.setString(4, client.getEmail());
			ps.setInt(5, client.getIdClient());
			int updated = ps.executeUpdate();
			connection.close();
			if (updated > 0) {
				return "";
			} else {
				return mStrings.ERROR_UPDATE_CLIENT;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

	public String removeCLient(int idClient) {
		String message = "";
		String sql = "DELETE FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_ID_CLI + " =? ;";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idClient);
			int deleted = ps.executeUpdate();
			connection.close();
			if (deleted > 0) {
				return "";
			} else {
				return mStrings.ERROR_REMOVE_CLIENT;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

}
