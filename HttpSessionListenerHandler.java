package com.nit.mv.listener;

import com.nit.mv.dao.UserAccountDAO;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerHandler implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se)  { 
		
		//Creating UseAccountDAO object
		UserAccountDAO accountDAO = new UserAccountDAO();
		
		//opening DB connection
		accountDAO.openConnection();
		
		//creating PreparedStatements
		accountDAO.createPreparedStatements();
		
		//adding accountDao object to session as attribute
		se.getSession().setAttribute("accountDAO", accountDAO);
		
		System.out.println("Accout object is stored in session: "+ se.getSession());
		
	}
	
    public void sessionDestroyed(HttpSessionEvent se)  {
    	UserAccountDAO  accountDAO = (UserAccountDAO)se.getSession().getAttribute("accountDAO");
    	accountDAO.closePreparedStements();
    	accountDAO.closeConnection();
    	accountDAO = null;
    	System.out.println("Accout object is unreferenced and destored from session: "+ se.getSession());
    }
	
}