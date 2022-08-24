<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New K-Pac Set</title>

    <link rel="stylesheet"
    	    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>

<body>
	<div class="container">
		<h2>Add New K-Pac Set</h2>

		<form method="POST" modelAttribute="kset">
		    <div class="form-group">
        		<label for="title">Title</label>
        		<input name="title" class="form-control">
        	</div>
        	<table name="kpacs" class="table">
        	  <tr>
                <th>Selected</th>
                <th>Id</th>
                <th>Title</th>
              </tr>
              <c:forEach items="${kset.kpacs}" var="kpac">
                    <tr>
                        <td><input type="checkbox" path="${kpac.selected}" name="kpacs[${kpac.id}].selected" /></td>
                        <td><input type="hidden" name="kpacs[${kpac.id}].id" value=${kpac.id}>${kpac.id}</input></td>
                        <td>${kpac.title}</td>
                    </tr>
              </c:forEach>
        	</table>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>


	</div>

</body>
</html>