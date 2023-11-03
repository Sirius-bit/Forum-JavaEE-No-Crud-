<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<html>
		<head>
    		<meta charset="UTF-8">
    		<meta name="viewport" content="width=device-width, initial-scale=1">
    		<title>Home Page</title>
    		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		</head>
		<body class="m-5 p-5">
    		<h1 class="text-center">Home Page</h1>
    		<div class="container mt-5">
        		<h2 class="text-center bg-light p-3">Lista Post di ${sessionScope.utente.utente} - ${sessionScope.utente.id}</h2>
        		<div class="d-flex justify-content-center mt-4 mb-4">
            		<a href="post?id=${sessionScope.utente.id}" class="btn btn-success">Crea un nuovo post</a>
        		</div>
        		<div class="row">
            		<c:forEach items="${sessionScope.ListaPost}" var="post">
                		<div class="col-md-6 mb-4">
                   			<div class="list-group-item">
                   				<h3>${post.utente.utente}</h3>
                        		<h4>${post.titolo_post}</h4>
                        		<p>${post.descrizione}</p>
                        		<p><a href="postVisualizer?id=${post.id}&idNome=${sessionScope.utente.id}" class="btn btn-primary">Visualizza post</a></p>
                        		<p>Commenti:</p>
                        		<ul class="list-group">
                            		<c:forEach items="${post.commenti}" var="commento">
                                		<li class="list-group-item">${commento.testo}</li>
                            		</c:forEach>
                        		</ul>
                    		</div>
                		</div>
            		</c:forEach>
        		</div>
    		</div>
		</body>
		</html>