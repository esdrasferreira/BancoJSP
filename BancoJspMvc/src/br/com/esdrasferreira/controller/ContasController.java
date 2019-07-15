package br.com.esdrasferreira.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.esdrasferreira.dao.ContaDao;
import br.com.esdrasferreira.dao.PoupancaDao;
import br.com.esdrasferreira.dao.UsuarioDao;
import br.com.esdrasferreira.entity.Conta;
import br.com.esdrasferreira.entity.Poupanca;
import br.com.esdrasferreira.entity.Usuario;
import br.com.esdrasferreira.service.calculos.Calculo;

@WebServlet({ "/ContasController", "/conta" })
public class ContasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(true);
		RequestDispatcher requestDispatcher = null;

		String comando = request.getParameter("comando");

		ContaDao contaDao = null;
		Usuario user = null;
		UsuarioDao userDao = null;
		Conta ct = null;
		Poupanca poupanca = null;
		PoupancaDao ppDao = null;
		Calculo calculo = new Calculo();

		int idConta = (Integer) sessao.getAttribute("conta");

		if (comando == null)
			comando = "saldo";

		try {
			contaDao = new ContaDao();
			userDao = new UsuarioDao();
			ppDao = new PoupancaDao();
			
			
			if (comando.equals("saldo")) {

				user = userDao.getUser(idConta);
				poupanca = ppDao.saldo(idConta);
				ct = contaDao.getConta(idConta);

				request.setAttribute("pp", poupanca);
				request.setAttribute("ct", ct);
				request.setAttribute("usuario", user);
				requestDispatcher = request.getRequestDispatcher("saldo.jsp");

			} else if (comando.equals("depositar")) {
				String deposito = request.getParameter("deposito");
				double dposito = Double.parseDouble(deposito);
				ct = contaDao.getConta(idConta);

				if (dposito > 0) {
					
					calculo.depositarCC(dposito, ct);

					request.setAttribute("conta", ct);

					requestDispatcher = request.getRequestDispatcher("/conta?comando=update");
				} else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}

			} else if (comando.equals("update")) {

				ct = (Conta) request.getAttribute("conta");

				contaDao.update(ct);

				requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");

			} else if (comando.equals("sacar")) {
				String sque = request.getParameter("saque");
				double saque = Double.parseDouble(sque);
				ct = contaDao.getConta(idConta);
				if (saque < ct.getSaldo()) {
					
					calculo.sacarCC(saque, ct);
					request.setAttribute("conta", ct);

					requestDispatcher = request.getRequestDispatcher("/conta?comando=update");
				}else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}

			} else if (comando.equals("parapoupanca")) {
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				ct = contaDao.getConta(idConta);
				poupanca = ppDao.saldo(idConta);
				if(valor < ct.getSaldo()) {
					
					calculo.paraPoupanca(valor, ct, poupanca);
					
					request.setAttribute("poupanca", poupanca);
					request.setAttribute("conta", ct);

					requestDispatcher = request.getRequestDispatcher("/conta?comando=updatePPcc");
					
				}else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}
				
				
			} else if(comando.equals("updatePPcc")) {
				ct = (Conta) request.getAttribute("conta");
				poupanca = (Poupanca) request.getAttribute("poupanca");
				
				contaDao.update(ct);
				ppDao.update(poupanca);
				
				requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				
			}else if (comando.equals("paraconta")) {
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				ct = contaDao.getConta(idConta);
				poupanca = ppDao.saldo(idConta);
				if(valor < poupanca.getSaldo()) {
					
					calculo.paraConta(valor, ct, poupanca);
					
					request.setAttribute("poupanca", poupanca);
					request.setAttribute("conta", ct);

					requestDispatcher = request.getRequestDispatcher("/conta?comando=updatePPcc");
					
				}else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}
				
				
			}else if (comando.equals("transferir")) {
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				String contaID = request.getParameter("contaID");
				int id = Integer.parseInt(contaID);
				ct = contaDao.getConta(idConta);
				
				if(valor < ct.getSaldo()) {
					contaDao.transferencia(id, valor);
					
					request.setAttribute("saque", valor);
					requestDispatcher = request.getRequestDispatcher("/conta?comando=sacar");
									
				}else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}
				
				
				
				
			}

			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
