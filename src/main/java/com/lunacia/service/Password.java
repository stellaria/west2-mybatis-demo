package com.lunacia.service;

import java.io.Console;
import java.util.Scanner;

public class Password {
	public static String enterPassword() {
		Scanner scanner = new Scanner(System.in);
		String password;
		Console console = System.console();
		scanner = new Scanner(System.in);
		if (console == null) {
			password = scanner.next();
		} else {
			char[] passwdArray = console.readPassword();
			password = new String(passwdArray);
		}
		return password;
	}
}
