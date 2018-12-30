package com.lunacia.dao;
import com.lunacia.domain.*;

import java.util.LinkedHashSet;

public interface AdministratorMapper {
	Administrator getAdministrator(String username);
	void insertAdministrator(Administrator admin);
	LinkedHashSet<User> getUsers();
	void insertUser(User user);
	void deleteUser(User user);
}
