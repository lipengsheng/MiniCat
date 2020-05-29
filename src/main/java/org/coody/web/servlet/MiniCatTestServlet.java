package org.coody.web.servlet;

import java.io.IOException;

import org.coody.framework.minicat.annotation.Servlet;
import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;
import org.coody.framework.minicat.servlet.HttpServlet;

@Servlet("/test.do")
public class MiniCatTestServlet extends HttpServlet{


	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getOutputStream().write("hello");
	}

}
