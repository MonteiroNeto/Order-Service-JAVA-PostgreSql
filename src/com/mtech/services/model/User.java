package com.mtech.services.model;

import java.util.Objects;

public class User {
	private int iduser;
	private String usuario;
	private String fone;
	private String login;
	private String senha;
	private String perfil;

	public User() {
		super();
	}

	public User(int iduser, String usuario, String fone, String login, String senha, String perfil) {
		super();
		this.iduser = iduser;
		this.usuario = usuario;
		this.fone = fone;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public User(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(fone, iduser, login, perfil, senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(fone, other.fone) && iduser == other.iduser && Objects.equals(login, other.login)
				&& Objects.equals(perfil, other.perfil) && Objects.equals(senha, other.senha)
				&& Objects.equals(usuario, other.usuario);
	}

}
