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

		
		Usuario user = null;
		Conta ct = null;
		Poupanca poupanca = null;
		Calculo calculo = new Calculo();

		int idConta = (Integer) sessao.getAttribute("conta");

		if (comando == null)
			comando = "saldo";

		try {
			

			if (comando.equals("saldo")) {
				UsuarioDao userDao = new UsuarioDao();
				PoupancaDao ppDao = new PoupancaDao();
				ContaDao contaDao = new ContaDao();
				
				user = userDao.getUser(idConta);
				poupanca = ppDao.saldo(idConta);
				ct = contaDao.getConta(idConta);

				request.setAttribute("pp", poupanca);
				request.setAttribute("ct", ct);
				request.setAttribute("usuario", user);
				System.out.println("resquest to saldo.jsp vindo do comando=saldo");
				requestDispatcher = request.getRequestDispatcher("saldo.jsp");

			} else if (comando.equals("depositar")) {
				ContaDao contaDao = new ContaDao();
				
				String deposito = request.getParameter("deposito");
				double dposito = Double.parseDouble(deposito);
				ct = contaDao.getConta(idConta);

				if (dposito > 0) {

					calculo.depositarCC(dposito, ct);

					request.setAttribute("conta", ct);
					System.out.println("resquest to comando=update vindo do sepositar");
					requestDispatcher =request.getRequestDispatcher("/conta?comando=update");
				} else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}

			} else if (comando.equals("update")) {
				ContaDao contaDao = new ContaDao();
				ct = (Conta) request.getAttribute("conta");

				contaDao.update(ct);
				System.out.println("resquest to comando=saldo vindo do update");
				requestDispatcher =request.getRequestDispatcher("/conta?comando=saldo");

			} else if (comando.equals("sacar")) {
				ContaDao contaDao = new ContaDao();
				
				String sque = request.getParameter("saque");
				String s = (String) request.getAttribute("saque");
				ct = contaDao.getConta(idConta);
				if (sque != null) {
					double saque = Double.parseDouble(sque);

					if (saque < ct.getSaldo()) {

						calculo.sacarCC(saque, ct);
						request.setAttribute("conta", ct);
						System.out.println("resquest to comando=update vindo do SACAR");
						requestDispatcher =request.getRequestDispatcher("/conta?comando=update");
					} else {
						requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
					}

				} else {
					double saque = Double.parseDouble(s);
					if (saque < ct.getSaldo()) {

						calculo.sacarCC(saque, ct);
						request.setAttribute("conta", ct);
						System.out.println("resquest to comando=update vindo do SACAR e do Transferir");
						requestDispatcher =request.getRequestDispatcher("/conta?comando=update");
					} else {
						requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
					}
				}

			} else if (comando.equals("parapoupanca")) {
				ContaDao contaDao = new ContaDao();
				PoupancaDao ppDao = new PoupancaDao();
				
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				ct = contaDao.getConta(idConta);
				poupanca = ppDao.saldo(idConta);
				if (valor < ct.getSaldo()) {

					calculo.paraPoupanca(valor, ct, poupanca);

					request.setAttribute("poupanca", poupanca);
					request.setAttribute("conta", ct);
					System.out.println("resquest to comando=updatePPcc vindo parapoupanca");
					requestDispatcher =	request.getRequestDispatcher("/conta?comando=updatePPcc");

				} else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}

			} else if (comando.equals("updatePPcc")) {
				ContaDao contaDao = new ContaDao();
				PoupancaDao ppDao = new PoupancaDao();
				
				ct = (Conta) request.getAttribute("conta");
				poupanca = (Poupanca) request.getAttribute("poupanca");

				contaDao.update(ct);
				ppDao.update(poupanca);
				System.out.println("resquest to comando=saldo vindo updatePPcc");
				requestDispatcher =request.getRequestDispatcher("/conta?comando=saldo");

			} else if (comando.equals("paraconta")) {
				ContaDao contaDao = new ContaDao();
				PoupancaDao ppDao = new PoupancaDao();
				
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				ct = contaDao.getConta(idConta);
				poupanca = ppDao.saldo(idConta);
				if (valor < poupanca.getSaldo()) {

					calculo.paraConta(valor, ct, poupanca);

					request.setAttribute("poupanca", poupanca);
					request.setAttribute("conta", ct);
					System.out.println("resquest to comando=updatePPcc vindo paraconta");
					requestDispatcher =request.getRequestDispatcher("/conta?comando=updatePPcc");

				} else {
					requestDispatcher = request.getRequestDispatcher("/conta?comando=saldo");
				}

			} else if (comando.equals("transferir")) {
				ContaDao contaDao = new ContaDao();
				
				String vl = request.getParameter("valor");
				double valor = Double.parseDouble(vl);
				String contaID = request.getParameter("contaID");
				int id = Integer.parseInt(contaID);

				Conta ct2 = new Conta();
				ct2 = contaDao.getConta(id);

				double total = ct2.getSaldo() + valor;

				if (valor > 0) {
					ContaDao contaDao2 = new ContaDao();
					contaDao2.transferencia(total, id);

					request.setAttribute("saque", vl);
					System.out.println("resquest to comando=sacar vindo transferir");
					requestDispatcher =request.getRequestDispatcher("/conta?comando=sacar");

				} else {
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
