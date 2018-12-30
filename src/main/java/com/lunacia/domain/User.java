package com.lunacia.domain;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String gender;
	private String phone;

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
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] inputArray = password.getBytes();
			md.update(inputArray);
			this.password = md.digest().toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getGender () {
		return gender;
	}

	public void setGender (String gender) {
		this.gender = gender;
	}

	public String getPhone () {
		return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
	}


	@Override
	public String toString () {
		return "[ id: " + this.id + "\n username: "+this.username + "\n name: " + this.name + "\n gender: " + this.gender + "\n phone: " + this.phone + " ]\n";
	}
}
