package org.coody.framework.minicat.servlet;

import java.io.IOException;

import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;

public abstract class HttpServlet extends MiniCatHttpPart{
	
	public abstract void doService(HttpServletRequest request,HttpServletResponse response) throws IOException;

}
