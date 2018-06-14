package com.examresults;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());

    private Database db = new Database();
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("txtuser");
        String password = request.getParameter("txtpwd");
        LOGGER.log(Level.INFO, "Received credentials: " + username + ":" + password);

        if (db.openConnection()) {

            if (db.checkAdminLogin(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userloggedin", username);
                session.setAttribute("role", "admin");
                response.sendRedirect("admin/Dashboard.jsp");
                return;

            } else {
                request.setAttribute("error", "Invalid Username or Password");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);

            }
        }
        db.closeConnection();
    }

}
