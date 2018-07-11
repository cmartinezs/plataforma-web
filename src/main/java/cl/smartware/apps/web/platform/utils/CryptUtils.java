package cl.smartware.apps.web.platform.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtils
{
	private static final BCryptPasswordEncoder CRYPT_ENCODER = new BCryptPasswordEncoder();

	public static String encodePassword(String password)
	{
		return CRYPT_ENCODER.encode(password);
	}
	
	public static void main(String[] args)
	{
		System.out.println("123 = " + encodePassword("123") );
	}
}
