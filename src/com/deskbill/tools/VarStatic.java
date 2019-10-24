package com.deskbill.tools;

/**
 * 静态常量
 * @author admin
 *
 */
public class VarStatic {
	/**
	 * 图片路径前缀
	 */
	public static final String PREFIX ;//= "D:\\Server_copy\\deskbill\\src\\resource\\img\\";
	
	static {
		String path = System.getProperty("user.dir");;
		PREFIX = path+"\\";
	}
	
}
