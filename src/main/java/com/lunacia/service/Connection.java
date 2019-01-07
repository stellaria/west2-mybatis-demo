package com.lunacia.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Connection {
	private final static String RESOURCE = "mybatis-config.xml";
	private SqlSessionFactory sqlSessionFactory;
	public SqlSession session;

	public SqlSession getSession() throws IOException {
		InputStream is = Resources.getResourceAsStream(RESOURCE);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		session = sqlSessionFactory.openSession();
		return session;
	}

	public void close() {
		session.commit();
		session.close();
	}
}
