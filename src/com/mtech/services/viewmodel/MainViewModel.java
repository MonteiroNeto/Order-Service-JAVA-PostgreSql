package com.mtech.services.viewmodel;

import java.awt.Window;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mtech.services.dao.DaoClient;
import com.mtech.services.util.GenerateReport;
import com.mtech.services.util.OpenActivity;
import com.mtech.services.util.OpenFragment;
import com.mtech.services.values.MyStrings;

public class MainViewModel {
	private Window activity;
	private MyStrings mStrings = new MyStrings();

	public MainViewModel(Window activity) {
		this.activity = activity;
	}

	public void closeSystem() {
		int exit = JOptionPane.showConfirmDialog(null, mStrings.DO_YOU_WANT_EXIT, mStrings.ATTENTION,
				JOptionPane.YES_NO_OPTION);

		if (exit == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	public void openAboutActivity() {
		new OpenActivity().openAboutActivity();
	}

	public void openUserFragment(JDesktopPane pane) {
		new OpenFragment(pane).openUserFragment();

	}

	public void openClientFragment(JDesktopPane pane) {
		new OpenFragment(pane).openClientFragment();
		;
	}

	public void openOsFragment(JDesktopPane pane) {
		new OpenFragment(pane).openOsFragment();
	}

	public void generateClientReport() {

	}

	public void generateServicesReport() {

		new GenerateReport().generateClientReport(mStrings.SERVICE_REPORT);

	}

}
