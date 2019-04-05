<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

<div class="container">
	
	<h1>Cadastro de Permissões para ${usuario.nome}</h1><br>
	
	<c:if test="${usuario.nome == 'Admin'}">
			
			<form:form action="${s:mvcUrl('UC#alterar').arg(0, usuario.email).build()}" method="POST">
					
					Permissões:
							
						<input type="checkbox" name="role" value="ROLE_ADMIN" checked> ROLE_ADMIN
						<input type="checkbox" name="role" value="ROLE_COMERCIAL"> ROLE_COMERCIAL
						<input type="checkbox" name="role" value="ROLE_USER"> ROLE_USER<br>

				<input type="submit" value="Atualizar">
			</form:form>
			
		</c:if>
		<c:if test="${usuario.nome != 'Admin'}">
			<form:form action="${s:mvcUrl('UC#alterar').arg(0, usuario.email).build()}" method="POST">
					
					Permissões:
					
						
						<input type="checkbox" name="role" value="ROLE_ADMIN"> ROLE_ADMIN
						<input type="checkbox" name="role" value="ROLE_COMERCIAL"> ROLE_COMERCIAL
						<input type="checkbox" name="role" value="ROLE_USER" checked> ROLE_USER<br>

				<input type="submit" value="Atualizar">
			</form:form>
		</c:if>
	</div>
<br>
</tags:pageTemplate>