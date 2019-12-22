package ua.nure.kn.ovsiienko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.ovsiienko.db.DaoFactory;
import ua.nure.kn.ovsiienko.db.DatabaseException;
import ua.nure.kn.ovsiienko.domain.User;

public class AddServlet extends EditServlet {

	


	
	protected void showPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}

}
