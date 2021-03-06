package com.svecw.oes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.svecw.oes.dao.*;
import com.svecw.oes.dto.Administrator;
import com.svecw.oes.dto.Enrollment;
import com.svecw.oes.dto.Test;
import com.svecw.oes.dto.User;
import com.svecw.oes.exception.OESException;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();

		String action = request.getParameter("action");
		if (action.equals("create_test")) {
			response.sendRedirect("createTest.jsp");
		} else if (action.equals("admin")) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("mobileNo");
			String emailId = request.getParameter("emailId");
			Administrator administrator = new Administrator(name, password, phoneNo, emailId);
			AdministratorDAO administratorDAO = new AdministratorDAO();
			try {
				if (administratorDAO.insert(administrator)) {
					request.setAttribute("successMessage", "sucessfully registered");
					request.getRequestDispatcher("CreateAdministrator.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "unsucessful");
					request.getRequestDispatcher("CreateAdministrator.jsp").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (action.equals("user")) {
			String userId = request.getParameter("userId");
			String uname = request.getParameter("name");
			String upassword = request.getParameter("password");
			String uphoneNo = request.getParameter("mobileNo");
			String uemailId = request.getParameter("emailId");
			User user = new User(userId, uname, upassword, uphoneNo, uemailId);
			UserDAO userDAO = new UserDAO();
			try {
				if (userDAO.insert(user)) {
					request.setAttribute("successMessage", "successfull");
					request.getRequestDispatcher("CreateUsers.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Unsuccessful");
					request.getRequestDispatcher("CreateUsers.jsp").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("user_login")) {
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			try {
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getUser(userId);

				if (user != null) {
					if (user.getPassword().equals(password)) {
						HttpSession session = request.getSession();
						session.setAttribute("loggedUser", user.getName());
						session.setAttribute("loggedUserID", user.getUserId());
						request.getRequestDispatcher("UserHomePage.html").forward(request, response);
					} else {
						request.setAttribute("errorMessage", "invalid password");
						request.getRequestDispatcher("signIn.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage", "invalid userid");
					request.getRequestDispatcher("signIn.jsp").forward(request, response);
				}
			} catch (OESException e) {
				request.setAttribute("errorMessage", e.toString());
				request.getRequestDispatcher("signIn.jsp").forward(request, response);
			}
		} else if (action.equals("admin_login")) {
			int adminId = Integer.parseInt(request.getParameter("adminId"));
			String password = request.getParameter("password");
			Administrator administrator = new Administrator();
			AdministratorDAO administratorDAO = new AdministratorDAO();
			administrator = administratorDAO.getAdmin(adminId);
			try {
				if (administrator != null && administrator.getPassword().equals(password)) {
					request.setAttribute("successMessage", "Welcome  " + administrator.getName());
					HttpSession session = request.getSession();
					session.setAttribute("adminId", adminId);
					request.getRequestDispatcher("AdminHomePage.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", adminId + "invalid adminid or password");
					request.getRequestDispatcher("signIn.jsp").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("enroll")) {

			try {
				UserDAO userDAO = new UserDAO();
				TestDAO testDAO = new TestDAO();
				List<User> user = new ArrayList<>();
				List<Test> tests = new ArrayList<>();
				user = userDAO.getUsers();
				tests = testDAO.getTests();
				HttpSession session = request.getSession();
				session.setAttribute("users", user);
				session.setAttribute("tests", tests);
				response.sendRedirect("EnrollUsers.jsp");

			} catch (OESException e1) {
				e1.printStackTrace();
			}
		} else if (action.equals("view")) {
			TestDAO testDAO = new TestDAO();
			List<Test> tests = new ArrayList<>();
			try {
				tests = testDAO.getTests();

				HttpSession session = request.getSession();
				if ((tests != null && tests.size() != 0)) {
					session.setAttribute("tests", tests);
					response.sendRedirect("viewTests.jsp");
				} else {
					request.setAttribute("errorMessage", "no tests available");
					request.getRequestDispatcher("viewTests.jsp").include(request, response);
				}

			} catch (OESException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("report")) {
			int testId = Integer.parseInt(request.getParameter("submit"));
			ReportDAO reportDAO = new ReportDAO();
			List<User> users = new ArrayList<>();
			try {
				users = reportDAO.getTestUsers(testId);
				ScoreDAO scoreDAO = new ScoreDAO();
				if (users != null && users.size() != 0) {
					for (User user : users) {
						user.setScore(scoreDAO.getScores(user));
					}
					HttpSession session = request.getSession();
					session.setAttribute("users", users);
					response.sendRedirect("viewReport.jsp");
				} else {
					request.setAttribute("errorMessage", "no test taken");
					request.getRequestDispatcher("viewTests.jsp").include(request, response);

				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (OESException e) {
				e.printStackTrace();
			}
		} else if (action.equals("logout")) {
			System.out.println("controller");
			try {

				httpSession.invalidate();
				request.getRequestDispatcher("signIn.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (action.equals("showenroll")) {
			String[] users = request.getParameterValues("users");
			EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
			String t = request.getParameter("tests");
			int test = 0;
			System.out.println(t);
			if (t != null && t.length()!=0) {
				test = Integer.parseInt(t);
			}
			if (test != 0 && users != null && users.length != 0) {
				Enrollment[] enrollment = new Enrollment[users.length];

				for (int i = 0; i < users.length; i++) {
					enrollment[i] = new Enrollment(users[i], test);
					enrollmentDAO.insert(enrollment[i]);

				}
				request.setAttribute("successMessage", "enrolled");
				request.getRequestDispatcher("EnrollUsers.jsp").include(request, response);

			} else {
				request.setAttribute("errorMessage", "user or test is not selected");
				request.getRequestDispatcher("EnrollUsers.jsp").include(request, response);

			}

		}
	}
}
