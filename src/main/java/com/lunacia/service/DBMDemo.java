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
import java.util.Scanner;

public class DBMDemo {
	private final static String RESOURCE = "mybatis-config.xml";
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private Scanner scanner;
	private String username, password;

	private void getSession() throws IOException {
		InputStream is = Resources.getResourceAsStream(RESOURCE);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		session = sqlSessionFactory.openSession();
	}
	private void enterPassWord() {
		Console console = System.console();
		scanner = new Scanner(System.in);
		if (console == null) {
			System.out.println("username: ");
			username = scanner.next();
			System.out.println("password: ");
			password = scanner.next();
		} else {
			username = scanner.next();
			char[] passwdArray = console.readPassword("%s", "password: ");
			password = new String(passwdArray);
		}
//		while (user.getPassword() != password) {
//			System.out.println("password error, please retry");
//			if (console == null) {
//				System.out.println("password: ");
//				password = scanner.next();
//			} else {
//				char[] passwdArray = console.readPassword("%s", "password: ");
//				password = new String(passwdArray);
//			}
//		}
	}

	private void loginAsUser() {
		enterPassWord();
		UserMapper um = session.getMapper(UserMapper.class);
		User user = um.getUser(username);
		while ()

	}
	private void loginAsAdmin() {
		AdministratorMapper am = session.getMapper(AdministratorMapper.class);
		Administrator admin = am.getAdministrator(username);

	}
	public void login() {
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
			case 1: {loginAsUser(); break;}
			case 2: {loginAsAdmin(); break;}
			default: {
				System.out.println("please choose the right choice");
				break;
			}
		}
	}
}
