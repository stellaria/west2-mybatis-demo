package com.lunacia.dao;
import com.lunacia.domain.*;

public interface UserMapper {
	User getUser(String username);
	void updateUser(User user);
}
