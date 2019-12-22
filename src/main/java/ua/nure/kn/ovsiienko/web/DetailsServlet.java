package ua.nure.kn.ovsiienko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsServlet extends HttpServlet{
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("backButton") != null) {
            req.getSession(true).removeAttribute("user");
            req.getRequestDispatcher("/browse").forward(req, resp);
        } else {
            req.getRequestDispatcher("/details.jsp").forward(req, resp);
        }
    }
}
