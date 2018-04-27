package com.sw.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.sw.servlets.Logout;
import com.sw.beans.Member;

public class TestLogout extends Mockito
{
	private Logout logout;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private Member member;
	
	@Before
	public void setUp()
	{
		logout = new Logout();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		session = new MockHttpSession();
		member = new Member();
	}
	
	@Test
	public void successfullLogout()
	{
		session.setAttribute("currentUser", member);
		request.setSession(session);
		try 
		{
			logout.doPost(request, response);
			assertEquals("./welcome.jsp", response.getRedirectedUrl());
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void errorLogout()
	{
		try 
		{
			logout.doPost(request, response);
			assertEquals(null, response.getRedirectedUrl());
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
