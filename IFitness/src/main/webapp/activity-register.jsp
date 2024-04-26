<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jakarta.tags.core -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!-- jakarta.tags.functions -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>iFitness - Página de Cadastro de Atividade</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/activity-register.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="homeServlet">IFitness</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" href="homeServlet">Home</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<div class="container ">
		<div class="center col-lg-6 offset-lg-3 col-sm-12">
				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Atividade salva com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'notRegistered'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Atividade não salva. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
				
			<form action="activityRegister" method="post" id="form1">
					<c:choose>
						<c:when test="${activity == null}">
							<h1 class="text-center">Nova Atividade</h1>
						</c:when>
						<c:when test="${activity != null}">
							<h1 class="text-center">Edição de Atividade</h1>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${activity == null}">
							<input type="hidden" name="id" value="0">
						</c:when>
						<c:when test="${activity != null}">
							<input type="hidden" name="id" value="${activity.id}">
						</c:when>
					</c:choose>
					<div class="mb-2">
						<label for="type">Tipo*</label> 
						<select class="form-select"
							name="type" id="type" required="required">
							<c:choose>
								<c:when test="${activity == null}">
									<option value="" selected>Selecione</option>
								</c:when>
							</c:choose>
							
							<c:choose>
								<c:when test="${activity.type != 'CAMINHADA'}">
									<option value="CAMINHADA">Caminhada</option>
								</c:when>
								<c:when test="${activity.type == 'CAMINHADA'}">
									<option value="CAMINHADA" selected>Caminhada</option>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${activity.type != 'CICLISMO'}">
									<option value="CICLISMO">Ciclismo</option>
								</c:when>
								<c:when test="${activity.type == 'CICLISMO'}">
									<option value="CICLISMO" selected>Ciclismo</option>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${activity.type != 'CORRIDA'}">
									<option value="CORRIDA">Corrida</option>
								</c:when>
								<c:when test="${activity.type == 'CORRIDA'}">
									<option value="CORRIDA" selected>Corrida</option>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${activity.type != 'NATACAO'}">
									<option value="NATACAO">Natação</option>
								</c:when>
								<c:when test="${activity.type == 'NATACAO'}">
									<option value="NATACAO" selected>Natação</option>
								</c:when>
							</c:choose>
						</select>
					</div>
					
					<div class="mb-2">
						<label for="date">Data*</label> 
						<input type="date" name="date" id="date"
							class="form-control" required="required" value="${activity.date}">
					</div>

					<div class="mb-2">
						<label for="distance">Distância (Km)*</label> 
						<input type="number"
							name="distance" id="distance" class="form-control" step="0.1" 
							required="required" value="${activity.distance}">
					</div>

					<div class="mb-2">
						<label for="duration">Duração (minutos)*</label> 
						<input type="number" step="1"
							name="duration" id="duration" class="form-control" required="required"
							value="${activity.duration}">
					</div>

					<div class="col-12 mb-2">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>