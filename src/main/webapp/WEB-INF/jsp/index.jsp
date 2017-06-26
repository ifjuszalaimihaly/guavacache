<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html>
<head>
<meta charset="utf-8">


<title>Guava Chache Statistics</title>

<link href="static/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div role="navigation">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<a href="/" class="navbar-brand">Guava Project</a>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="list-people">List Person</a></li>
						<li><a href="add-person">Add Person</a></li>
						<li><a href="del-person">Delete Person</a></li>
					</ul>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>

	<div class="container">
		<!-- make all usecase in one view file -->
		<!-- select the usecase by mode property -->
		<c:choose>
	
			<c:when test="${mode == 'MODE_HOME'}">
				<div class="row">
					<h1>Welcome to Guava Project</h1>


					<div class="col-md-4">
						<h2>Cache statistic</h2>
						<!-- print the statistics param (key), and its value -->
						<ul>							
							<c:forEach var="stat" items="${stats}">
								<li>${stat.key}: ${stat.value}</li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</c:when>
			<c:when test="${mode == 'MODE_LIST_PEOPLE' }">
				<div class="row">
					<div class="col-md-4">
						<h2>Current Person</h2>
						<ul>
							<!-- listing the existing people -->
							<c:forEach var="person" items="${people}">
								<li>Id: ${person.value.id}, Name: ${person.value.name}, Age:
									${person.value.age}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:when>
			<c:when test="${mode == 'MODE_ADD_PERSON'}">
				<form class="form-inline" method="post" action="add-person">
					<div class="form-group">
						<label for="id">Id</label> <input type="number"
							class="form-control" name="id">
					</div>
					<div class="form-group">
						<label for="name">Name</label> <input type="text"
							class="form-control" name="name">
					</div>
					<div class="form-group">
						<label for="age">Age</label> <input type="number"
							class="form-control" name="age">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</c:when>
			<c:when test="${mode == 'MODE_DEL_PERSON'}">
				<form class="form-inline" method="post" action="del-person">
					<div class="form-group">
						<label for="id">Person</label>
									
						 <select class="form-control"
							name="id">
							
							<c:forEach var="person" items="${people}">
								<option value="${person.value.id}">${person.value.id}
									${person.value.name} ${person.value.age}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-default">Delete</button>
					<a class="btn btn-default" href="del-all-people">Delete All People</a>
				</form>
			</c:when>
		</c:choose>
	</div>

	<script src="static/js/jquery-3.2.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>