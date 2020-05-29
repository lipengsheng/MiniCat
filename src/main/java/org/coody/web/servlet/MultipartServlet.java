package org.coody.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.coody.framework.minicat.annotation.Servlet;
import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;
import org.coody.framework.minicat.servlet.HttpServlet;
import org.coody.framework.minicat.util.StringUtil;

@Servlet("/upload.do")
public class MultipartServlet extends HttpServlet {

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, List<Object>> map = request.getParams();
		if (StringUtil.isNullOrEmpty(map)) {
			response.getOutputStream().write("未传递任何参数");
			return;
		}
		for (String key : map.keySet()) {
			List<Object> paramValues = map.get(key);
			if (StringUtil.isNullOrEmpty(paramValues)) {
				response.getOutputStream().write(key + ">>null");
				response.getOutputStream().write("<br>");
				continue;
			}
			for (Object obj : paramValues) {
				response.getOutputStream().write(key + ">>" + obj.toString());
				response.getOutputStream().write("<br>");
				continue;
			}
		}
	}

}
