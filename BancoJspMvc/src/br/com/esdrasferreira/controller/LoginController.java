package br.com.esdrasferreira.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.esdrasferreira.dao.*;
import br.com.esdrasferreira.entity.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({ "/LoginController", "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession(true);

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String parametro = request.getParameter("parametro");

		

		if (parametro.equals("login")) {

			if (nome != null && nome != "" && senha != null && senha != "") {

				

				try {
					Usuario user = new Usuario();
					UsuarioDao dao = new UsuarioDao();

					user = dao.login(nome, senha);
					
					
					if(user == null) {
						request.setAttribute("erros", "Nome ou senha inválidos.");

						request.getRequestDispatcher("login.jsp").forward(request, response);
					}

					sessao.setAttribute("nome", user.getNome());
					sessao.setAttribute("conta", user.getConta());

					request.getRequestDispatcher("/conta").forward(request, response);

				} catch (Exception e) {

					e.printStackTrace();

				}

			} else {request.setAttribute("erros", "Nome e senha são obrigatórios!");

			request.getRequestDispatcher("login.jsp").forward(request, response);

			}			

		} else if (parametro.equals("logout")) {

			sessao.setAttribute("id", null);

			response.sendRedirect("login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
