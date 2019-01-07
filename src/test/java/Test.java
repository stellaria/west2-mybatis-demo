import com.lunacia.dao.AdministratorMapper;
import com.lunacia.dao.UserMapper;
import com.lunacia.domain.Administrator;
import com.lunacia.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Console;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
//import java.security.MessageDigest;
//
//import java.io.InputStream;
//import java.util.LinkedHashSet;
//import java.util.Scanner;

class Encrypt {
	public String getHash(String source) {
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes()); // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要   (为什么需要先使用update方法   有的md5方法中怎么不使用？)
			byte[] encryptStr = md.digest(); // 获得密文完成哈希计算,产生128 位的长整数
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
				byte byte0 = encryptStr[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			return new String(str); // 换后的结果转换为字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}

public class Test {

	public static void main (String[] args) throws Exception {
		Encrypt md = new Encrypt();
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
//		System.out.println(is);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession session = sessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		AdministratorMapper adMapper = session.getMapper(AdministratorMapper.class);
//		adMapper.insertAdministrator(admin);
		User user1 = new User();
		user1.setUsername("tiefblau");
		user1.setPassword(md.getHash("tiefblau"));
		user1.setGender("f");
		adMapper.insertUser(user1);
		User user2 = new User();
		user2.setUsername("primus");
		user2.setPassword(md.getHash("primus"));
		user2.setGender("f");
		adMapper.insertUser(user2);
		user2.setUsername("stella");
		user2.setPassword(md.getHash("stella"));
		user2.setGender("m");
		adMapper.insertUser(user2);
		Administrator admin = new Administrator();
		admin.setUsername("lunacia");
		admin.setPassword(md.getHash("lunacia"));
		adMapper.insertAdministrator(admin);
//		System.out.println(user);
//		LinkedHashSet<User> set = new LinkedHashSet<User>();
//		set = adMapper.getUsers();
//		System.out.println(set);
		session.commit();
		session.close();
//		String name, password;
//		Scanner scan = new Scanner(System.in);
//		name = scan.next();
//		password = scan.next();
//		System.out.println(name + " " + password);
	}
}
