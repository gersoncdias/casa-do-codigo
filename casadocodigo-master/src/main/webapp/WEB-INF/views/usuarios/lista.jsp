<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

<div class="container">
	
	<a href="${s:mvcUrl('UC#form').build()}">Novo usuário</a><br>

		<h1>Lista de Usuários</h1><br>
		<p> ${message} </p>

		<table>
			
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th> </th>
			</tr>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
					<td>${usuario.roles}</td>			
					<td><a href="usuarios/roles?email=${usuario.email}"> <input type="image" src="${contextPath }resources/imagens/adicionar.png" alt="Adicionar" title="Adicionar" /> </a></td>
				</tr>			
			</c:forEach>
			
		</table>
		
</div>

</tags:pageTemplate>