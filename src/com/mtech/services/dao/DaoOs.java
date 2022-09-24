package com.mtech.services.dao;

import java.beans.JavaBean;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mtech.services.model.Os;
import com.mtech.services.values.MyStrings;
import com.mtech.services.values.TblReference.TblOs;

import net.proteanit.sql.JTableUpdateDb;

public class DaoOs {
	private Connection connection = new Service().conector();
	private PreparedStatement ps;
	private ResultSet resultSet;
	private MyStrings mStrings = new MyStrings();
	private TblOs tblReference;

	public String save(Os os) {
		String message = "";
		String sql = "INSERT INTO " + tblReference.TBL_NAME + " ( " + tblReference.COLUMN_EQUIPAMENTO + ", "
				+ tblReference.COLUMN_DEFEITO + ", " + tblReference.COLUMN_SERVICO + ", " + tblReference.COLUMN_TECNICO
				+ ", " + tblReference.COLUMN_VALOR + ", " + tblReference.COLUMN_ID_CLIENTE + ", "
				+ tblReference.COLUMN_STATUS + ", " + tblReference.COLUMN_TYPE_SERVICE + " ) VALUES (?,?,?,?,?,?,?,?);";

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, os.getEquipment());
			ps.setString(2, os.getDefect());
			ps.setString(3, os.getService());
			ps.setString(4, os.getTechnician());
			ps.setDouble(5, os.getPrice());
			ps.setInt(6, os.getIdCli());
			ps.setInt(7, os.getStatus());
			ps.setInt(8, os.getServiceType());
			int inserted = ps.executeUpdate();
			connection.close();

			if (inserted > 0) {
				return "";
			} else {
				return mStrings.ERROR_INSERT_OS;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

	public String update(Os os) {
		String message = "";
		String sql = "UPDATE " + tblReference.TBL_NAME + " SET " + tblReference.COLUMN_EQUIPAMENTO + "=?,"
				+ tblReference.COLUMN_DEFEITO + "=?," + tblReference.COLUMN_SERVICO + "=?,"
				+ tblReference.COLUMN_TECNICO + "=?," + tblReference.COLUMN_VALOR + "=?,"
				+ tblReference.COLUMN_ID_CLIENTE + "=?," + tblReference.COLUMN_STATUS + "=?,"
				+ tblReference.COLUMN_TYPE_SERVICE + "=? WHERE " + tblReference.COLUMN_ID_OS + "=? ;";

		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, os.getEquipment());
			ps.setString(2, os.getDefect());
			ps.setString(3, os.getService());
			ps.setString(4, os.getTechnician());
			ps.setDouble(5, os.getPrice());
			ps.setInt(6, os.getIdCli());
			ps.setInt(7, os.getStatus());
			ps.setInt(8, os.getServiceType());
			ps.setInt(9, os.getId());
			int updated = ps.executeUpdate();

			connection.close();
			if (updated > 0) {
				return "";
			} else {
				return mStrings.ERROR_UPDATE_OS;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

	public String remove(int idOs) {
		String message = "";
		String sql = "DELETE FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_ID_OS + " =?;";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idOs);
			int removed = ps.executeUpdate();

			connection.close();
			if (removed > 0) {
				return "";
			} else {
				return mStrings.ERROR_REMOVE_OS;
			}

		} catch (Exception e) {
			return e.toString();
		}

	}

	public Os findOs(int idOs) {
		Os os = null;
		String sql = "SELECT * FROM " + tblReference.TBL_NAME + " WHERE " + tblReference.COLUMN_ID_OS + " = ? ;";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idOs);
			resultSet = ps.executeQuery();
			os = getOs(resultSet);
			connection.close();

			return os;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	private Os getOs(ResultSet rs) {
		Os os;
		try {
			if (rs.next()) {
				os = new Os(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				return os;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public String getNextOs() {
		String message = "";
		String sql = "SELECT " + tblReference.COLUMN_ID_OS + " FROM " + tblReference.TBL_NAME + " WHERE "
				+ tblReference.COLUMN_ID_OS + "=(SELECT MAX (" + tblReference.COLUMN_ID_OS + ") FROM "
				+ tblReference.TBL_NAME + ");";

		try {
			ps = connection.prepareStatement(sql);
			resultSet = ps.executeQuery();

			if (resultSet != null && resultSet.next()) {
				return message = resultSet.getString(1);
			} else {
				return mStrings.ERROR_FIND_LAST_OS;
			}

		} catch (Exception e) {
			System.out.println(e);
			return mStrings.ERROR_FIND_LAST_OS + "***" + e.toString();

		}

	}

}
