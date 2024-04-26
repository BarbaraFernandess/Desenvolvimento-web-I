package br.edu.ifsp.arq.ads.ifitness.servlets;

import java.io.IOException;
import java.time.LocalDate;

import br.edu.ifsp.arq.ads.ifitness.model.daos.ActivityDao;
import br.edu.ifsp.arq.ads.ifitness.model.entities.Activity;
import br.edu.ifsp.arq.ads.ifitness.model.entities.ActivityType;
import br.edu.ifsp.arq.ads.ifitness.model.entities.User;
import br.edu.ifsp.arq.ads.ifitness.utils.SearcherDataSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/activityRegister")
public class ActivityRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		ActivityType type = ActivityType.valueOf(req.getParameter("type"));
		LocalDate date = LocalDate.parse(req.getParameter("date"));
		Double distance = Double.parseDouble(req.getParameter("distance"));
		Integer duration = Integer.parseInt(req.getParameter("duration"));

		String url;
		HttpSession session = req.getSession(false);
		if(session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			User user = (User)session.getAttribute("user");
			ActivityDao activityDao = new ActivityDao(SearcherDataSource.getInstance().getDataSource());
			Activity activity = new Activity();
			activity.setType(type);
			activity.setDate(date);
			activity.setDistance(distance);
			activity.setDuration(duration);
			activity.setUser(user);
			if(id == 0) {
				if(activityDao.save(activity)) {
					req.setAttribute("result", "registered");
				}
			}else {
				activity.setId(id);
				if(activityDao.update(activity)) {
					req.setAttribute("result", "registered");
				}
			}
			url = "/activity-register.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		Long activityId = Long.parseLong(req.getParameter("activity-id"));
		
		String url = null;
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			ActivityDao activityDao = new ActivityDao(SearcherDataSource.getInstance().getDataSource());
			Activity activity = activityDao.getActivitiesById(activityId); 
			if(activity != null) {
				if(action.equals("update")) {
					req.setAttribute("activity", activity);
					url = "/activity-register.jsp";
				}
				if(action.equals("delete")) {
					activityDao.delete(activity);
					url = "/homeServlet";
				}
			}else {
				url = "/homeServlet";
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}