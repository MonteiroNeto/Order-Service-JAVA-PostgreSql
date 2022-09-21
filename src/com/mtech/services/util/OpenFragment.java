package com.mtech.services.util;

import javax.swing.JDesktopPane;

import com.mtech.services.ui.fragments.ClientFragment;
import com.mtech.services.ui.fragments.OsFragment;
import com.mtech.services.ui.fragments.UsersFragment;

public class OpenFragment {
	private JDesktopPane pane;

	public OpenFragment(JDesktopPane pane) {
		this.pane = pane;
	}

	public void openUserFragment() {
		UsersFragment fragment = new UsersFragment();
		fragment.setVisible(true);
		pane.add(fragment);

	}
	
	public void openClientFragment() {
		ClientFragment fragment = new ClientFragment();
		fragment.setVisible(true);
		pane.add(fragment);

	}
	
	public void openOsFragment() {
		OsFragment fragment = new OsFragment();
		fragment.setVisible(true);
		pane.add(fragment);

	}

}
