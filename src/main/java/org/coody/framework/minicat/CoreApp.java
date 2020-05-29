package org.coody.framework.minicat;

import org.coody.framework.minicat.annotation.Filter;
import org.coody.framework.minicat.annotation.Servlet;
import org.coody.framework.minicat.config.MiniCatConfig;
import org.coody.framework.minicat.container.FilterContainer;
import org.coody.framework.minicat.container.ServletContainer;
import org.coody.framework.minicat.servlet.HttpFilter;
import org.coody.framework.minicat.servlet.HttpServlet;
import org.coody.framework.minicat.servlet.MiniCatHttpPart;
import org.coody.framework.minicat.socket.BioService;
import org.coody.framework.minicat.socket.NioService;
import org.coody.framework.minicat.socket.iface.MiniCatService;
import org.coody.framework.minicat.util.StringUtil;

public class CoreApp {

	public static void init(Class<?>... clazzs) {
		long startTime = System.currentTimeMillis();
		MiniCatService miniCatService = new BioService();
		if (MiniCatConfig.MODEL == 2) {
			miniCatService = new NioService();
		}
		System.out.println("引用模式>>" + miniCatService.getClass().getName());
		try {
			if (StringUtil.isNullOrEmpty(clazzs)) {
				System.err.println("初始化Servlet为空");
				return;
			}
			// 打开端口
			miniCatService.openPort(MiniCatConfig.HTTP_PORT, MiniCatConfig.SESSION_TIMEOUT);
			System.out.println("监听端口>>" + MiniCatConfig.HTTP_PORT);
			for (Class<?> clazz : clazzs) {
				if (!MiniCatHttpPart.class.isAssignableFrom(clazz)) {
					continue;
				}
				Servlet servletFlag = clazz.getAnnotation(Servlet.class);
				if (servletFlag != null && !StringUtil.isNullOrEmpty(servletFlag.value())) {
					HttpServlet servlet = (HttpServlet) clazz.getDeclaredConstructor().newInstance();
					System.out.println("注册Servlet>>" + clazz.getName() + ">>" + servletFlag.value());
					ServletContainer.putServlet(servletFlag.value(), servlet);
				}
				Filter filterFlag=clazz.getAnnotation(Filter.class);
				if (filterFlag != null && !StringUtil.isNullOrEmpty(filterFlag.value())) {
					HttpFilter filter = (HttpFilter) clazz.getDeclaredConstructor().newInstance();
					filter.setMapping(filterFlag.value());
					System.out.println("注册Filter>>" + clazz.getName() + ">>" + filterFlag.value());
					FilterContainer.pushFilter(filter);
				}
			}
			System.out.println("MiniCat启动完成,耗时>>" + (System.currentTimeMillis() - startTime) + "ms");
			// 处理请求
			miniCatService.doService();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
