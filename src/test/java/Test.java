//import com.lunacia.dao.AdministratorMapper;
//import com.lunacia.dao.UserMapper;
//import com.lunacia.domain.Administrator;
//import com.lunacia.domain.User;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
import java.io.Console;
import java.util.Scanner;
//import java.security.MessageDigest;
//
//import java.io.InputStream;
//import java.util.LinkedHashSet;
//import java.util.Scanner;


public class Test {
	public static void main (String[] args) throws Exception {
//		String resource = "mybatis-config.xml";
//		InputStream is = Resources.getResourceAsStream(resource);
////		System.out.println(is);
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//
//		SqlSession session = sessionFactory.openSession();
//		UserMapper userMapper = session.getMapper(UserMapper.class);
//		AdministratorMapper adMapper = session.getMapper(AdministratorMapper.class);
////		adMapper.insertAdministrator(admin);
//		User user1 = new User();
//		user1.setUsername("tiefblau");
//		user1.setPassword("jack990729");
//		user1.setGender("f");
//		adMapper.insertUser(user1);
//		User user2 = new User();
//		user2.setUsername("primus");
//		user2.setPassword("xpwanghy");
//		user2.setGender("f");
//		adMapper.insertUser(user2);
//		user2.setUsername("stella");
//		user2.setPassword("qwerty");
//		user2.setGender("m");
//		adMapper.insertUser(user2);
////		System.out.println(user);
////		LinkedHashSet<User> set = new LinkedHashSet<User>();
////		set = adMapper.getUsers();
////		System.out.println(set);
//		session.commit();
//		session.close()
		String name, password;
		Scanner scan = new Scanner(System.in);
		name = scan.next();
		password = scan.next();
		System.out.println(name + " " + password);
	}
}
