

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="javax.servlet.*" import="java.sql.*"
	import="java.util.*" import="br.com.esdrasferreira.entity.*"
	import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Produtos</title>
</head>
<body>

	<%
		Usuario user = (Usuario) request.getAttribute("usuario");
	%>



	<div class="container">
		<div class="row">
			<nav class="navbar navbar-sticky-top navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Produtos</span>
			</nav>
		</div>
		<div class="row">
			<br />
			<h6 style="color: blue;">
				Usuário logado:
				<%=user.getNome()%>
			</h6>
		</div>

	</div>



	<div class="container">
		<div class="row">
			<div class="col-3">
				<table class="table table-bordered table-dark">
					<thead class="thead-dark">
						<tr>
							<th scope="col" align="left">Conta</th>
							<th scope="col" align="right">Saldo</th>

						</tr>
					</thead>

					<tbody>
						<%
							Conta conta = (Conta) request.getAttribute("ct");
						%>
						<tr>
							<td><%=conta.getConta()%></td>
							<td><%=conta.getSaldo()%></td>

						</tr>


					</tbody>
				</table>
			</div>

			<div class="row">
				<div class="col">
					<table class="table table-bordered table-dark">
						<thead class="thead-dark">
							<tr>
								<th scope="col" align="left">Poupança</th>
								<th scope="col" align="right">Saldo</th>

							</tr>
						</thead>

						<tbody>
							<%
								Poupanca poupanca = (Poupanca) request.getAttribute("pp");
							%>
							<tr>
								<td><%=poupanca.getContaCorrente_idconta()%></td>
								<td><%=poupanca.getSaldo()%></td>

							</tr>


						</tbody>
					</table>
				</div>
			</div>



		</div>
	</div>

	<br>

	<form action="conta" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">deposito</label> <input
						type="number" min="1" class="form-control" name="deposito"
						placeholder="R$" value="">
					<button type="submit" class="btn btn-primary mb-2">Depositar</button>
					<input type="hidden" name="comando" value="depositar">
				</div>
			</div>

		</div>

	</form>

	<form action="conta" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">saque</label> <input
						type="number" min="1" class="form-control" name="saque"
						placeholder="R$" value="">
					<button type="submit" class="btn btn-primary mb-2">Sacar</button>
					<input type="hidden" name="comando" value="sacar">
				</div>
			</div>

		</div>

	</form>
	<form action="conta" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">poupanca</label> <input
						type="number" min="1" class="form-control" name="valor"
						placeholder="R$" value="">
					<button type="submit" class="btn btn-primary mb-2">Transferir
						para poupança</button>
					<input type="hidden" name="comando" value="parapoupanca">
				</div>
			</div>

		</div>

	</form>
	<form action="conta" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">
					<label for="inputPassword2" class="sr-only">conta</label> <input
						type="number" min="1" class="form-control" name="valor"
						placeholder="R$" value="">
					<button type="submit" class="btn btn-primary mb-2">Transferir
						para conta corrente</button>
					<input type="hidden" name="comando" value="paraconta">
				</div>
			</div>

		</div>

	</form>
	<form action="conta" method="get">
		<div class="container">

			<div class="form-inline">
				<div class="row">

					<label for="inputPassword2" class="sr-only">conta</label> <input
						type="number" min="1" class="form-control" name="valor"
						placeholder="R$" value="">
					<button type="submit" class="btn btn-dark" disabled="disabled">Transferir
						para conta numero:</button>
					<input type="hidden" name="comando" value="transferir"> <input
						type="number" min="1" class="form-control" name="contaID"
						placeholder="número da conta" value="">
					<button type="submit" class="btn btn-dark">TRANSFERIR</button>

				</div>
			</div>

		</div>

	</form>

	<div class="container">
		<div class="row">
			<br />
		</div>
		<div class="row" style="font-style: italic;">
			<a class="btn btn-primary" href="login?parametro=logout"
				role="button">Logout</a>
		</div>
	</div>




</body>
</html>