package com.lunacia;

import com.lunacia.dao.UserMapper;
import com.lunacia.domain.Administrator;
import com.lunacia.domain.User;
import com.lunacia.service.AdminConnection;
import com.lunacia.service.Connection;
import com.lunacia.service.LoginManager;
import com.lunacia.service.UserConnection;

import java.io.IOException;
import java.util.Scanner;

public class dbDemo {
	public static int menu() {
		System.out.println("-------------first-class menu-------------");
		System.out.println("1. sign in");
		System.out.println("2. login as user");
		System.out.println("3. login as administrator");
		System.out.println("4. quit");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		while (i != 1 && i != 2 && i != 3 && i != 4) {
			i = scan.nextInt();
		}
		return i;
	}
	public static void main (String[] args) throws IOException {
		LoginManager lm = new LoginManager();
		int i = menu();
		Scanner scan = new Scanner(System.in);
		while (i != 4) {
			switch (i) {
				case 1: {
					lm.signInAsUser();
					break;
				}case 2: {
					UserConnection uc = new UserConnection(lm.loginAsUser());
					int j = uc.menu();
					while (j != 3) {
						switch(j) {
							case 1: {
								uc.changePassword();
								break;
							}case 2: {
								uc.changeInfo();
								break;
							}default: {
								break;
							}
						}
						j = uc.menu();
					}
				}case 3: {
					AdminConnection ac = new AdminConnection(lm.loginAsAdmin());
					int j = ac.menu();
					while (j != 5) {
						switch(j) {
							case 1: {
								ac.changePassword();
								break;
							}case 2: {
								ac.showUsers();
								break;
							}case 4: {
								ac.deleteUser();
								break;
							}case 3: {
								ac.addAdmin();
								break;
							}default: {
								break;
							}
						}
						j = ac.menu();
					}
				}
			}
			i = menu();
		}

	}
}
