<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Atleta" %>
<%@page import="model.AtletaDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Atleta</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Atleta at = null;
            String id = request.getParameter("id");
            if( id != null ) {
                at = new AtletaDAO().getUnique( Integer.parseInt( id ) );
                if( at != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Atleta</h1>
        
        <form action="<%= request.getContextPath() %>/home?task=atleta&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= at != null ? at.getId() : "" %>" required <%= at != null ? "readonly" : "" %>> <br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= at != null ? at.getNome() : "" %>" required><br/>
            
            <label for="cpf">CPF (000.000.000-00):</label>
            <input type="text" pattern="^\d{3}\.\d{3}\.\d{3}-\d{2}$" title="Formato: 000.000.000-00" id="cpf" name="cpf" value="<%= at != null ? at.getCpf() : "" %>" required><br/>
            
            <label for="data_nascimento">Data de Nascimento:</label>
            <input type="date" id="data_nascimento" name="data_nascimento" value="<%= ( at != null && at.getDataNascimento() != null ) ? at.getDataNascimento() : "" %>" required><br/>
            
            <label for="posicao">Posição:</label>
            <input type="text" id="posicao" name="posicao" value="<%= at != null ? at.getPosicao() : "" %>" required><br/>
            
            <label for="numero_camisa">Número da Camisa:</label>
            <input type="text" pattern="[0-9]*" id="numero_camisa" name="numero_camisa" value="<%= at != null ? at.getNumeroCamisa() : "" %>" required><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>