package com.sw.test.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.sw.servlets.Login;

public class TestLogin extends Mockito
{
	
	private Login login;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Before
	public void setUp()
	{
		login = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void errorLoginServlet() throws Exception
	{
		request.addParameter("username", "scott");
		request.addParameter("pwd", "tiger");
		
		login.doPost(request, response);
		System.out.println(response.getContentType());
		
		assertEquals("./welcome.jsp", response.getRedirectedUrl());
	}
	
	@Test
	public void successfullLoginServlet() throws Exception
	{
		request.addParameter("username", "admin");
		request.addParameter("pwd", "admin");
		
		login.doPost(request, response);

		assertEquals("./index.jsp", response.getRedirectedUrl());
	}
}
