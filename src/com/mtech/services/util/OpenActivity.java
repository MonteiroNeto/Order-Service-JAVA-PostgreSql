package com.mtech.services.util;

import java.awt.Color;

import com.mtech.services.model.User;
import com.mtech.services.ui.AboutActivity;
import com.mtech.services.ui.MainActivity;
import com.mtech.services.values.MyStrings;

public class OpenActivity {
	private MyStrings mStrings;

	public OpenActivity() {
		mStrings = new MyStrings();
	}

	public void openMainActivity(User user) {
		String perfil = user.getPerfil();

		MainActivity mainActivity = new MainActivity();

		// SET permission user ADMNISTRADOR
		if (perfil.equals(mStrings.ADMIN)) {
			mainActivity.mnReport.setEnabled(true);
			mainActivity.iMnRegisUsers.setEnabled(true);
			mainActivity.tvUser.setForeground(Color.red);
		}

		mainActivity.tvUser.setText(user.getUsuario());
		mainActivity.setVisible(true);
	}

	public void openAboutActivity() {
		AboutActivity aboutActivity = new AboutActivity();

		aboutActivity.setVisible(true);
	}

}
