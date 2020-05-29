package org.coody.web.filter;

import java.io.IOException;

import org.coody.framework.minicat.annotation.Filter;
import org.coody.framework.minicat.entity.ApplicationFilterChain;
import org.coody.framework.minicat.entity.HttpServletRequest;
import org.coody.framework.minicat.entity.HttpServletResponse;
import org.coody.framework.minicat.servlet.HttpFilter;

@Filter("/*.do")
public class BaseFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, ApplicationFilterChain chain) throws IOException {
		//System.out.println("this is "+this.getClass().getSimpleName());
		chain.doFilter(request, response);
	}

}
