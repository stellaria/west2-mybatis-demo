package com.lunacia.service;

import com.lunacia.dao.AdministratorMapper;
import com.lunacia.dao.UserMapper;
import com.lunacia.domain.Administrator;
import com.lunacia.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginManager {
	private Connection conn;
	private Scanner scanner = new Scanner(System.in);
	private String username = null, password = null;
	private Encrypt en = new Encrypt();

	private void enterPassword() {
		Console console = System.console();
		scanner = new Scanner(System.in);
		if (console == null) {
			password = scanner.next();
		} else {
			char[] passwdArray = console.readPassword("%s");
			password = new String(passwdArray);
		}
	}

	public void signInAsUser() throws IOException {
		conn = new Connection();
		UserMapper um = conn.getSession().getMapper(UserMapper.class);
		AdministratorMapper am = conn.getSession().getMapper(AdministratorMapper.class);
		User user = new User();
		String name, gender, phone;
		name = gender = phone = null;
		scanner = new Scanner(System.in);
		System.out.printf("please enter username: ");
		username = scanner.next();
		while (um.getUser(username) != null) {
			System.out.printf("%s already exist, please enter another name: ", username);
			username = scanner.next();
		}
		System.out.printf("please enter password: ");
		enterPassword();
		System.out.printf("please enter name: ");
		name = scanner.next();
		System.out.printf("please enter gender(f/m): ");
		gender = scanner.next();
		System.out.printf("please enter phone: ");
		phone = scanner.next();
		Encrypt en = new Encrypt();
		user.setUsername(username);
		user.setPassword(en.getHash(password));
		user.setName(name);
		user.setGender(gender);
		user.setPhone(phone);
		am.insertUser(user);
		conn.close();
	}

//	public void signInAsAdmin() throws IOException {
//		conn = new Connection();
//		AdministratorMapper am = conn.getSession().getMapper(AdministratorMapper.class);
//		Administrator admin = new Administrator();
//		System.out.printf("please enter username ");
//		username = scanner.next();
//		if (am.getAdministrator(username) != null) {
//			System.out.printf("%s already exist, please enter another name: ", username);
//			username = scanner.next();
//		}
//		System.out.printf("please enter password: ");
//		enterPassword();
//		am.insertAdministrator(admin);
//		conn.close();
//	}
	public User loginAsUser() throws IOException {
		conn = new Connection();
		UserMapper um = conn.getSession().getMapper(UserMapper.class);
		scanner = new Scanner(System.in);
		System.out.print("please enter username: ");
		username = scanner.next();
		User user = um.getUser(username);
		if (user == null) {
			System.out.println("can't find such user");
			return null;
		}
		System.out.print("please enter password: ");
		enterPassword();
//		System.out.println(en.getHash(password).equals(user.getPassword()));
		while (!en.getHash(password).equals(user.getPassword())) {
			System.out.print("login failed, please enter correct password: ");
			enterPassword();
		}
		conn.close();
		return user; //用户登录
	}

	public Administrator loginAsAdmin() throws IOException {
		conn = new Connection();
		AdministratorMapper am = conn.getSession().getMapper(AdministratorMapper.class);
		System.out.printf("please enter username: ");
		username = scanner.next();
		Administrator admin = am.getAdministrator(username);
		if (admin == null) {
			System.out.println("can't find such administrator");
			return null;
		}
		System.out.print("please enter password: ");
		enterPassword();
		while (!en.getHash(password).equals(admin.getPassword())) {
			System.out.print("login failed, please enter correct password: ");
			enterPassword();
		}
		conn.close();
		return admin;//管理员登录
	}

//	public int login() throws IOException {
//		int flag = 3;
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("1.login as user\n2.login as administrator");
//		int choice = scanner.nextInt();
//		switch (choice) {
//			case 1: {loginAsUser(); break;}
//			case 2: {loginAsAdmin(); break;}
//			default: {
//				System.out.println("please choose the right choice.");
//				break;
//			}
//		}
//		return flag;
//	}
}
//
//class Test {
//	public static void main (String[] args) throws IOException {
//		LoginManager lg = new LoginManager();
//		lg.login();
//	}
//}