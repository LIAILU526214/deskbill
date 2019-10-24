package com.deskbill.tools;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * @author admin
 *
 */
public class StringUtils {
	// 验证码字符集
    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int[] ints = {
    		4, 5, 6, 7, 8, 9};
	/**
	 * 判断字符串是否为空或null 
	 * 备注: 只有空格也表示为空
	 * @param src
	 * @return true : 是 false :不为空
	 */
	public static boolean isNull(String src) {
		return src == null || "".equals(src.trim());
	}
	
	public static boolean isEmail(String email){  
        if (null==email || "".equals(email)){
        	return false; 
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if(m.matches()){
        	return true;
        }else{
        	return false;
        }
    }
	/**
	 * 生成4-9位随机数
	 * @return
	 */
	public static String getRandomString() {
		StringBuilder sBuilder = new StringBuilder();
		int nextInt2 = ThreadLocalRandom.current().nextInt(ints.length);
		nextInt2 = ints[nextInt2];
		for (int i = 0; i < nextInt2; i++) {
			int nextInt = ThreadLocalRandom.current().nextInt(chars.length);
			sBuilder.append(chars[nextInt]);
		}
		return sBuilder.toString();
	}
	public static void main(String[] args) {
		boolean null1 = isNull("  a ");
		System.out.println(null1);
	}
}
