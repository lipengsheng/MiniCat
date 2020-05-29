package org.coody.framework.minicat.servlet;

import java.io.IOException;

import org.coody.framework.minicat.entity.ApplicationFilterChain;
import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;

public abstract class HttpFilter extends MiniCatHttpPart{
	
	private String mapping;
	
	

	public String getMapping() {
		return mapping;
	}



	public void setMapping(String mapping) {
		this.mapping = mapping;
	}



	public abstract void doFilter(HttpServletRequest request,HttpServletResponse response,ApplicationFilterChain chain) throws IOException;
}
