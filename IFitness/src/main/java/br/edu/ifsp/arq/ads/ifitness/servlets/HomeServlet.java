package br.edu.ifsp.arq.ads.ifitness.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arq.ads.ifitness.model.daos.ActivityDao;
import br.edu.ifsp.arq.ads.ifitness.model.entities.Activity;
import br.edu.ifsp.arq.ads.ifitness.model.entities.User;
import br.edu.ifsp.arq.ads.ifitness.utils.SearcherDataSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url;
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			User user = (User)session.getAttribute("user");
			ActivityDao activityDao = new ActivityDao(SearcherDataSource.getInstance().getDataSource());
			List<Activity> userActivities = activityDao.getActivitiesByUser(user);
			req.setAttribute("userActivities", userActivities);
			req.setAttribute("name", user.getName());
			url = "/home.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}