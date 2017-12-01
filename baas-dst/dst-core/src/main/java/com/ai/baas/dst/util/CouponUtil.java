package com.ai.baas.dst.util;

import java.util.Random;
import java.util.UUID;

import com.ai.opt.sdk.util.DateUtil;

public final class CouponUtil {
	public static String[] chars = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static void main(String[] args) {
		/*for (int i = 0; i < 100; i++) {
			System.out.println(generateShortUuid());
		}*/
		
		
		System.out.println(getRandom_Contain_N_C(4));

	}

	public static String getRandom_Contain_N_C(int length) {

		// 加上前缀
		String time=DateUtil.getDateString("yyMMddHH");
		//System.out.println(time);
		
		//

		String return_value = "";
		StringBuffer random_value = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				random_value.append((char) (choice + random.nextInt(26)));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				random_value.append(random.nextInt(10));
			}
		}
		return_value =time+ random_value.toString().trim().toUpperCase();
		return return_value;
	}
	public static String getRandom_Contain_N_C(String channelCode ,int length) {

		// 加上前缀
		String time=DateUtil.getDateString("yyMMddHH");
		//System.out.println(time);
		
		//
		String return_value = "";
		StringBuffer random_value = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				random_value.append((char) (choice + random.nextInt(26)));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				random_value.append(random.nextInt(10));
			}
		}
		return_value = channelCode+time+ random_value.toString().trim().toUpperCase();
		return return_value;
	}
}
