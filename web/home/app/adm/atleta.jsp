<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Atleta" %>
<%@page import="model.AtletaDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atletas</title>
    </head>
    <body>
        
        <% 
            ArrayList<Atleta> lista = new AtletaDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Atletas</h1>
        
        <table>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Data Nascimento</th>
                <th>Posição</th>
                <th>Número da Camisa</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Atleta at : lista ) { %>
                <tr>
                    <td><%= at.getId() %></td>
                    <td><%= at.getNome() %></td>
                    <td><%= at.getCpf() %></td>
                    <td><%= at.getDataNascimento() != null ? at.getDataNascimento() : "-" %></td>
                    <td><%= at.getPosicao() %></td>
                    <td><%= at.getNumeroCamisa() %></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home/app/adm/atleta_form.jsp?id=<%= at.getId() %>">Alterar</a></td>
                    <td><a href="<%= request.getContextPath() %>/home?task=atleta&action=delete&id=<%= at.getId() %>" onclick="return confirm('Deseja realmente excluir Atleta <%= at.getId() %> -- <%= at.getNome() %>')">Excluir</a></td>
                </tr>
            <% } %>
        </table>
            
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/adm/atleta_form.jsp'">Adicionar</button>
        
    </body>
</html>