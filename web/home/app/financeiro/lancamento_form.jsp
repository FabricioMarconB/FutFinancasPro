<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.LancamentoFinanceiro" %>
<%@page import="model.LancamentoFinanceiroDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Lançamento Financeiro</title>
    </head>
    <body>
        
        <%
            String action = "new";
            LancamentoFinanceiro lf = null;
            String id = request.getParameter("id");
            if( id != null ) {
                lf = new LancamentoFinanceiroDAO().getUnique( Integer.parseInt( id ) );
                if( lf != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Lançamento Financeiro</h1>
        
        <form action="<%= request.getContextPath() %>/home?task=lancamento&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= lf != null ? lf.getId() : "" %>" required <%= lf != null ? "readonly" : "" %>> <br/>
            
            <label for="clube_id">Clube Id:</label>
            <input type="text" pattern="[0-9]*" id="clube_id" name="clube_id" value="<%= lf != null ? lf.getClubeId() : "" %>" required><br/>
            
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="<%= lf != null ? lf.getDescricao() : "" %>" required><br/>
            
            <label for="tipo">Tipo (RECEITA ou DESPESA):</label>
            <input type="text" id="tipo" name="tipo" value="<%= lf != null ? lf.getTipo() : "RECEITA" %>" required><br/>
            
            <label for="categoria">Categoria (Bilheteria, Patrocínio, Folha Salarial, Transferência):</label>
            <input type="text" id="categoria" name="categoria" value="<%= lf != null ? lf.getCategoria() : "" %>" required><br/>
            
            <label for="valor">Valor (R$):</label>
            <input type="number" step="0.01" id="valor" name="valor" value="<%= lf != null ? lf.getValor() : "" %>" required><br/>
            
            <label for="data_competencia">Data de Competência:</label>
            <input type="date" id="data_competencia" name="data_competencia" value="<%= ( lf != null && lf.getDataCompetencia() != null ) ? lf.getDataCompetencia() : "" %>" required><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>