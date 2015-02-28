package org.iiitb.interceptor;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticateInterceptor implements Interceptor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799348281841811478L;

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{

		SessionMap<String, Object> session = (SessionMap<String, Object>)actionInvocation.getInvocationContext().getSession();

		if (session.get("userName") == null)
		{
			//System.out.println("login interceptor");
			return ActionSupport.LOGIN;
			
		}
		return actionInvocation.invoke();
	}

}
