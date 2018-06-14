package com.examresults;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "LoginValidator", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    private static final Logger SECURITY_SUCCESS = Logger.getLogger(Login.class.getName());
    private static final Logger SECURITY_FAILURE = Logger.getLogger(Login.class.getName());

    private Database db = new Database();

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("txtuser");
        String password = request.getParameter("txtpwd");
        // LOGGER.log(Level.INFO, "Received credentials: " + username + ":" + password);
        if (db.openConnection()) {

            if (db.checkLogin(username, password)) {

                SECURITY_SUCCESS.log(Level.INFO, "Login successful for: " + username);
                HttpSession session = request.getSession();
                session.setAttribute("userloggedin", username);
                response.sendRedirect("Home.jsp");
                return;
            } else {

                SECURITY_FAILURE.log(Level.SEVERE, "Login Failed for: " + username);
                request.setAttribute("error", "Invalid Username or Password");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            }
        }
        db.closeConnection();
    }
}
