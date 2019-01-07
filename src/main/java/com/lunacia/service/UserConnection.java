package com.lunacia.service;

import com.lunacia.dao.UserMapper;
import com.lunacia.domain.User;

import javax.annotation.Resource;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class UserConnection extends AbstractPerson{
	private Connection conn;
	private UserMapper um;
	private User theOne;
	private Scanner scan = new Scanner(System.in);

	public UserConnection (User theOne) {
		this.theOne = theOne;
	}
	public int menu() {
		System.out.println("-------------User menu-------------");
		System.out.println("1. change password");
		System.out.println("2. change information");
		System.out.println("3. quit");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		while (i != 1 && i != 2 && i != 3) {
			i = scan.nextInt();
		}
		return i;
	}

	@Override
	public void changePassword () {
		conn = new Connection();
		try {
			um = conn.getSession().getMapper(UserMapper.class);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("please enter new password");
		String passwd = Password.enterPassword();
		Encrypt en = new Encrypt();
		theOne.setPassword(en.getHash(passwd));
		um.updateUser(theOne);
		conn.close();
	}


	public void changeInfo () {
		String name, gender, phone;
		System.out.print("please enter new name: ");
		name = scan.next();
		System.out.print("please enter new gender: ");
		gender = scan.next();
		System.out.printf("please enter new phone: ");
		phone = scan.next();
		theOne.setName(name);
		theOne.setPhone(phone);
		theOne.setGender(gender);
	}
}
