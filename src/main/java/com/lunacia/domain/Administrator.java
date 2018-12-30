package com.lunacia.domain;

public class Administrator {
	private int id;
	private String username;
	private String password;

	@Override
	public String toString () {
		return "[ username: " + this.username + " ]\n";
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}
}
