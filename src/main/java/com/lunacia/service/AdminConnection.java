package com.lunacia.service;

import com.lunacia.dao.AdministratorMapper;
import com.lunacia.domain.Administrator;
import com.lunacia.domain.User;

import java.io.IOException;
import java.util.Scanner;

public class AdminConnection extends AbstractPerson{
	private Administrator theOne;
	private AdministratorMapper am;
	private Connection conn;
	private Scanner scan = new Scanner(System.in);

	public AdminConnection (Administrator theOne) {
		this.theOne = theOne;
	}
	public int menu() {
		System.out.println("-------------administrator menu-------------");
		System.out.println("1. change password");
		System.out.println("2. show all users");
		System.out.println("3. add a administrator");
		System.out.println("4. delete the user");
		System.out.println("5. quit");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		while (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
			i = scan.nextInt();
		}
		return i;
	}

	@Override
	public void changePassword () {
		conn = new Connection();
		try {
			am = conn.getSession().getMapper(AdministratorMapper.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.printf("please enter new password: ");
		String passwd = Password.enterPassword();
		Encrypt en = new Encrypt();
		theOne.setPassword(en.getHash(passwd));
		am.changePassword(theOne);
		conn.close();
	}

	public void showUsers() {
		conn = new Connection();
		try {
			am = conn.getSession().getMapper(AdministratorMapper.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(am.getUsers());
		conn.close();
	}

	public void deleteUser() {
		conn = new Connection();
		try {
			am = conn.getSession().getMapper(AdministratorMapper.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.printf("please enter the username: ");
		String username = scan.next();
		User user = am.getUser(username);
		am.deleteUser(user);
		conn.close();
	}

	public void addAdmin() {
		conn = new Connection();
		try {
			am = conn.getSession().getMapper(AdministratorMapper.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Encrypt en = new Encrypt();
		Administrator admin = new Administrator();
		String username, password;
		System.out.printf("please enter username: ");
		username = scan.next();
		while (am.getAdministrator(username) != null) {
			System.out.printf("%s already exist, please enter another name: ", username);
			username = scan.next();
		}
		System.out.printf("please enter password: ");
		password = scan.next();
		admin.setUsername(username);
		admin.setPassword(en.getHash(password));
		am.insertAdministrator(admin);
		conn.close();
	}
}
