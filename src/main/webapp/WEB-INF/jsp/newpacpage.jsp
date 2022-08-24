<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add New K-Pac</title>

    <link rel="stylesheet"
	    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<h2>K-Pac Form</h2>
		<p style="color:red;">${errorMessage}</p>

		<form method="POST" modelAttribute="kpac"
			class="form-horizontal">

			<div class="form-group">
				<label for="title">Title</label>
				<input name="title" class="form-control">
			</div>

			<div class="form-group">
				<label for="description">Description</label>
				<input name="description" class="form-control">
			</div>

			<hr />
			<button type="submit" class="btn btn-primary">Save</button>

		</form>
	</div>
</body>
</html>