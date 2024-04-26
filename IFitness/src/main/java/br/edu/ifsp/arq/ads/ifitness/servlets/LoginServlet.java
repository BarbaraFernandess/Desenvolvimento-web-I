package br.edu.ifsp.arq.ads.ifitness.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.ads.ifitness.model.daos.UserDao;
import br.edu.ifsp.arq.ads.ifitness.model.entities.User;
import br.edu.ifsp.arq.ads.ifitness.utils.SearcherDataSource;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		public LoginServlet() {
			super();
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// obter dados
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			UserDao userDao = new UserDao(SearcherDataSource.getInstance().getDataSource());
			Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
			String url;
			if(optional.isPresent()) {
				User user = optional.get();
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(10);
				session.setAttribute("user", user);
				url = "/homeServlet";
			}else {
				req.setAttribute("result", "loginError");
				url = "/login.jsp";
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		}
	}






