<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Clube" %>
<%@page import="model.ClubeDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clubes</title>
    </head>
    <body>
        
        <% 
            ArrayList<Clube> lista = new ClubeDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Clubes</h1>
        
        <table>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Sigla</th>
                <th>CNPJ</th>
                <th>Estádio Sede</th>
                <th>Orçamento Anual (R$)</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Clube cl : lista ) { %>
                <tr>
                    <td><%= cl.getId() %></td>
                    <td><%= cl.getNome() %></td>
                    <td><%= cl.getSigla() %></td>
                    <td><%= cl.getCnpj() %></td>
                    <td><%= cl.getEstadio() %></td>
                    <td><%= String.format("%.2f", cl.getOrcamento()) %></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home/app/adm/clube_form.jsp?id=<%= cl.getId() %>">Alterar</a></td>
                    <td><a href="<%= request.getContextPath() %>/home?task=clube&action=delete&id=<%= cl.getId() %>" onclick="return confirm('Deseja realmente excluir Clube <%= cl.getId() %> -- <%= cl.getNome() %>')">Excluir</a></td>
                </tr>
            <% } %>
        </table>
            
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/adm/clube_form.jsp'">Adicionar</button>
        
    </body>
</html>