<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Contrato" %>
<%@page import="model.ContratoDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contratos</title>
    </head>
    <body>
        
        <% 
            ArrayList<Contrato> lista = new ContratoDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Contratos</h1>
        
        <table>
            <tr>
                <th>Id</th>
                <th>Clube Id</th>
                <th>Atleta Id</th>
                <th>Salário Mensal (R$)</th>
                <th>Multa Rescisória (R$)</th>
                <th>Data Início</th>
                <th>Data Fim</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Contrato ct : lista ) { %>
                <tr>
                    <td><%= ct.getId() %></td>
                    <td><%= ct.getClubeId() %></td>
                    <td><%= ct.getAtletaId() %></td>
                    <td><%= String.format("%.2f", ct.getSalario()) %></td>
                    <td><%= String.format("%.2f", ct.getMultaRescisoria()) %></td>
                    <td><%= ct.getDataInicio() %></td>
                    <td><%= ct.getDataFim() %></td>
                    <td><%= ct.getStatus() %></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home/app/elenco/contrato_form.jsp?id=<%= ct.getId() %>">Alterar</a></td>
                    <td><a href="<%= request.getContextPath() %>/home?task=contrato&action=delete&id=<%= ct.getId() %>" onclick="return confirm('Deseja realmente excluir Contrato <%= ct.getId() %>')">Excluir</a></td>
                </tr>
            <% } %>
        </table>
            
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/elenco/contrato_form.jsp'">Adicionar</button>
        
    </body>
</html>