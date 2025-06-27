package com.nit.mv.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.nit.mv.dao.UserAccountDAO;
import com.nit.mv.pojo.UserAccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserAccountServlet")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession(); //background HSLH.sessionCreated() is executed
		
		UserAccountDAO accountDAO = 
				(UserAccountDAO)session.getAttribute("accountDAO");
		
		String button = request.getParameter("button");
		
		switch (button) {
			case "Register":  {
				//Creating new instance of UserAccount
				UserAccount account = new UserAccount();
				
				//Initialising new account with current request parameters
				account.setUserId(Long.parseLong(request.getParameter("userid")));
				account.setUsername(request.getParameter("username"));
				account.setPassword(request.getParameter("password"));
				account.setEmail(request.getParameter("email"));
				account.setMobile(Long.parseLong(request.getParameter("mobile")));
				
				//inserting or creating an account with this request details
				boolean created = accountDAO.create(account);
				
				if(created) {
				  //success page
					request.setAttribute("success", "Account is created successfully");
					request.getRequestDispatcher("./success.jsp").forward(request, response);
				}else {
				  //error page
					request.setAttribute("error", "Account creation is failed");
					request.getRequestDispatcher("./error.jsp").forward(request, response);
				}
				break;
			}//case register close
			
			case "Login": {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				long userid = accountDAO.login(username, password);
				
				if(userid != -1) {
					//success page
					session.setAttribute("userid", userid);
					request.getRequestDispatcher("./home.jsp").forward(request, response);
				}else {
					//error page
					request.setAttribute("username", username);
					request.setAttribute("failed", "User is not found with the given username and password");
					request.getRequestDispatcher("./login.jsp").forward(request, response);
				}
				break;
			}//case login close
			
			case "Get User Details": {
				Object userid = session.getAttribute("userid");
				if(userid != null) {
					UserAccount account = accountDAO.getAccount((long)userid);
					if(account != null) {
						//display page
						request.setAttribute("account", account);
						request.getRequestDispatcher("./accountdisplay.jsp").forward(request, response);
						break;
					}
				}
				
				//error page
				request.setAttribute("error", "User is not found");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
				
				break;
			}//case get Account details
			
			case "Get All Users": {
				ArrayList<UserAccount> accountsList = accountDAO.getAccounts();
				
				if(accountsList != null && !accountsList.isEmpty()) {
					//display page
					request.setAttribute("accountsList", accountsList);
					request.getRequestDispatcher("./accountdisplay.jsp").forward(request, response);
				}else {
					//error page
					request.setAttribute("error", "No Users found");
					request.getRequestDispatcher("./error.jsp").forward(request, response);
				}
				
				break;
			}//case Get All Users
	
			case "Update" :{
				Object userid = session.getAttribute("userid");
				if(userid != null) {
					UserAccount account = accountDAO.update((long)userid);
					if(account!=null) {	
						//success page
						request.setAttribute("update", account);
						request.getRequestDispatcher("./update.jsp").forward(request, response);
						break;
					}
				}
				//error page
				request.setAttribute("error", "User is not found");	
				request.getRequestDispatcher("./error.jsp").forward(request, response);

				break;
			}//case Update close
			
			case "Save" :{
				Object userid = session.getAttribute("userid");
				if(userid != null) {

					//Updating user details 
					UserAccount account = new UserAccount();
	
					//Initializing new account with current request parameters
					account.setUserId((long)userid);
					account.setPassword(request.getParameter("password"));
					account.setEmail(request.getParameter("email"));
					account.setMobile(Long.parseLong(request.getParameter("mobile")));
					
					//inserting or creating an account with this request details
					boolean updated = accountDAO.save(account);
					
					if(updated) {
					  //success page
						request.setAttribute("success", "Account is updated successfully");
						request.getRequestDispatcher("./success.jsp").forward(request, response);
						break;
					}
				}
				
				//error page
				request.setAttribute("error", "Account is not updated");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
				break;
			}//case Save close
			case "Delete" :{
				Object userid = session.getAttribute("userid");
				if(userid != null) {

					boolean deleted = accountDAO.delete((long)userid);

					if(deleted) {
					  //success page
						session.removeAttribute("userid");
						request.setAttribute("success", "User is deleted successfully");
						request.getRequestDispatcher("./success.jsp").forward(request, response);
						break;
					}
				}				
				//error page
				request.setAttribute("error", "User is not found");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
					
				break;
			}//case Delete close
			default:
				//error page
				request.setAttribute("error", "Unsupported operation");
				request.getRequestDispatcher("./error.jsp").forward(request, response);
		}
	}
}