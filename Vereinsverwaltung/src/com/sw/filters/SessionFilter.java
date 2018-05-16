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



// TODO First next Day Set this filter before MemberDashboardServlet

@WebFilter({"/ChangePassword", "/ChangePasswordServlet", "/DashboardServlet", "/InventoryDashboardServlet", "/InventoryRegisterServlet", "/Logout", "/MemberDashboardServlet", "/MemberDeleteServlet", "/MemberEditServlet", "/MemberEditServletSave", "/MemberRegisterServlet", "/RoleServlet", "/RoleAddServlet", "/RoleDeleteServlet", "/RoleEditServlet"})
public class SessionFilter implements Filter
{
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("SessionFilter() - Konstruktor");
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		System.out.println("Hello from doFilter() SessionFilter");
		// Check for valid session 
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse =  (HttpServletResponse) response;
		
	
		if(servletRequest.getSession().getAttribute("currentUser") == null)
		{
			System.out.println("No Session");
			servletRequest.getRequestDispatcher("./welcome.jsp").forward(servletRequest, servletResponse);
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
