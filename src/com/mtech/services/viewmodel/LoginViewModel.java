package com.mtech.services.viewmodel;

import java.awt.Window;

import com.mtech.services.dao.DaoUser;
import com.mtech.services.model.User;

public class LoginViewModel {
	private Window activity;

	public LoginViewModel(Window activity) {
		this.activity = activity;
	}

	public void login(String user, String pass) {
		User userSignIn = new User(user, pass);
		Boolean signIn = new DaoUser().getSignIn(userSignIn);
		if (signIn) {
			activity.dispose();
		}
	}

}
