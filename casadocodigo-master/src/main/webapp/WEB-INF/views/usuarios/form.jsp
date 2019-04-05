<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	
<div class="container">
	
<h1>Cadastro de Usuário</h1>
<p>${message}</p>


   <form:form  action="${s:mvcUrl('UC#gravar').build()}" method="POST" commandName="usuario">
        <div>
            <label>Nome</label><br>
            <input type="text" name="nome" />
            <form:errors path="nome" />
        </div>
        <div>
            <label>Email</label><br>
            <input type="email" name="email" />
            <form:errors path="email" />
            
        </div>
        <div>
            <label>Senha</label><br>           
            <input type="password" name="senha" />
            <form:errors path="senha" />
        </div>
        <div>
            <label>Senha repetida</label><br>
            <input type="password" name="senha_repetida" />
            <form:errors path="senha_repetida" />
        </div><br>
        
        <button type="submit"> Cadastrar </button>
        <br>
        <br>
  </form:form>
</div>
</tags:pageTemplate>
