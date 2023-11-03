<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<html>
		<head>
		<meta charset="UTF-8">
		<title>Registrazione</title>
		</head>
	<body class="m-3 p-5">
		<h2>Pagina di registrazione</h2>
		<form method="post" action="register">
  			<div class="mb-3">
    			<label class="form-label">Nome</label>
    			<input type="text" name="nome" required class="form-control">
  			</div>
  			<div class="mb-3">
    			<label class="form-label">Password</label>
    			<input type="password" name="pass" required class="form-control">
  			</div>
  			<button type="submit" class="btn btn-primary">Registrati</button>
		</form>
		<p>Sei già registrato? <a href="login">Effettua l'accesso cliccando qui</a></p>
	</body>
</html>