<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>




<h1>Welcome ${name}</h1>
<hr>
<div class="container">
	<h1>List of ToDos</h1>
	<table class="table">
		<tr>
			
			<th>Description </th>
			<th>TargetDate </th>
			<th>Done </th>
			<th> </th>
			<th> </th>
		<tr>
		</thead>
	<tbody>
		<c:forEach items="${todos}" var = "todo">
<tr>
	
	<td> ${todo.description}</td>
	<td> ${todo.targetDate}</td>
	<td> ${todo.done}</td>
	<td> <a href= "delete-todo?id=${todo.id}" class="btn btn-warning">Delete </a></td>
	<td> <a href= "update-todo?id=${todo.id}" class="btn btn-warning">Update </a></td>
</tr>


</c:forEach>
</tbody>
</table>
<a href="add-todo" class="btn btn-success">Add new To do</a>
</div>
<%@ include file="common/footer.jspf" %>