package org.coody.web.servlet;

import java.io.IOException;

import org.coody.framework.minicat.annotation.Servlet;
import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;
import org.coody.framework.minicat.servlet.HttpServlet;

@Servlet("/index.do")
public class TestServlet extends HttpServlet{

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String name=request.getParament("name");
		response.getOutputStream().write("Hello,I'm "+name);
		
	}

	
	
}
