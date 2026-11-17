<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.LancamentoFinanceiro" %>
<%@page import="model.LancamentoFinanceiroDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lançamentos Financeiros</title>
    </head>
    <body>
        
        <% 
            ArrayList<LancamentoFinanceiro> lista = new LancamentoFinanceiroDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Lançamentos Financeiros</h1>
        
        <table>
            <tr>
                <th>Id</th>
                <th>Clube Id</th>
                <th>Descrição</th>
                <th>Tipo</th>
                <th>Categoria</th>
                <th>Valor (R$)</th>
                <th>Data Competência</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( LancamentoFinanceiro lf : lista ) { %>
                <tr>
                    <td><%= lf.getId() %></td>
                    <td><%= lf.getClubeId() %></td>
                    <td><%= lf.getDescricao() %></td>
                    <td><%= lf.getTipo() %></td>
                    <td><%= lf.getCategoria() %></td>
                    <td><%= String.format("%.2f", lf.getValor()) %></td>
                    <td><%= lf.getDataCompetencia() %></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home/app/financeiro/lancamento_form.jsp?id=<%= lf.getId() %>">Alterar</a></td>
                    <td><a href="<%= request.getContextPath() %>/home?task=lancamento&action=delete&id=<%= lf.getId() %>" onclick="return confirm('Deseja realmente excluir Lançamento <%= lf.getId() %> -- <%= lf.getDescricao() %>')">Excluir</a></td>
                </tr>
            <% } %>
        </table>
            
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/financeiro/lancamento_form.jsp'">Adicionar</button>
        
    </body>
</html>