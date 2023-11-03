<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Post</title>
	</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <form method="post" action="post" class="col-lg-6 col-md-8 col-sm-10">
                <div class="mb-3">
                    <label class="form-label">Titolo Post</label>
                    <input type="text" name="titolo_post" required class="form-control">
                </div>
                <div class="mb-3">
                    <label class="form-label">Descrizione Post</label>
                    <input type="text" name="descrizione" required class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Pubblica Post</button>
            </form>
        </div>
    </div>
</body>
</html>