package br.edu.ifsp.arq.ads.ifitness.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.ads.ifitness.model.daos.UserDao;
import br.edu.ifsp.arq.ads.ifitness.model.entities.Gender;
import br.edu.ifsp.arq.ads.ifitness.model.entities.User;
import br.edu.ifsp.arq.ads.ifitness.utils.PasswordEncode;
import br.edu.ifsp.arq.ads.ifitness.utils.SearcherDataSource;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// recuperar os dados
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String gender = req.getParameter("gender");
		// instanciar o objeto User
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(PasswordEncode.encode(password));
		user.setDateOfBirth(LocalDate.parse(dateOfBirth));
		user.setGender(Gender.valueOf(gender));
		// instanciar o objeto UserDao
		UserDao userDao = new UserDao(
				SearcherDataSource.getInstance().getDataSource());
		
		RequestDispatcher dispatcher = null;
		
		if(userDao.save(user)) {
			req.setAttribute("result", "registered");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("/user-register.jsp");
		}
		// encaminha a requisição
		dispatcher.forward(req, resp);
		
		
	}

	
}




