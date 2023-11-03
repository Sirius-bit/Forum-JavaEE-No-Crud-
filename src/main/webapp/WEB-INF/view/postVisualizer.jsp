<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Post</title>
	</head>
<body class="m-5 p-5">
    <div class="container">
        <h2>Questo è il post n° ${sessionScope.post_id} della lista</h2>
        <h2>E' stato pubblicato da ${sessionScope.post.utente.utente}</h2>
        <div class="mt-4">
            <h3>${sessionScope.post.titolo_post}</h3>
            <h4>${sessionScope.post.descrizione}</h4>
        </div>
        <form method="post" class="mt-4">
            <h2>Inserisci il commento</h2>
            <div class="form-group">
                <input type="text" name="commento" class="form-control mb-5" placeholder="Commento..." maxlength="300">
            </div>
            <button type="submit" class="btn btn-primary">Inserisci commento</button>
        </form>
    </div>
</body>
</html>