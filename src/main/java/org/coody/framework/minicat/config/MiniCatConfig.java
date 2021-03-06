package org.coody.framework.minicat.config;

public class MiniCatConfig {
	/**
	 * Session超时时间
	 */
	public static Integer SESSION_TIMEOUT = 60 * 1000 * 10;
	
	/**
	 * MiniCat HTTP线程数量
	 */
	public static Integer HTTP_THREAD_NUM = 500;
	
	/**
	 * MiniCat 内务线程数量
	 */
	public static Integer MINICAT_THREAD_NUM = 20;
	
	/**
	 * MiniCat端口
	 */
	public static Integer HTTP_PORT=80;
	
	/**
	 * HttpSocket超时时间
	 */
	public static Integer HTTP_SO_TIMEOUT=3000;
	
	/**
	 * HTTP SessionId字段名
	 */
	public static String SESSION_ID_FIELD_NAME="COODYSESSID";
	
	/**
	 * 全局编码
	 */
	public static String ENCODE="UTF-8";
	
	/**
	 * 打开Gzip
	 */
	public static boolean OPENGZIP=true;
	
	/**
	 * 模式 1Bio  2Nio
	 */
	public static Integer MODEL=2;
	
	/**
	 * 最大Head长度
	 */
	public static Integer MAX_HEADER_LENGTH=8192;
	/**
	 * 首页
	 */
	public static String WELCOME_PATH="/index.do";
}
