package com.sw.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



// TODO First next Day Set this filter before MemberDashboardServlet

@WebFilter({"/MemberDashboardServlet", "/deleteMember"})
public class SessionFilter implements Filter
{
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("SessionFilter()");
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		System.out.println("Hello from doFilter() SessionFilter: Check before MemberDashboardServlet");
		// pass the request along the filter chain
		//chain.doFilter(request, response);
		
		
		// Check for valid session 
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		//HttpSession session = servletRequest.getSession();
		
		HttpServletResponse servletResponse =  (HttpServletResponse) response;
		
	
		if(servletRequest.getSession().getAttribute("currentUser") == null)
		{
			System.out.println("No Session");
			servletResponse.sendRedirect("./welcome.jsp");
			//servletRequest.getRequestDispatcher("").forward(request, response);
		} 
		else 
		{
			System.out.println("Session alive! SessionFilter()");
			System.out.println(servletRequest.getSession().getAttribute("currentUser"));
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}

}
