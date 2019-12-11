package ua.nure.kn.ovsiienko.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.ovsiienko.db.DaoFactory;
import ua.nure.kn.ovsiienko.db.DatabaseException;

public class BrowseServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	browse(req,resp);
}

private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Collection users;
	try {
		users = DaoFactory.getInstance().getUserDao().findAll();
		req.getSession().setAttribute("users", users);
		req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	} catch (DatabaseException e) {
		throw new ServletException(e);
	}
	
}
}
