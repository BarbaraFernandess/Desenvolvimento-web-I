<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="pt-BR">
  <head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link href="css/login.css" rel="stylesheet">
	
    <title>IFitness - Página de Login</title>
  </head>
  <body>
  	<div class="container">
  		<div class="col-lg-4 offset-lg-4 col-sm-12">
  			<c:if test="${result == 'registered'}">
  				<div class="alert alert-success alert-dismissible fade show" role="alert">
				  Usuário cadastrado com sucesso. Faça o login.
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
  			</c:if>
  			<c:if test="${result == 'loginError'}">
  				<div class="alert alert-danger alert-dismissible fade show" role="alert">
				  E-mail e/ou senha inválidos.
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
  			</c:if>
  			<form action="login" method="post">
  				<h1 class="text-center">Login</h1>
  				
  				<div class="input-group mb-3">
				  <span class="input-group-text">
				  	<img src="img/envelope.svg" alt="Ícone de e-mail">
				  </span>
				  <input type="email" class="form-control" placeholder="E-mail" 
				  	aria-label="E-mail" name="email" id="email">
				</div>
				
				<div class="input-group mb-3">
				  <span class="input-group-text">
				  	<img src="img/file-lock.svg" alt="Ícone de senha">
				  </span>
				  <input type="password" class="form-control" placeholder="Senha" 
				  	aria-label="Senha" name="password" id="password">
				</div>
				
				<button type="submit" class="btn btn-primary mb-3">
					Entrar
				</button>
				<a class="btn btn-secondary" href="user-register.jsp">
					Cadastrar
				</a>
  			</form>
  		</div>
  	</div>
    
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>
