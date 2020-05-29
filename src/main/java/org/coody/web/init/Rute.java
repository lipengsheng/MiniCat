package org.coody.web.init;

import java.util.Scanner;

import org.coody.framework.minicat.CoreApp;
import org.coody.framework.minicat.config.MiniCatConfig;
import org.coody.web.filter.BaseFilter;
import org.coody.web.filter.GeneralFilter;
import org.coody.web.servlet.MiniCatTestServlet;
import org.coody.web.servlet.MultipartServlet;
import org.coody.web.servlet.TestServlet;

public class Rute {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new java.util.Scanner(System.in);
		System.out.println("请输入监听端口:");
		Integer port = sc.nextInt();
		System.out.println("请选择运行模式:");
		System.out.println("1、Bio");
		System.out.println("2、Nio");
		Integer model = sc.nextInt();
		MiniCatConfig.MODEL = model;
		MiniCatConfig.HTTP_PORT = port;
		CoreApp.init(TestServlet.class, MiniCatTestServlet.class, MultipartServlet.class, BaseFilter.class,
				GeneralFilter.class);
	}
}
