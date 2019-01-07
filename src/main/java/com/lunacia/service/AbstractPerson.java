package com.lunacia.service;

import com.lunacia.dao.UserMapper;
import com.lunacia.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.Console;
import java.util.Scanner;

public abstract class AbstractPerson {
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private String username, password;

	public void enterPassword() {
		Console console = System.console();
		Scanner scanner = new Scanner(System.in);
		if (console == null) {
			System.out.println("password: ");
			password = scanner.next();
		} else {
			char[] passwdArray = console.readPassword("%s", "password: ");
			password = new String(passwdArray);
		}
	}

	public abstract void changePassword();
}

